package mariri.safestones;


import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSafeColorStone extends BlockSafeStoneBase {
	
	protected BlockSafeColorStone(){
		super("safeColorStone", 16);
    }
	
    @Override
    public int damageDropped(int meta){
    	if(meta % 4 == 1){
    		return meta - 1;
    	}else{
    		return meta;
    	}
    }
    
	protected void registerRecipe(){
		// black
		// cobblestone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 0),
        		"XXX", "XYX", "XXX",
        		'X', "cobblestone",
        		'Y', new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE)
        	));

        // stone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 1),
        		"XXX", "XYX", "XXX",
        		'X', "stone",
        		'Y', new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE)
        	));
        
        GameRegistry.addSmelting(new ItemStack(this, 1, 0), new ItemStack(this, 1, 1), 0);
        
        // stonebrick
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 2),
        		"XXX", "XYX", "XXX",
        		'X', "stonebrick",
        		'Y', new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE)
        	));

        GameRegistry.addRecipe( new ItemStack(this, 4, 2),
        	new Object[] { "XX", "XX",
        		'X', new ItemStack(this, 1, 1)
        	});

        // smoothstone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 3),
        		"XXX", "XYX", "XXX",
        		'X', "smoothstone",
        		'Y', new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE)
        	));

        
        GameRegistry.addRecipe( new ItemStack(this, 4, 3),
        	new Object[] { "XX", "XX",
        		'X', new ItemStack(this, 1, 2)
        	});
		
		// red
		// cobblestone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 4),
        		"XXX", "XYX", "XXX",
        		'X', "cobblestone",
        		'Y', "dustRedstone"
        	));

        // stone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 5),
        		"XXX", "XYX", "XXX",
        		'X', "stone",
        		'Y', "dustRedstone"
        	));
        
        GameRegistry.addSmelting(new ItemStack(this, 1, 4), new ItemStack(this, 1, 5), 0);
        
        // stonebrick
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 6),
        		"XXX", "XYX", "XXX",
        		'X', "stonebrick",
        		'Y', "dustRedstone"
        	));

        GameRegistry.addRecipe( new ItemStack(this, 4, 6),
        	new Object[] { "XX", "XX",
        		'X', new ItemStack(this, 1, 5)
        	});

        // smoothstone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 7),
        		"XXX", "XYX", "XXX",
        		'X', "smoothstone",
        		'Y', "dustRedstone"
        	));

        GameRegistry.addRecipe( new ItemStack(this, 4, 7),
        	new Object[] { "XX", "XX",
				'X', new ItemStack(this, 1, 6)
        	});
		
		// blue
		// cobblestone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 8),
        		"XXX", "XYX", "XXX",
        		'X', "cobblestone",
        		'Y', "gemLapis"
        	));

        // stone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 9),
        		"XXX", "XYX", "XXX",
        		'X', "stone",
        		'Y', "gemLapis"
        	));
        
        GameRegistry.addSmelting(new ItemStack(this, 1, 8), new ItemStack(this, 1, 9), 0);
        
        // stonebrick
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 10),
        		"XXX", "XYX", "XXX",
        		'X', "stonebrick",
        		'Y', "gemLapis"
        	));

        GameRegistry.addRecipe( new ItemStack(this, 4, 10),
        	new Object[] { "XX", "XX",
        		'X', new ItemStack(this, 1, 9)
        	});

        // smoothstone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 11),
        		"XXX", "XYX", "XXX",
        		'X', "smoothstone",
        		'Y', "gemLapis"
        	));
        
        GameRegistry.addRecipe( new ItemStack(this, 4, 11),
        	new Object[] { "XX", "XX",
        		'X', new ItemStack(this, 1, 10)
        	});
        
		// white
		// cobblestone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 12),
        		"XXX", "XYX", "XXX",
        		'X', "cobblestone",
        		'Y', "gemQuartz"
        	));

        // stone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 13),
        		"XXX", "XYX", "XXX",
        		'X', "stone",
        		'Y', "gemQuartz"
        	));
        
        GameRegistry.addSmelting(new ItemStack(this, 1, 12), new ItemStack(this, 1, 13), 0);
        
        // stonebrick
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 14),
        		"XXX", "XYX", "XXX",
        		'X', "stonebrick",
        		'Y', "gemQuartz"
        	));

        GameRegistry.addRecipe( new ItemStack(this, 4, 14),
        	new Object[] { "XX", "XX",
        		'X', new ItemStack(this, 1, 13)
        	});

        // smoothstone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 15),
        		"XXX", "XYX", "XXX",
        		'X', "smoothstone",
        		'Y', "gemQuartz"
        	));

        GameRegistry.addRecipe( new ItemStack(this, 4, 15),
        	new Object[] { "XX", "XX",
        		'X', new ItemStack(this, 1, 14)
        	});
	}
	
	protected void registerOreDictionary(){
        OreDictionary.registerOre("cobblestone", new ItemStack(this, 1, 0));
        OreDictionary.registerOre("stone", new ItemStack(this, 1, 1));
        OreDictionary.registerOre("stonebrick", new ItemStack(this, 1, 2));
        OreDictionary.registerOre("smoothstone", new ItemStack(this, 1, 3));
        OreDictionary.registerOre("cobblestone", new ItemStack(this, 1, 4));
        OreDictionary.registerOre("stone", new ItemStack(this, 1, 5));
        OreDictionary.registerOre("stonebrick", new ItemStack(this, 1, 6));
        OreDictionary.registerOre("smoothstone", new ItemStack(this, 1, 7));
        OreDictionary.registerOre("cobblestone", new ItemStack(this, 1, 8));
        OreDictionary.registerOre("stone", new ItemStack(this, 1, 9));
        OreDictionary.registerOre("stonebrick", new ItemStack(this, 1, 10));
        OreDictionary.registerOre("smoothstone", new ItemStack(this, 1, 11));
        OreDictionary.registerOre("cobblestone", new ItemStack(this, 1, 12));
        OreDictionary.registerOre("stone", new ItemStack(this, 1, 13));
        OreDictionary.registerOre("stonebrick", new ItemStack(this, 1, 14));
        OreDictionary.registerOre("smoothstone", new ItemStack(this, 1, 15));
	}
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        this.icons[0] = iconRegister.registerIcon("mariri:black_cobblestone");
        this.icons[1] = iconRegister.registerIcon("mariri:black_stone");
        this.icons[2] = iconRegister.registerIcon("mariri:black_stonebrick");
        this.icons[3] = iconRegister.registerIcon("mariri:black_smoothstone");
        this.icons[4] = iconRegister.registerIcon("mariri:red_cobblestone");
        this.icons[5] = iconRegister.registerIcon("mariri:red_stone");
        this.icons[6] = iconRegister.registerIcon("mariri:red_stonebrick");
        this.icons[7] = iconRegister.registerIcon("mariri:red_smoothstone");
        this.icons[8] = iconRegister.registerIcon("mariri:blue_cobblestone");
        this.icons[9] = iconRegister.registerIcon("mariri:blue_stone");
        this.icons[10] = iconRegister.registerIcon("mariri:blue_stonebrick");
        this.icons[11] = iconRegister.registerIcon("mariri:blue_smoothstone");
        this.icons[12] = iconRegister.registerIcon("mariri:white_cobblestone");
        this.icons[13] = iconRegister.registerIcon("mariri:white_stone");
        this.icons[14] = iconRegister.registerIcon("mariri:white_stonebrick");
        this.icons[15] = iconRegister.registerIcon("mariri:white_smoothstone");
    }
}
