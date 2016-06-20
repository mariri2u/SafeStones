package mariri.safestones;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class CheckSpawnEventHandler {
	public static CheckSpawnEventHandler INSTANCE = new CheckSpawnEventHandler();
	
	private CheckSpawnEventHandler() {}

	@SubscribeEvent
	public void onCheckSpawn(LivingSpawnEvent.CheckSpawn e){
		int x = (int)e.x;
		int y = (int)e.y;
		int z = (int)e.z;
		Block under = e.world.getBlock(x, y - 1, z);
		
		if(under instanceof BlockEnderStone){
			if(e.entityLiving instanceof EntityEnderman){
				e.setResult(Result.DEFAULT);
			}else{
				e.setResult(Result.DENY);
				EntityEnderman ender = new EntityEnderman(e.world);
				ender.setPosition(e.x + 0.5, e.y, e.z + 0.5);
				if(e.entityLiving instanceof IMob && ender.getCanSpawnHere()){
					ItemMonsterPlacer.spawnCreature(e.world, 58, x + 0.5, y, z + 0.5);
				}
			}
		}else if(under instanceof IBlockSafeStone){
			e.setResult(Result.DEFAULT);
		}else if(e.entityLiving instanceof IMob){
			e.setResult(Result.DENY);
		}else{
			e.setResult(Result.DEFAULT);
		}
		
//		if(under instanceof IBlockSafeStone && e.entityLiving instanceof IMob){
//			System.out.println("- Check Spawn -");
//			System.out.println(under.getClass().getCanonicalName() + " : " + e.entityLiving.getClass().getCanonicalName());
//			System.out.println("Result: " + e.getResult());
//		}
	}
}
