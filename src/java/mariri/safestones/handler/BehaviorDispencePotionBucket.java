package mariri.safestones.handler;

import mariri.safestones.SafeStones;
import mariri.safestones.item.ItemPotionBucket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BehaviorDispencePotionBucket extends BehaviorDefaultDispenseItem {
	public static BehaviorDispencePotionBucket INSTANCE = new BehaviorDispencePotionBucket();
	public static boolean DISPENSE_MAGIC_BUCKET;

	private BehaviorDispencePotionBucket(){}


	@Override
	public ItemStack dispenseStack(IBlockSource source, ItemStack itemstack) {
		World world = source.getWorld();
		IPosition ipos = BlockDispenser.getDispensePosition(source);
		BlockPos pos = new BlockPos(ipos.getX(), ipos.getY(), ipos.getZ());
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		int meta = block.getMetaFromState(state);
		Item item = DISPENSE_MAGIC_BUCKET ? SafeStones.itemMagicBucket : Items.BUCKET;

		if(block == Blocks.AIR){
			world.setBlockState(pos, SafeStones.blockFluidPotions[Potion.getIdFromPotion(((ItemPotionBucket)itemstack.getItem()).getPotionEffect()) - 1].getDefaultState());
			if(--itemstack.stackSize == 0){
				itemstack.setItem(item);
				itemstack.stackSize = 1;
			}else if(((TileEntityDispenser)source.getBlockTileEntity()).addItemStack(new ItemStack(item)) < 0){
				super.dispenseStack(source, new ItemStack(item));
			}
		}

		return itemstack;
	}
}
