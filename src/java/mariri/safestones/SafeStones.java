package mariri.safestones;


import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = SafeStones.MODID, version = SafeStones.VERSION)
public class SafeStones
{
    public static final String MODID = "SafeStones";
    public static final String VERSION = "1.0";
    
    public static BlockSafeVanillaStone blockSafeStone;
    public static BlockSafeColorStone blockSafeColorStone;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        blockSafeStone = new BlockSafeVanillaStone();
        blockSafeColorStone = new BlockSafeColorStone();
        
        OreDictionary.registerOre("stonebrick", new ItemStack(Blocks.stonebrick));
    }
}
