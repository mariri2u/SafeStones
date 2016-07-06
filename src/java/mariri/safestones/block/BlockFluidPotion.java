package mariri.safestones.block;

import java.util.Random;

import mariri.safestones.SafeStones;
import mariri.safestones.misc.CustomPotionHelper;
import mariri.safestones.misc.SpawnHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockFluidPotion extends BlockFluidClassic {

	protected Potion potionEffect;
	protected boolean explode = true;
	protected int explosionPower = 3;
	protected boolean spawn = true;
	protected boolean infinity = true;

	public BlockFluidPotion(Fluid fluid, Material material, Potion effect) {
		super(fluid, material);

		int id = Potion.getIdFromPotion(effect);
		ResourceLocation res = new ResourceLocation(SafeStones.MODID, "fluidPotion" + id);

		this.setPotionEffect(effect);
		this.setExplode(SafeStones.ENABLE_REACT_EXPLOSION);
		this.setExplosionPower(SafeStones.REACT_EXPLOSION_POWER);
		this.setSpawn(SafeStones.ENABLE_REACT_SPAWN);
		this.setInfinity(SafeStones.ENABLE_INFINITY_SOURCE);
		this.setUnlocalizedName("fluidPotion" + id);
		GameRegistry.register(this, res);
		GameRegistry.register(new ItemBlock(this), res);
	}

	public BlockFluidPotion setPotionEffect(Potion potionEffect){
		this.potionEffect = potionEffect;
		return this;
	}

	public Potion getPotionEffect(){
		return potionEffect;
	}

	public BlockFluidPotion setExplode(boolean value){
		this.explode = value;
		return this;
	}

	public BlockFluidPotion setExplosionPower(int value){
		this.explosionPower = value;
		return this;
	}

	public BlockFluidPotion setSpawn(boolean value){
		this.spawn = value;
		return this;
	}

	public BlockFluidPotion setInfinity(boolean value){
		this.infinity = value;
		return this;
	}

//    protected void flowIntoBlock(World world, int x, int y, int z, int meta)
//    {
//    	Block block = world.getBlock(x, y, z);
//    	if(block == Blocks.water){
//
//    	}else if(block == Blocks.lava){
//
//    	}
//    	world.setBlockToAir(x, y, z);
//    	super.flowIntoBlock(world, x, y, z, meta);
//    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
//    	checkExplosion(world, x, y, z);
    	reactBlock(world, pos, world.getBlockState(pos.east()));
    	reactBlock(world, pos, world.getBlockState(pos.west()));
    	reactBlock(world, pos, world.getBlockState(pos.north()));
    	reactBlock(world, pos, world.getBlockState(pos.south()));
    	reactBlock(world, pos, world.getBlockState(pos.up()));
    	super.onBlockAdded(world, pos, state);
    }

    @Override
    public void onNeighborChange(IBlockAccess worldIn, BlockPos pos, BlockPos neighbor)
    {
    	World world = (World)worldIn;
//    	checkExplosion(world, x, y, z);
    	reactBlock(world, pos, world.getBlockState(pos.east()));
    	reactBlock(world, pos, world.getBlockState(pos.west()));
    	reactBlock(world, pos, world.getBlockState(pos.north()));
    	reactBlock(world, pos, world.getBlockState(pos.south()));
    	reactBlock(world, pos, world.getBlockState(pos.up()));
    	super.onNeighborChange(world, pos, neighbor);
    }

//    private boolean checkExplosion(World world, int x, int y, int z){
//        boolean flag = false;
//        if(!CustomPotionHelper.isSupport(potionEffect)) { return false; }
//        if(world.getBlock(x, y, z) == this){
//            flag |= world.getBlock(x, y, z - 1).getMaterial() == Material.lava;
//            flag |= world.getBlock(x, y, z + 1).getMaterial() == Material.lava;
//            flag |= world.getBlock(x - 1, y, z).getMaterial() == Material.lava;
//            flag |= world.getBlock(x + 1, y, z).getMaterial() == Material.lava;
//            flag |= world.getBlock(x, y + 1, z).getMaterial() == Material.lava;
//            if(world.getBlockMetadata(x, y, z) == 0){
//	           	flag |= world.canBlockSeeTheSky(x, y, z) && world.getBlockLightValue(x, y, z) == 15;
//	           	flag |= world.provider.isHellWorld;
//            }
//            if(flag){
//            	world.setBlockToAir(x, y, z);
//            	world.newExplosion(null, x, y, z, 4, true, false);
////            	world.createExplosion(null, x, y, z, 4, true);
//            }
//        }
//        return flag;
//    }

