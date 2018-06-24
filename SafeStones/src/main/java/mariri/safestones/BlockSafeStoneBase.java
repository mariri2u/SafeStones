package mariri.safestones;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockStoneBrick;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockSafeStoneBase extends BlockStoneBrick implements IBlockSafeStone {
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;

	protected String unlocalizedName;

	public BlockSafeStoneBase(String unlocalizedName, int subvalue){
		super();
		setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypePiston);
		this.setHardness(2.0F);
		this.setResistance(2000.0F);
		this.unlocalizedName = unlocalizedName;
		
		setBlockName(unlocalizedName);
        GameRegistry.registerBlock(this, ItemBlockSafeStone.class, unlocalizedName);
        
    	this.icons = new IIcon[subvalue];
    	
    	this.setTickRandomly(true);
    	
        registerRecipe();
        registerOreDictionary();
	}
	
	protected abstract void registerRecipe();
	protected abstract void registerOreDictionary();
	
	@Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z){
		if(world.getBlock(x, y - 1, z) == Blocks.mob_spawner){
			return false;
		}else if(ModRegistry.INVERT_SPAWN_RULE){
			return super.canCreatureSpawn(type, world, x, y, z);
		}else{
			return false;
		}
    }
	
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemstack)
    {
    	super.onBlockPlacedBy(world, x, y, z, entityLiving, itemstack);
    	onUpdate(world, x, y, z);
    }
    
	@Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
    	super.updateTick(world, x, y, z, rand);
    	onUpdate(world, x, y, z);
    }
    
    public void onUpdate(World world, int x, int y, int z){
    	if(world.getBlock(x, y - 1, z) == Blocks.mob_spawner){
    		TileEntity tileentity = world.getTileEntity(x, y - 1, z);
    		if(tileentity instanceof TileEntityMobSpawner){
    			TileEntityMobSpawner spawner = (TileEntityMobSpawner)tileentity;
    			spawner.func_145881_a().spawnDelay = 800;
    		}
    		world.scheduleBlockUpdate(x, y, z, this, 100);
    	}
    }
    
    public String getUnlocalizedName(int meta){
    	return "tile."+ unlocalizedName + "." + meta;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
    	if(meta < icons.length){
    		return icons[meta];
    	}else{
    		return icons[0];
    	}
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list){
    	for(int i = 0; i < icons.length; i++){
    		list.add(new ItemStack(item, 1, i));
    	}
    }
}
