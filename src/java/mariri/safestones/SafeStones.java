package mariri.safestones;


import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SafeStones.MODID, version = SafeStones.VERSION)
public class SafeStones
{
    public static final String MODID = "SafeStones";
    public static final String VERSION = "1.0";
    
    public static int BLOCK_SAFE_VANILLA_STONE_ID;
    public static int BLOCK_SAFE_COLOR_STONE_ID;
    
    public static BlockSafeVanillaStone blockSafeStone;
    public static BlockSafeColorStone blockSafeColorStone;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        
        BLOCK_SAFE_VANILLA_STONE_ID = config.get(Configuration.CATEGORY_BLOCK, "safeVanillaStoneId",700).getInt();
        BLOCK_SAFE_COLOR_STONE_ID = config.get(Configuration.CATEGORY_BLOCK, "safeColorStoneId", 701).getInt();
        config.save();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        blockSafeStone = new BlockSafeVanillaStone();
        blockSafeColorStone = new BlockSafeColorStone();
        
        OreDictionary.registerOre("stonebrick", new ItemStack(Block.stoneBrick));
    }
}
