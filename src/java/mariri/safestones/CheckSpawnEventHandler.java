package mariri.safestones;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class CheckSpawnEventHandler {
	public static CheckSpawnEventHandler INSTANCE = new CheckSpawnEventHandler();
	
	private CheckSpawnEventHandler() {}

	@SubscribeEvent
	public void onCheckSpawn(LivingSpawnEvent.CheckSpawn e){
		Block under = e.world.getBlockState(new BlockPos(e.x, e.y - 1, e.z	)).getBlock();

		if(under instanceof BlockEnderStone){
			if(e.entityLiving instanceof EntityEnderman){
				e.setResult(Event.Result.DEFAULT);
			}else{
				e.setResult(Event.Result.DENY);
				EntityEnderman ender = new EntityEnderman(e.world);
				ender.setPosition(e.x + 0.5, e.y, e.z + 0.5);
				if(e.entityLiving instanceof IMob && ender.getCanSpawnHere()){
					Entity entity = ItemMonsterPlacer.spawnCreature(e.world, "Enderman", e.x + 0.5, e.y, e.z + 0.5);
				}
			}
		}else if(under instanceof IBlockSafeStone){
			e.setResult(Event.Result.DEFAULT);
		}else if(e.entityLiving instanceof IMob){
			e.setResult(Event.Result.DENY);
		}else{
			e.setResult(Event.Result.DEFAULT);
		}
		
//		if(under instanceof IBlockSafeStone && e.entityLiving instanceof IMob){
//			System.out.println("- Check Spawn -");
//			System.out.println(under.getClass().getCanonicalName() + " : " + e.entityLiving.getClass().getCanonicalName());
//			System.out.println("Result: " + e.getResult());
//		}
	}
}
