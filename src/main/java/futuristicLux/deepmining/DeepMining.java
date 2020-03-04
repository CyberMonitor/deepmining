package futuristicLux.deepmining;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import futuristicLux.deepmining.common.Blocks.DeepDeposit;
import futuristicLux.deepmining.common.TileEntitys.DepositTileEntity;

@Mod("deepmining")
public class DeepMining {
	
	// Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    

    /**
     * mod constructor
     */
    public DeepMining() {
    	
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    //setup event
    private void setup(final FMLCommonSetupEvent event)
    {
    	
    	oreGeneration();
    	
        // some preinit code
        //LOGGER.info("HELLO FROM PREINIT");
        //LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        
        
    }

	//client stuff gets done
    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        //LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    //send to another mod
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        //InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    //receive from another mod
    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        //LOGGER.info("Got IMC {}", event.getIMCStream().
          //      map(m->m.getMessageSupplier().get()).
            //    collect(Collectors.toList()));
    }
    
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        //LOGGER.info("HELLO from server starting");
    }
    
 // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
        	blockRegistryEvent.getRegistry().registerAll
        	(
        			BlockList.deepDepositCoal = new DeepDeposit( Block.Properties.create( Material.ROCK ).hardnessAndResistance( -1.0F, 18000000.0F ).sound( SoundType.STONE ),Blocks.COAL_ORE,2,600).setRegistryName("deep_deposit_coal"),
        			BlockList.deepDepositIron = new DeepDeposit( Block.Properties.create( Material.ROCK ).hardnessAndResistance( -1.0F, 18000000.0F ).sound( SoundType.STONE ),Blocks.IRON_ORE,2,1000).setRegistryName("deep_deposit_iron"),
        			BlockList.deepDepositGold = new DeepDeposit( Block.Properties.create( Material.ROCK ).hardnessAndResistance( -1.0F, 18000000.0F ).sound( SoundType.STONE ),Blocks.GOLD_ORE,1,1400).setRegistryName("deep_deposit_gold"),
        			BlockList.deepDepositLapis = new DeepDeposit( Block.Properties.create( Material.ROCK ).hardnessAndResistance( -1.0F, 18000000.0F ).sound( SoundType.STONE ),Blocks.LAPIS_ORE,1,1400).setRegistryName("deep_deposit_lapis"),
        			BlockList.deepDepositRedstone = new DeepDeposit( Block.Properties.create( Material.ROCK ).hardnessAndResistance( -1.0F, 18000000.0F ).sound( SoundType.STONE ),Blocks.REDSTONE_ORE,1,1000).setRegistryName("deep_deposit_redstone"),
        			BlockList.deepDepositDiamond = new DeepDeposit( Block.Properties.create( Material.ROCK ).hardnessAndResistance( -1.0F, 18000000.0F ).sound( SoundType.STONE ),Blocks.DIAMOND_ORE,1,3000).setRegistryName("deep_deposit_diamond")
        			);
        	
        	LOGGER.info("Blocks Registered");
        }
        
        @SubscribeEvent
		public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent)
		{
        	DeepMiningTab tab = new DeepMiningTab("deepmining");
        	itemRegistryEvent.getRegistry().registerAll
        	(
        			ItemList.deepDepositCoal = new BlockItem(BlockList.deepDepositCoal, new Item.Properties().group(tab).maxStackSize(64)).setRegistryName("deep_deposit_coal"),
        			ItemList.deepDepositIron = new BlockItem(BlockList.deepDepositIron, new Item.Properties().group(tab).maxStackSize(64)).setRegistryName("deep_deposit_iron"),
        			ItemList.deepDepositGold = new BlockItem(BlockList.deepDepositGold, new Item.Properties().group(tab).maxStackSize(64)).setRegistryName("deep_deposit_gold"),
        			ItemList.deepDepositLapis = new BlockItem(BlockList.deepDepositLapis, new Item.Properties().group(tab).maxStackSize(64)).setRegistryName("deep_deposit_lapis"),
        			ItemList.deepDepositRedstone = new BlockItem(BlockList.deepDepositRedstone, new Item.Properties().group(tab).maxStackSize(64)).setRegistryName("deep_deposit_redstone"),
        			ItemList.deepDepositDiamond = new BlockItem(BlockList.deepDepositDiamond, new Item.Properties().group(tab).maxStackSize(64)).setRegistryName("deep_deposit_diamond")
        	);
        	
        	LOGGER.info("Items Registered");
		}
        
        @SubscribeEvent
        public static void registerTE(@Nonnull final RegistryEvent.Register<TileEntityType<?>> evt) {
        	//create(TileEntity class, valid blocks for TileEntity
        	evt.getRegistry().register( TileEntityType.Builder.create(DepositTileEntity::new,BlockList.deepDepositCoal,
        			BlockList.deepDepositIron,BlockList.deepDepositGold,BlockList.deepDepositLapis,BlockList.deepDepositRedstone,
        			BlockList.deepDepositDiamond ).build( null ).setRegistryName( "deepmining", "deep_deposit" ) );
        	LOGGER.info("Tile Entitys Registered");
        }
		
    }
    
    private void oreGeneration() {
    	
    	ChanceRangeConfig chance = new ChanceRangeConfig(0.1f, 8, 4, 16);
    	int depositSize = 3;
		for(Biome biome : ForgeRegistries.BIOMES) {
			
			biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.deepDepositCoal.getDefaultState(), depositSize)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(chance)));
			biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.deepDepositIron.getDefaultState(), depositSize)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(chance)));

	    	biome.addFeature(
				GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
	                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.deepDepositGold.getDefaultState(), depositSize)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(chance)));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.deepDepositLapis.getDefaultState(), depositSize)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(chance)));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.deepDepositRedstone.getDefaultState(), depositSize)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(chance)));
	    	biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,Feature.ORE.func_225566_b_(
		                    new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.deepDepositDiamond.getDefaultState(), depositSize)).func_227228_a_( Placement.CHANCE_RANGE.func_227446_a_(chance)));
	    	
					
		}
		
	}

}
