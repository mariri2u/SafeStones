package mariri.safestones;

import java.util.List;

import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockSafeStoneBase extends BlockStoneBrick implements IBlockSafeStone {
	@SideOnly(Side.CLIENT)
	protected ResourceLocation[] jsons;

	protected String unlocalizedName;

	public BlockSafeStoneBase(String unlocalizedName, int subvalue){
		super();
		setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypePiston);
		this.setHardness(2.0F);
		this.setResistance(2000.0F);
		this.unlocalizedName = unlocalizedName;

        GameRegistry.registerBlock(this, ItemBlockSafeStone.class, unlocalizedName);
        
        registerRecipe();
        registerOreDictionary();
	}
	
	protected abstract void registerRecipe();
	protected abstract void registerOreDictionary();

	@Override
	public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type){
		if(ModRegistry.INVERT_SPAWN_RULE){
			return super.canCreatureSpawn(world, pos, type);
		}else{
			return false;
		}
    }

    public String getUnlocalizedName(int meta){
    	return "tile."+ unlocalizedName + "." + meta;
    }
}
