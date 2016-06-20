package mariri.safestones;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockSafeStoneBase extends Block implements IBlockSafeStone {

	public BlockSafeStoneBase(){
		super(Material.rock);
		this.setTickRandomly(true);
	}

	@Override
	public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type){
		if(world.getBlockState(pos.down()) == Blocks.mob_spawner) {
			return false;
		}else if(ModRegistry.INVERT_SPAWN_RULE){
			return super.canCreatureSpawn(world, pos, type);
		}else{
			return false;
		}
    }

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		onUpdate(world, pos);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
		super.updateTick(world, pos, state, rand);
		onUpdate(world, pos);
	}


	public void onUpdate(World world, BlockPos pos){
		if(world.getBlockState(pos.down()).getBlock() == Blocks.mob_spawner){
			TileEntity tileentity = world.getTileEntity(pos.down());
			if(tileentity instanceof TileEntityMobSpawner){
				MobSpawnerBaseLogic spawner = ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic();
				NBTTagCompound nbt = new NBTTagCompound();
				spawner.writeToNBT(nbt);
				nbt.setShort("Delay", (short)800);
				spawner.readFromNBT(nbt);
			}
			world.scheduleUpdate(pos, this, 100);
		}
	}
}
