package mariri.safestones;


import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSafeVanillaStone extends BlockSafeStoneBase{

	protected BlockSafeVanillaStone(){
		super("safeVanillaStone", 5);
	}
	
    @Override
    public int damageDropped(int meta){
    	if(meta == 1){
    		return 0;
    	}else{
    		return meta;
    	}
    }
    
	protected void registerRecipe(){
		// cobblestone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 0),
        		"XXX", "XYX", "XXX",
        		'X', "cobblestone",
        		'Y', "nuggetGold"
        	));

        // stone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 1),
        		"XXX", "XYX", "XXX",
        		'X', "stone",
        		'Y', "nuggetGold"
        	));
        
        GameRegistry.addSmelting(new ItemStack(this, 1, 0), new ItemStack(this, 1, 1), 0);
        
        // stonebrick
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 2),
        		"XXX", "XYX", "XXX",
        		'X', "stonebrick",
        		'Y', "nuggetGold"
        	));

        GameRegistry.addRecipe( new ItemStack(this, 4, 2),
        	new Object[] { "XX", "XX",
        		'X', new ItemStack(this, 1, 1)
        	});

        // brick
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 3),
        		"XXX", "XYX", "XXX",
        		'X', new ItemStack(Blocks.brick_block),
        		'Y', "nuggetGold"
        	));
        
        // nether brick
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 8, 4),
        		"XXX", "XYX", "XXX",
            	'X', new ItemStack(Blocks.nether_brick),
            	'Y', "nuggetGold"
            ));
    }
	
	protected void registerOreDictionary(){
        OreDictionary.registerOre("cobblestone", new ItemStack(this, 1, 0));
        OreDictionary.registerOre("stone", new ItemStack(this, 1, 1));
        OreDictionary.registerOre("stonebrick", new ItemStack(this, 1, 2));
	}
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        this.icons[0] = iconRegister.registerIcon("minecraft:cobblestone");
        this.icons[1] = iconRegister.registerIcon("minecraft:stone");
        this.icons[2] = iconRegister.registerIcon("minecraft:stonebrick");
        this.icons[3] = iconRegister.registerIcon("minecraft:brick");
        this.icons[4] = iconRegister.registerIcon("minecraft:nether_brick");
    }
}
