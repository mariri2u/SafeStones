package mariri.safestones;


import mariri.safestones.block.BlockEnderStone;
import mariri.safestones.block.BlockFluidPotion;
import mariri.safestones.block.BlockSafeColorStone;
import mariri.safestones.block.BlockSafeVanillaStone;
import mariri.safestones.block.BlockSemiSolidPotion;
import mariri.safestones.block.MaterialPotion;
import mariri.safestones.handler.BehaviorDispencePotionBucket;
import mariri.safestones.handler.BehaviorDispenseMagicBucket;
import mariri.safestones.handler.BehaviorDispenseMagicGlass;
import mariri.safestones.handler.CheckSpawnEventHandler;
import mariri.safestones.handler.FillBucketHandler;
import mariri.safestones.handler.PlayerClickHandler;
import mariri.safestones.item.ItemMagicBottle;
import mariri.safestones.item.ItemMagicBucket;
import mariri.safestones.item.ItemPotionBucket;
import mariri.safestones.item.ItemSpawnerUpgrade;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SafeStones.MODID, version = SafeStones.VERSION, useMetadata = true)
public class SafeStones
{
    public static final String MODID = "SafeStones";
    public static final String VERSION = "1.9.4-1.1-dev1";

    public static BlockSafeVanillaStone blockSafeVanillaStone;
    public static BlockSafeColorStone blockSafeColorStone;
    public static BlockEnderStone blockEnderStone;


    public static BlockFluidPotion[] blockFluidPotions;
    public static BlockSemiSolidPotion blockSemiSolidPotion;
    public static Fluid[] fluidPotions;
    public static ItemPotionBucket[] itemPotionBuckets;
    public static ItemMagicBucket itemMagicBucket;
    public static ItemMagicBottle itemMagicBottle;
    public static ItemSpawnerUpgrade itemSpawnerUpgrade;
    public static int POTION_COUNT = 27;

    public static CreativeTabs creativeTab;

    public static Material potionMaterial;

    public static boolean ENABLE_REACT_EXPLOSION;
    public static int REACT_EXPLOSION_POWER;
    public static boolean ENABLE_REACT_SPAWN;
    public static boolean ENABLE_INFINITY_SOURCE;
    public static boolean DISPENSE_MAGIC_BUCKET;
    public static int POTION_STACK_SIZE;

    public static boolean SHOW_SPAWNER_DETAILS;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        ModRegistry.INVERT_SPAWN_RULE = config.get(Configuration.CATEGORY_GENERAL, "invertSpawnRule", false).getBoolean(false);
//        INVERT_SPAWNER_RULE = config.get(Configuration.CATEGORY_GENERAL, "invertSpawnerRule", false).getBoolean(false);

        ENABLE_REACT_EXPLOSION = config.get(Configuration.CATEGORY_GENERAL, "enableReactExplosion", true).getBoolean(true);
        REACT_EXPLOSION_POWER = config.get(Configuration.CATEGORY_GENERAL, "reactExplosionPower", 3).getInt();
        ENABLE_REACT_SPAWN = config.get(Configuration.CATEGORY_GENERAL, "enableReactSpawn", true).getBoolean(true);
        ENABLE_INFINITY_SOURCE = config.get(Configuration.CATEGORY_GENERAL, "enableInfinitySource", true).getBoolean(true);
        DISPENSE_MAGIC_BUCKET = config.get(Configuration.CATEGORY_GENERAL, "dispenseMagicBucket", false).getBoolean(false);
        POTION_STACK_SIZE = config.get(Configuration.CATEGORY_GENERAL, "potionStackSize", 8).getInt();

        SHOW_SPAWNER_DETAILS = config.get(Configuration.CATEGORY_GENERAL, "showSpawnerDetails", false).getBoolean(false);

        config.save();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	creativeTab = new CreativeSafeStones("Safe Stones");

    	blockSafeVanillaStone = new BlockSafeVanillaStone();
        blockSafeColorStone = new BlockSafeColorStone();
        blockEnderStone = new BlockEnderStone();

//        OreDictionary.registerOre("stonebrick", new ItemStack(Blocks.stonebrick));

       	Items.POTIONITEM.setMaxStackSize(POTION_STACK_SIZE);

    	itemMagicBottle = new ItemMagicBottle(POTION_STACK_SIZE);
        itemSpawnerUpgrade = new ItemSpawnerUpgrade();
        blockSemiSolidPotion = new BlockSemiSolidPotion();

        potionMaterial = new MaterialPotion();
        fluidPotions = new Fluid[POTION_COUNT];
        blockFluidPotions = new BlockFluidPotion[POTION_COUNT];
        itemPotionBuckets = new ItemPotionBucket[POTION_COUNT];

        for(int i = 0; i < POTION_COUNT; i++){
        	fluidPotions[i] = new Fluid(
        				"potion" + (i + 1),
        				new ResourceLocation(SafeStones.MODID, "blocks/potion" + (i + 1) + "_still"),
        				new ResourceLocation(SafeStones.MODID, "blocks/potion" + (i + 1) + "_flow")
        			).setDensity(800).setViscosity(1000);
        	FluidRegistry.registerFluid(fluidPotions[i]);
    		FluidRegistry.addBucketForFluid(fluidPotions[i]);

        	blockFluidPotions[i] = new BlockFluidPotion(fluidPotions[i], Material.WATER, Potion.getPotionById(i + 1));
        	itemPotionBuckets[i] = new ItemPotionBucket(blockFluidPotions[i]);

        	FluidContainerRegistry.registerFluidContainer(fluidPotions[i], new ItemStack(itemPotionBuckets[i]), FluidContainerRegistry.EMPTY_BUCKET);

        	FillBucketHandler.INSTANCE.buckets.put(blockFluidPotions[i], itemPotionBuckets[i]);
        }

    	itemMagicBucket = new ItemMagicBucket();


        if(ModRegistry.INVERT_SPAWN_RULE){
            MinecraftForge.EVENT_BUS.register(CheckSpawnEventHandler.INSTANCE);
        }

        //テクスチャ・モデル指定JSONファイル名の登録。
        if (event.getSide().isClient()) {
        	// SafeStones
        	blockSafeVanillaStone.registerTextures();
        	blockSafeColorStone.registerTextures();
            blockEnderStone.registerTextures();

            // PotionItem
            itemMagicBottle.registerTextures();
            itemMagicBucket.registerTextures();
            for(int i = 0; i < itemPotionBuckets.length; i++){
            	itemPotionBuckets[i].registerTextures(i + 1);
            }
            itemSpawnerUpgrade.registerTextures();

            // PotionBlock
            blockSemiSolidPotion.registerTextures();
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	MinecraftForge.EVENT_BUS.register(FillBucketHandler.INSTANCE);
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(itemMagicBottle, BehaviorDispenseMagicGlass.INSTANCE);
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(itemMagicBucket, BehaviorDispenseMagicBucket.INSTANCE);
		if(!DISPENSE_MAGIC_BUCKET){
			BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.BUCKET, BehaviorDispenseMagicBucket.INSTANCE);
		}
		BehaviorDispencePotionBucket.DISPENSE_MAGIC_BUCKET = DISPENSE_MAGIC_BUCKET;
		for(int i = 0; i < POTION_COUNT; i++){
    		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(itemPotionBuckets[i], BehaviorDispencePotionBucket.INSTANCE);
    	}

		MinecraftForge.EVENT_BUS.register(new PlayerClickHandler());
    }
}
