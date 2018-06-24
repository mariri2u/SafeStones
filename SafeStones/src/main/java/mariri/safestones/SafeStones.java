package mariri.safestones;


import mariri.safestones.block.BlockEnderStone;
import mariri.safestones.block.BlockSafeColorStone;
import mariri.safestones.block.BlockSafeVanillaStone;
import mariri.safestones.handler.CheckSpawnEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SafeStones.MODID, version = SafeStones.VERSION, useMetadata = true)
public class SafeStones
{
    public static final String MODID = "SafeStones";
    public static final String VERSION = "1.10.2-1.0a";

    public static BlockSafeVanillaStone blockSafeVanillaStone;
    public static BlockSafeColorStone blockSafeColorStone;
    public static BlockEnderStone blockEnderStone;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	initConfig(new Configuration(event.getSuggestedConfigurationFile()));
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	initBlocks();

        //テクスチャ・モデル指定JSONファイル名の登録。
        if (event.getSide().isClient()) {
        	initTexture();
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    }

    private void initConfig(Configuration config){
        config.load();

        ModRegistry.INVERT_SPAWN_RULE = config.get(Configuration.CATEGORY_GENERAL, "invertSpawnRule", false).getBoolean(false);
        config.save();
    }

    private void initBlocks(){
    	blockSafeVanillaStone = new BlockSafeVanillaStone();
        blockSafeColorStone = new BlockSafeColorStone();
        blockEnderStone = new BlockEnderStone();

        if(ModRegistry.INVERT_SPAWN_RULE){
            MinecraftForge.EVENT_BUS.register(CheckSpawnEventHandler.INSTANCE);
        }
    }

    private void initTexture(){
    	blockSafeVanillaStone.registerTextures();
    	blockSafeColorStone.registerTextures();
        blockEnderStone.registerTextures();
    }
}