//    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
//    	if(world.getBlockMetadata(x, y, z) == 0){
//    		world.createExplosion(null, x, y, z, 4, true);
//    	}
//    }

    private void reactBlock(World world, BlockPos pos, IBlockState state){
    	Block infusion = state.getBlock();
    	boolean support = CustomPotionHelper.isSupport(this.potionEffect);
    	boolean instant = CustomPotionHelper.isInstant(this.potionEffect);
    	Fluid fluid = FluidRegistry.lookupFluidForBlock(infusion);
//    	if(world.getBlockMetadata(x, y, z) != 0){ return; }
    	if(this == infusion){ return; }
    	if(infusion instanceof BlockFluidPotion){
    		Potion effect = ((BlockFluidPotion)infusion).getPotionEffect();
    		if(support != CustomPotionHelper.isSupport(effect)){
            	createExplosion(world, pos);
    		}else if(support){
    			// Block
    			if(instant == CustomPotionHelper.isInstant(effect)){
        			world.setBlockState(pos, Blocks.GRAVEL.getStateFromMeta(0));
    			}else{
        			world.setBlockState(pos, Blocks.SAND.getStateFromMeta(0));
    			}
    			// Creature
				if(this.potionEffect == MobEffects.STRENGTH || effect == MobEffects.STRENGTH){
					spawnCreature(world, pos, new EntityEnderman(world), 0.1);
				}
				if(this.potionEffect == MobEffects.JUMP_BOOST || effect == MobEffects.JUMP_BOOST){
					spawnCreature(world, pos, new EntityBat(world), 0.1);
				}
				if(this.potionEffect == MobEffects.REGENERATION || effect == MobEffects.REGENERATION){
					spawnCreature(world, pos, new EntitySlime(world), 0.1);
				}
				if(this.potionEffect == MobEffects.WATER_BREATHING || effect == MobEffects.WATER_BREATHING){
					spawnCreature(world, pos, new EntitySquid(world), 0.1);
				}
				if(this.potionEffect == MobEffects.INVISIBILITY || effect == MobEffects.INVISIBILITY){
					spawnCreature(world, pos, new EntityVillager(world), 0.1);
				}
				if(this.potionEffect == MobEffects.NIGHT_VISION || effect == MobEffects.NIGHT_VISION){
					spawnCreature(world, pos, new EntitySpider(world), 0.1);
				}
			}else{
				// Block
				if(instant == CustomPotionHelper.isInstant(effect)){
	    			world.setBlockState(pos, Blocks.NETHERRACK.getStateFromMeta(0));
				}else{
	    			world.setBlockState(pos, Blocks.SOUL_SAND.getStateFromMeta(0));
				}
				// Creature
				if(this.potionEffect == MobEffects.SLOWNESS || effect == MobEffects.SLOWNESS){
					spawnCreature(world, pos, new EntityWitch(world), 1.0);
				}
				if(this.potionEffect == MobEffects.MINING_FATIGUE || effect == MobEffects.MINING_FATIGUE){
					spawnCreature(world, pos, new EntityCreeper(world), 1.0);
				}
				if(this.potionEffect == MobEffects.INSTANT_DAMAGE || effect == MobEffects.INSTANT_DAMAGE){
					// duplicate
					spawnCreature(world, pos, new EntityWitch(world), 1.0);
				}
				if(this.potionEffect == MobEffects.NAUSEA || effect == MobEffects.NAUSEA){
					// duplicate
					if(world.provider.doesWaterVaporize()){
						spawnCreature(world, pos, new EntityPigZombie(world), 1.0);
					}else{
						spawnCreature(world, pos, new EntityZombie(world), 1.0);
					}
				}
				if(this.potionEffect == MobEffects.BLINDNESS || effect == MobEffects.BLINDNESS){
					spawnCreature(world, pos, new EntityBlaze(world), 1.0);
				}
				if(this.potionEffect == MobEffects.HUNGER || effect == MobEffects.HUNGER){
					if(world.provider.doesWaterVaporize()){
						spawnCreature(world, pos, new EntityPigZombie(world), 1.0);
					}else{
						spawnCreature(world, pos, new EntityZombie(world), 1.0);
					}
				}
				if(this.potionEffect == MobEffects.WEAKNESS || effect == MobEffects.WEAKNESS){
					// duplicate
					spawnCreature(world, pos, new EntitySkeleton(world), 1.0);
				}
				if(this.potionEffect == MobEffects.POISON || effect == MobEffects.POISON){
					spawnCreature(world, pos, new EntityCaveSpider(world), 1.0);
				}
				if(this.potionEffect == MobEffects.WITHER || effect == MobEffects.WITHER){
					spawnCreature(world, pos, new EntitySkeleton(world), 1.0);
				}
			}
    	}else if(state.getMaterial() == Material.LAVA){
    		if(support){
            	createExplosion(world, pos);
    		}else{
    			world.setBlockState(pos, Blocks.SANDSTONE.getStateFromMeta(0));
    		}
    	}else if(fluid != null && fluid.getLuminosity() >= 9){
    		if(support){
            	world.setBlockState(pos, Blocks.GLASS.getStateFromMeta(0));
    		}else{
    			world.setBlockState(pos, Blocks.GLOWSTONE.getStateFromMeta(0));
    			spawnCreature(world, pos, new EntityCreeper(world), 1.0);
    			spawnCreature(world, pos, new EntityBlaze(world), 1.0);
    			spawnCreature(world, pos, new EntityCreeper(world), 0.8);
    			spawnCreature(world, pos, new EntityBlaze(world), 0.8);
    		}
    	}else if(fluid != null && infusion.isFlammable(world, pos, EnumFacing.UP)){
    		if(support){
            	createExplosion(world, pos);
    		}else{
    			world.setBlockState(pos, Blocks.COAL_BLOCK.getStateFromMeta(0));
				spawnCreature(world, pos, new EntityPigZombie(world), 1.0);
				spawnCreature(world, pos, new EntitySkeleton(world), 1.0);
				spawnCreature(world, pos, new EntityPigZombie(world), 0.8);
				spawnCreature(world, pos, new EntitySkeleton(world), 0.8);
    		}
    	}else if(state.getMaterial() == Material.WATER){
    		if(support){
    			world.setBlockState(pos, Blocks.MYCELIUM.getStateFromMeta(0));
    		}else{
    			world.setBlockState(pos, Blocks.CLAY.getStateFromMeta(0));
    		}
    	}else if(infusion == Blocks.FIRE){
    		if(support){
            	createExplosion(world, pos);
    		}
    	}else if(infusion == Blocks.ICE){
   			world.setBlockState(pos, SafeStones.blockSemiSolidPotion.getStateFromMeta(0));
    	}
    }

    private void spawnCreature(World world, BlockPos pos, EntityLiving creature, double chance){
    	if(spawn){
    		SpawnHelper.spawnCreature(world, pos, creature, chance, 4);
    	}
    }

    private boolean reactSunlight(World world, BlockPos pos){
    	boolean flag = false;
        if(world.getBlockState(pos).getBlock() == this && world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)) == 0 && CustomPotionHelper.isSupport(this.potionEffect)){
           	flag |= world.canBlockSeeSky(pos) && world.getLight(pos) == 15;
//           	flag |= world.canBlockSeeTheSky(x, y, z);
           	flag |= world.provider.doesWaterVaporize();
            if(flag){
            	world.setBlockToAir(pos);
            	createExplosion(world, pos);
            }
        }
        return flag;
    }

    private void createExplosion(World world, BlockPos pos){
    	if(explode){
    		world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), explosionPower, true);
    	}
    }

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
		super.updateTick(world, pos, state, rand);
		if(reactSunlight(world, pos)) { return; }
