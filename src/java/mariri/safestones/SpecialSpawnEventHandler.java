package mariri.safestones;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class SpecialSpawnEventHandler {
	public static SpecialSpawnEventHandler INSTANCE = new SpecialSpawnEventHandler();
	
	private SpecialSpawnEventHandler(){}

	@SubscribeEvent
	public void onSpecialSpawn(LivingSpawnEvent.SpecialSpawn e){
		int x = (int)e.x;
		int y = (int)e.y;
		int z = (int)e.z;
		Block under = e.world.getBlock(x, y - 1, z);
		
//		if(findUnderBlock(e.world, x, y, z) instanceof IBlockSafeStone){
//			e.setCanceled(false);
//		}else{
//			e.setCanceled(true);
//		}
		
		if(under instanceof IBlockSafeStone && e.entityLiving instanceof IMob){
			System.out.println("- Special Spawn -");
			System.out.println(under.getClass().getCanonicalName() + " : " + e.entityLiving.getClass().getCanonicalName());
			System.out.println("Result: " + e.getResult());
		}
	}
	
	private Block findUnderBlock(World world, int x, int y, int z){
		if(world.isAirBlock(x, y, z)){
			return findUnderBlock(world, x, y - 1, z);
		}else{
			return world.getBlock(x, y, z);
		}
	}
}
