package net.makozort.beaconflight;

import com.mojang.logging.LogUtils;

import net.makozort.beaconflight.effect.ModEffects;
import net.makozort.beaconflight.loot.ModLootModifiers;
import net.makozort.beaconflight.networking.ModPackets;
import net.makozort.beaconflight.reg.AllItems;
import net.makozort.beaconflight.reg.AllSoundEvents;
import net.makozort.beaconflight.reg.AllEnchantments;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(BeaconFlight.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BeaconFlight {
    public static final String MOD_ID = "beaconflight";
    public static final Logger LOGGER = LogUtils.getLogger();


    public BeaconFlight() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEffects.register(modEventBus);
        AllItems.register();
        AllSoundEvents.register(modEventBus);
        AllEnchantments.register(modEventBus);
        ModLootModifiers.register(modEventBus);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {

    }
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModPackets.register();
        });
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }



}
