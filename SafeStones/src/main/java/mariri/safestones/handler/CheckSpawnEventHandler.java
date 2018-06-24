package mariri.safestones.handler;

import mariri.safestones.block.BlockEnderStone;
import mariri.safestones.block.IBlockSafeStone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class CheckSpawnEventHandler {
	public static CheckSpawnEventHandler INSTANCE = new CheckSpawnEventHandler();

	private CheckSpawnEventHandler() {}

	@SubscribeEvent
	public void onCheckSpawn(LivingSpawnEvent.CheckSpawn e){
		Block under = findUnder(e.getWorld(), new BlockPos(e.getX(), e.getY(), e.getZ()));

		if(under instanceof BlockEnderStone){
			if(e.getEntityLiving() instanceof EntityEnderman){
				e.setResult(Event.Result.DEFAULT);
			}else{
				e.setResult(Event.Result.DENY);
				EntityEnderman ender = new EntityEnderman(e.getWorld());
				ender.setPosition(e.getX() + 0.5, e.getY(), e.getZ() + 0.5);
				if(e.getEntityLiving() instanceof IMob && ender.getCanSpawnHere()){
					Entity entity = ItemMonsterPlacer.spawnCreature(e.getWorld(), "Enderman", e.getX() + 0.5, e.getY(), e.getZ() + 0.5);
				}
			}
		}else if(under instanceof IBlockSafeStone){
			e.setResult(Event.Result.DEFAULT);
		}else if(e.getEntityLiving() instanceof IMob){
			e.setResult(Event.Result.DENY);
		}else{
			e.setResult(Event.Result.DEFAULT);
		}
	}

	private Block findUnder(World world, BlockPos pos){
		Block under = world.getBlockState(pos.down()).getBlock();

		if(under == Blocks.BEDROCK || pos.getY() <= 1){
			return Blocks.BEDROCK;
		}else if(under == Blocks.AIR || under instanceof BlockLiquid){
			return findUnder(world, pos.down());
		}else{
			return under;
		}
	}
}
