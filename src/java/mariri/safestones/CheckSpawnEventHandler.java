package mariri.safestones;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.IMob;
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
			e.setResult(e.entityLiving instanceof EntityEnderman ? Result.DEFAULT : Result.DENY);
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
