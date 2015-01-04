package mariri.safestones;


import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = SafeStones.MODID, version = SafeStones.VERSION)
public class SafeStones
{
    public static final String MODID = "SafeStones";
    public static final String VERSION = "1.0";
    
    public static BlockSafeVanillaStone blockSafeVanillaStone;
    public static BlockSafeColorStone blockSafeColorStone;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        blockSafeVanillaStone = new BlockSafeVanillaStone();
        blockSafeColorStone = new BlockSafeColorStone();
        
        OreDictionary.registerOre("stonebrick", new ItemStack(Blocks.stonebrick));
        
        //テクスチャ・モデル指定JSONファイル名の登録。
        if (event.getSide().isClient()) {
        	blockSafeVanillaStone.registerTextures();
        	blockSafeColorStone.registerTextures();
        }
    }
}
