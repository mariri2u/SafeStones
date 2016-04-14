package mariri.safestones;

import java.util.List;

import net.minecraft.block.BlockStoneBrick;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
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

        registerRecipe();
        registerOreDictionary();
	}
	
	protected abstract void registerRecipe();
	protected abstract void registerOreDictionary();
	
	@Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z){
		if(ModRegistry.INVERT_SPAWN_RULE){
			return super.canCreatureSpawn(type, world, x, y, z);
		}else{
			return false;
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
