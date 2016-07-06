package mariri.safestones.handler;

import mariri.safestones.SafeStones;
import mariri.safestones.misc.CustomSpawnerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerClickHandler {

	@SubscribeEvent
	public void onPlayerClick(PlayerInteractEvent.RightClickBlock e){
		if(!e.getEntityPlayer().worldObj.isRemote){
			if(!e.getEntityPlayer().isSneaking()){
				onRightClickBlock(e);
			}else{
				onShiftRightClickBlock(e);
			}
		}
	}

	private void onRightClickBlock(PlayerInteractEvent e){
		World world = e.getEntityPlayer().worldObj;
		IBlockState state = world.getBlockState(e.getPos());
		Block block = state.getBlock();
		int meta = block.getMetaFromState(state);

		if(		e.getEntityPlayer().inventory.getCurrentItem() != null &&
				e.getEntityPlayer().inventory.getCurrentItem().getItem() == Items.STICK &&
				block == Blocks.MOB_SPAWNER){
			CustomSpawnerHelper.showSpawnerLevel(world, e.getPos(), e.getEntityPlayer());
			e.setCanceled(true);
		}
	}



	private void onShiftRightClickBlock(PlayerInteractEvent e){
		World world = e.getEntityPlayer().worldObj;
		IBlockState state = world.getBlockState(e.getPos());
		Block block = state.getBlock();
		int meta = block.getMetaFromState(state);

		if(		e.getEntityPlayer().inventory.getCurrentItem() != null &&
				e.getEntityPlayer().inventory.getCurrentItem().getItem() == Items.STICK &&
				block == Blocks.MOB_SPAWNER){
			if(SafeStones.SHOW_SPAWNER_DETAILS){
				CustomSpawnerHelper.showSpawnerDetails(world, e.getPos(), e.getEntityPlayer());
			}else{
				CustomSpawnerHelper.showSpawnerLevel(world, e.getPos(), e.getEntityPlayer());
			}
        	e.setCanceled(true);
		}
	}
}