//		Block aboveBlock = world.getBlock(x, y + 1, z);
		int count = 0;
//		int waterCount = 0;
//		int lavaCount = 0;
		for(int i = 0; i < 4; i++){
			int offsetX = 0;
			int offsetZ = 0;
			switch(i){
			case 0:
				offsetX = -1;
				break;
			case 1:
				offsetX = 1;
				break;
			case 2:
				offsetZ = -1;
				break;
			case 3:
				offsetZ = 1;
				break;
			}
			Block block = world.getBlockState(pos.add(offsetX, 0, offsetZ)).getBlock();
			if(block == this && block.getMetaFromState(world.getBlockState(pos.add(offsetX, 0, offsetZ))) == 0){
				count++;
			}
//			if(block == Blocks.water){
//				waterCount++;
//			}
//			if(block == Blocks.lava){
//				lavaCount++;
//			}
		}
//		block = world.getBlock(x - 1, y, z);
//		if(block == this && world.getBlockMetadata(x - 1, y, z) == 0){
//			count++;
//		}
//		block = world.getBlock(x, y, z + 1);
//		if(block == this && world.getBlockMetadata(x, y, z + 1) == 0){
//			count++;
//		}
//		block = world.getBlock(x, y, z - 1);
//		if(block == this && world.getBlockMetadata(x, y, z - 1) == 0){
//			count++;
//		}
//		if(count >= 2){

