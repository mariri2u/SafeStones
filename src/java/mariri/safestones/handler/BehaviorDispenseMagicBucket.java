package mariri.safestones.handler;

import mariri.safestones.SafeStones;
import mariri.safestones.block.BlockFluidPotion;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BehaviorDispenseMagicBucket extends BehaviorDefaultDispenseItem {
	public final static BehaviorDispenseMagicBucket INSTANCE = new BehaviorDispenseMagicBucket();

	private BehaviorDispenseMagicBucket(){}

	@Override
	public ItemStack dispenseStack(IBlockSource source, ItemStack itemstack){
		World world = source.getWorld();
		IPosition ipos = BlockDispenser.getDispensePosition(source);
		BlockPos pos = new BlockPos(ipos.getX(), ipos.getY(), ipos.getZ());
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		int meta = block.getMetaFromState(state);
		Item item;

		if(block instanceof BlockFluidPotion && meta == 0){
			item = SafeStones.itemPotionBuckets[Potion.getIdFromPotion(((BlockFluidPotion)block).getPotionEffect()) - 1];
		}else if(state.getMaterial() == Material.WATER){
			item = Items.WATER_BUCKET;
		}else if(state.getMaterial() == Material.LAVA){
			item = Items.LAVA_BUCKET;
		}else{
			return super.dispenseStack(source, itemstack);
		}

		world.setBlockToAir(pos);

		if(--itemstack.stackSize == 0){
			itemstack.setItem(item);
			itemstack.stackSize = 1;
		}else if(((TileEntityDispenser)source.getBlockTileEntity()).addItemStack(new ItemStack(item)) < 0){
			super.dispenseStack(source, new ItemStack(item));
		}

		return itemstack;
	}
}
