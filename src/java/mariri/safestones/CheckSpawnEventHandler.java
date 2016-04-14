package mariri.safestones;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.IMob;
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
			e.setResult(e.entityLiving instanceof EntityEnderman ? Event.Result.DEFAULT : Event.Result.DENY);
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
