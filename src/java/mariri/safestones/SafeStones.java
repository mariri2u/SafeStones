package mariri.safestones;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SafeStones.MODID, version = SafeStones.VERSION)
public class SafeStones
{
    public static final String MODID = "SafeStones";
    public static final String VERSION = "1.8.9-1.1";

    public static BlockSafeVanillaStone blockSafeVanillaStone;
    public static BlockSafeColorStone blockSafeColorStone;
    public static BlockEnderStone blockEnderStone;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        ModRegistry.INVERT_SPAWN_RULE = config.get(Configuration.CATEGORY_GENERAL, "invertSpawnRule", false).getBoolean(false);
//        INVERT_SPAWNER_RULE = config.get(Configuration.CATEGORY_GENERAL, "invertSpawnerRule", false).getBoolean(false);

        config.save();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        blockSafeVanillaStone = new BlockSafeVanillaStone();
        blockSafeColorStone = new BlockSafeColorStone();
        blockEnderStone = new BlockEnderStone();

//        OreDictionary.registerOre("stonebrick", new ItemStack(Blocks.stonebrick));

        if(ModRegistry.INVERT_SPAWN_RULE){
            MinecraftForge.EVENT_BUS.register(CheckSpawnEventHandler.INSTANCE);
        }

        //テクスチャ・モデル指定JSONファイル名の登録。
        if (event.getSide().isClient()) {
        	blockSafeVanillaStone.registerTextures();
        	blockSafeColorStone.registerTextures();
            blockEnderStone.registerTextures();
        }
    }
}