//		if(aboveBlock == Blocks.water || waterCount > 0){
//			world.setBlock(x, y, z, Blocks.water, 1, 2);
//            world.scheduleBlockUpdate(x, y, z, Blocks.water, 0);
//            world.notifyBlocksOfNeighborChange(x, y, z, Blocks.water);
//		}else if(aboveBlock == Blocks.lava || lavaCount > 0){
//			if(world.getBlockMetadata(x, y, z) == 0){
//				world.createExplosion(null, x, y, z, 4, true);
//				world.setBlockToAir(x, y, z);
//	            world.scheduleBlockUpdate(x, y, z, Blocks.air, 0);
//	            world.notifyBlocksOfNeighborChange(x, y, z, Blocks.air);
//			}else{
//				world.setBlock(x, y, z, Blocks.lava, 1, 2);
//	            world.scheduleBlockUpdate(x, y, z, Blocks.lava, 0);
//	            world.notifyBlocksOfNeighborChange(x, y, z, Blocks.lava);
//			}
//		}else if(potCount >= 2 && world.getBlock(x, y - 1, z).isNormalCube()){
		if(infinity && count >= 2 && world.getBlockState(pos.down()).getBlock().isNormalCube(world.getBlockState(pos.down()))){
			world.setBlockState(pos, Block.getStateById(0), 2);
            world.scheduleBlockUpdate(pos, this, 0, 0);
            world.notifyNeighborsOfStateChange(pos, this);
		}

//		if(potCount >= 2 && world.getBlock(x, y - 1, z).isNormalCube()){
//			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
//            world.scheduleBlockUpdate(x, y, z, this, 0);
//            world.notifyBlocksOfNeighborChange(x, y, z, this);
//		}
	}

	@Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if(!world.isRemote && entity instanceof EntityLivingBase){
			if(CustomPotionHelper.isInstant(potionEffect)){
				if(world.getWorldTime() % 60 == 0){
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(potionEffect, CustomPotionHelper.INSTANT_DURATION, 0));
					if(potionEffect == MobEffects.INSTANT_DAMAGE && entity instanceof EntityVillager){
						if(world.rand.nextDouble() < 0.05){
							spawnItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.EMERALD));
						}
					}
				}
			}else{
				if(world.getWorldTime() % 20 == 0){
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(potionEffect, 100, 0));
//					if(potionEffect == CustomPotionHelper.NAUSEA && entity instanceof EntityCreature){
//						if(world.rand.nextDouble() <= 1){
//							spawnItem(world, x, y, z, new ItemStack(Items.spawn_egg, 1, entity.getEntityId()));
//						}
//					}
				}
			}
		}
	}

	private void spawnItem(World world, double x, double y, double z, ItemStack itemstack){
	    float f = 0.7F;
	    double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	    double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	    double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	    EntityItem entityitem =
	    		new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, itemstack);
	    entityitem.setPickupDelay(10);
	    world.spawnEntityInWorld(entityitem);
	}
//
//	@Override
//    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float p_149746_6_) {
//		if(entity instanceof EntityLiving && !CustomPotionHelper.isInstant(potionEffect)){
//			((EntityLiving)entity).addPotionEffect(new PotionEffect(potionEffect, 100, 0));
//		}
//
//	}



	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		if (world.getBlockState(pos).getMaterial().isLiquid()) {
			return false;
		}
		return super.canDisplace(world, pos);
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		if (world.getBlockState(pos).getMaterial().isLiquid()) {
			return false;
		}
		return super.displaceIfPossible(world, pos);
	}
}
