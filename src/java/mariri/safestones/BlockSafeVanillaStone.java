package mariri.safestones;


import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BlockSafeVanillaStone extends Block{
	
	public static int SUB_BLOCK_VALUES = 15;

    //BlockState用Property変数。今回はmetadataと同じようなPropertyIntegerを用いる。
    public static final PropertyInteger METADATA = PropertyInteger.create("meta", 0, SUB_BLOCK_VALUES - 1);;
	
	protected String name;

	protected BlockSafeVanillaStone(){
		super(Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypePiston);
		this.setHardness(2.0F);
		this.setResistance(2000.0F);
    	this.name = "safeVanillaStone";
    	this.setDefaultState(this.blockState.getBaseState().withProperty(METADATA, 0));
    	this.setUnlocalizedName(name);
    	
        GameRegistry.registerBlock(this, ItemBlockSafeStone.class, name);
   
        registerOreDictionary();
        registerRecipe();
	}
	
    @Override
    public int damageDropped(IBlockState state){
    	int meta = state.getBlock().getMetaFromState(state);
    	if(meta == 1){
    		return 0;
    	}else{
    		return meta;
    	}
    }
    
    //ItemStackのmetadataからIBlockStateを生成。設置時に呼ばれる。
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(METADATA, meta);
    }
 
    //IBlockStateからItemStackのmetadataを生成。ドロップ時とテクスチャ・モデル参照時に呼ばれる。
    @Override
    public int getMetaFromState(IBlockState state) {
        return (Integer)state.getValue(METADATA);
    }
 
    //初期BlockStateの生成。
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, METADATA);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerTextures(){
    	String[] jsons = new String[SUB_BLOCK_VALUES];
    	jsons[0] = "cobblestone";
    	jsons[1] = "stone";
    	jsons[2] = "stonebrick";
    	jsons[3] = "brick_block";
    	jsons[4] = "nether_brick";
    	jsons[5] = "sandstone";
    	jsons[6] = "smooth_sandstone";
    	jsons[7] = "red_sandstone";
    	jsons[8] = "smooth_red_sandstone";
    	jsons[9] = "granite";
    	jsons[10] = "granite_smooth";
    	jsons[11] = "diorite";
    	jsons[12] = "diorite_smooth";
    	jsons[13] = "andesite";
    	jsons[14] = "andesite_smooth";
        //モデルJSONファイルのファイル名を登録。1IDで1つだけなら、登録名はGameRegistryでの登録名と同じものにする。
        //ItemStackのmetadataで種類を分けて描画させたい場合。登録名を予め登録する。
        ModelBakery.addVariantName(Item.getItemFromBlock(this), jsons);
        //1IDで複数モデルを登録するなら、上のメソッドで登録した登録名を指定する。
        for(int i = 0; i < jsons.length; i++){
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), i, new ModelResourceLocation(jsons[i], "inventory"));        	
        }
    }
    
	@Override
    public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type){
    	return false;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list){
    	for(int i = 0; i < SUB_BLOCK_VALUES; i++){
    		list.add(new ItemStack(item, 1, i));
    	}
    }

	private void registerOreDictionary(){
        OreDictionary.registerOre("cobblestone", new ItemStack(this, 1, 0));
        OreDictionary.registerOre("stone", new ItemStack(this, 1, 1));
        OreDictionary.registerOre("stonebrick", new ItemStack(this, 1, 2));
	}

	private void registerRecipe(){
		// cobblestone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 0),
        		"XXX", "XYX", "XXX",
        		'X', "cobblestone",
        		'Y', "nuggetGold"
        	));

        // stone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 1),
        		"XXX", "XYX", "XXX",
        		'X', "stone",
        		'Y', "nuggetGold"
        	));
        
        GameRegistry.addSmelting(new ItemStack(this, 1, 0), new ItemStack(this, 1, 1), 0);
        
        // stonebrick
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 2),
        		"XXX", "XYX", "XXX",
        		'X', "stonebrick",
        		'Y', "nuggetGold"
        	));

        GameRegistry.addRecipe( new ItemStack(this, 1, 2),
        	new Object[] { "XX", "XX",
        		'X', new ItemStack(this, 1, 1)
        	});

        // brick
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 3),
        		"XXX", "XYX", "XXX",
        		'X', new ItemStack(Blocks.brick_block),
        		'Y', "nuggetGold"
        	));
        
        // nether brick
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 4),
        		"XXX", "XYX", "XXX",
            	'X', new ItemStack(Blocks.nether_brick),
            	'Y', "nuggetGold"
            ));
        
		// sandstone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 5),
        		"XXX", "XYX", "XXX",
        		'X', new ItemStack(Blocks.sandstone),
        		'Y', "nuggetGold"
        	));
        
		// smooth sandstone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 6),
        		"XXX", "XYX", "XXX",
        		'X', new ItemStack(Blocks.sandstone, 1, 2),
        		'Y', "nuggetGold"
        	));
        
		// red sandstone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 7),
        		"XXX", "XYX", "XXX",
        		'X', new ItemStack(Blocks.red_sandstone),
        		'Y', "nuggetGold"
        	));
        
		// smooth sandstone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 8),
        		"XXX", "XYX", "XXX",
        		'X', new ItemStack(Blocks.red_sandstone, 1, 2),
        		'Y', "nuggetGold"
        	));
        
		// granite
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 9),
        		"XXX", "XYX", "XXX",
        		'X', new ItemStack(Blocks.stone, 1, 1),
        		'Y', "nuggetGold"
        	));
        
        GameRegistry.addRecipe( new ItemStack(this, 1, 9),
            	new Object[] { "XX", "XX",
            		'X', new ItemStack(this, 1, 9)
            	});
        
		// polished granite
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 10),
        		"XY",
        		'X', new ItemStack(Blocks.stone, 1, 0),
        		'Y', new ItemStack(Blocks.stone, 1, 11)
        	));
        
        GameRegistry.addRecipe( new ItemStack(this, 1, 10),
            	new Object[] { "XX", "XX",
            		'X', new ItemStack(this, 1, 9)
            	});
        
		// diorite
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 11),
        		"XXX", "XYX", "XXX",
        		'X', new ItemStack(Blocks.stone, 1, 3),
        		'Y', "nuggetGold"
        	));
        
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 11),
        		"YX", "XY", 
        		'X', new ItemStack(this, 1, 0),
        		'Y', "gemQuartz"
        	));
        
		// polished diorite
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 12),
        		"XXX", "XYX", "XXX",
        		'X', new ItemStack(Blocks.stone, 1, 4),
        		'Y', "nuggetGold"
        	));
        
        GameRegistry.addRecipe( new ItemStack(this, 1, 12),
            	new Object[] { "XX", "XX",
            		'X', new ItemStack(this, 1, 11)
            	});
        
		// andesite
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 13),
        		"XXX", "XYX", "XXX",
        		'X', new ItemStack(Blocks.stone, 1, 5),
        		'Y', "nuggetGold"
        	));
        
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 13),
        		"XY", 
        		'X', new ItemStack(this, 1, 11),
        		'Y', "gemQuartz"
        	));
        
		// polished andesite
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 14),
        		"XXX", "XYX", "XXX",
        		'X', new ItemStack(Blocks.stone, 1, 6),
        		'Y', "nuggetGold"
        	));
        
        GameRegistry.addRecipe( new ItemStack(this, 1, 14),
            	new Object[] { "XX", "XX",
            		'X', new ItemStack(this, 1, 13)
            	});
    }
}
