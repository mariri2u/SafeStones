package mariri.safestones;


import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSafeVanillaStone extends BlockStoneBrick implements IBlockSafeStone{
	
	@SideOnly(Side.CLIENT)
	protected Icon[] icons;

	protected String unlocalizedName;

	protected BlockSafeVanillaStone(){
		super(SafeStones.BLOCK_SAFE_VANILLA_STONE_ID);
		setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundStoneFootstep);
		this.setHardness(2.0F);
		this.setResistance(2000.0F);
    	this.icons = new Icon[5];
    	this.unlocalizedName = "safeVanillaStone";

//		setBlockName(unlocalizedName);
        GameRegistry.registerBlock(this, ItemBlockSafeStone.class, unlocalizedName);
   
        registerRecipe();
        registerOreDictionary();
	}
    
	private void registerRecipe(){
		
		// cobblestone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 0),
        		"XXX", "XYX", "XXX",
        		'X', "cobblestone",
        		'Y', new ItemStack(Item.goldNugget)
        	));

        // stone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 1),
        		"XXX", "XYX", "XXX",
        		'X', "stone",
        		'Y', new ItemStack(Item.goldNugget)
        	));
        
        FurnaceRecipes.smelting().addSmelting(this.blockID, 0, new ItemStack(this, 1, 1), 0);
        
        // stonebrick
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 2),
        		"XXX", "XYX", "XXX",
        		'X', "stonebrick",
        		'Y', new ItemStack(Item.goldNugget)
        	));

        GameRegistry.addRecipe( new ItemStack(this, 4, 2),
        	new Object[] { "XX", "XX",
        		'X', new ItemStack(this, 1, 1)
        	});

        // brick
        GameRegistry.addRecipe( new ItemStack(this, 8, 3),
        	new Object[] { "XXX", "XYX", "XXX",
        		'X', new ItemStack(Block.brick),
        		'Y', new ItemStack(Item.goldNugget)
        	});
        
        // nether brick
        GameRegistry.addRecipe( new ItemStack(this, 8, 4),
            new Object[] { "XXX", "XYX", "XXX",
            	'X', new ItemStack(Block.netherBrick),
            	'Y', new ItemStack(Item.goldNugget)
            });
    }
	
	private void registerOreDictionary(){
        OreDictionary.registerOre("cobblestone", new ItemStack(this, 1, 0));
        OreDictionary.registerOre("stone", new ItemStack(this, 1, 1));
        OreDictionary.registerOre("stonebrick", new ItemStack(this, 1, 2));
	}
	
    @Override
    public int damageDropped(int meta){
    	if(meta == 1){
    		return 0;
    	}else{
    		return meta;
    	}
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister){
        this.icons[0] = iconRegister.registerIcon("minecraft:cobblestone");
        this.icons[1] = iconRegister.registerIcon("minecraft:stone");
        this.icons[2] = iconRegister.registerIcon("minecraft:stonebrick");
        this.icons[3] = iconRegister.registerIcon("minecraft:brick");
        this.icons[4] = iconRegister.registerIcon("minecraft:nether_brick");
    }
    
    public String getUnlocalizedName(int meta){
    	return "tile."+ unlocalizedName + "." + meta;
    }
    
	@Override
    public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z){
    	return false;
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta){
    	if(meta < icons.length){
    		return icons[meta];
    	}else{
    		return icons[0];
    	}
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List list){
    	for(int i = 0; i < icons.length; i++){
    		list.add(new ItemStack(id, 1, i));
    	}
    }
}
