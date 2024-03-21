<<<<<<<< Updated upstream:src/main/java/net/makozort/beaconflight/BeaconFlight.java
package net.makozort.beaconflight;

import com.mojang.logging.LogUtils;

import net.makozort.beaconflight.effect.ModEffects;
import net.makozort.beaconflight.reg.AllItems;
========
package makosmisc;

import com.mojang.logging.LogUtils;

import makosmisc.content.effect.ModEffects;
import makosmisc.loot.ModLootModifiers;
import makosmisc.networking.ModPackets;
import makosmisc.reg.AllItems;
import makosmisc.reg.AllSoundEvents;
import makosmisc.reg.AllEnchantments;
>>>>>>>> Stashed changes:src/main/java/makosmisc/MakosMisc.java
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MakosMisc.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MakosMisc {
    public static final String MOD_ID = "makosmisc";
    public static final Logger LOGGER = LogUtils.getLogger();
<<<<<<<< Updated upstream:src/main/java/net/makozort/beaconflight/BeaconFlight.java


    public BeaconFlight() {
========
    public MakosMisc() {
>>>>>>>> Stashed changes:src/main/java/makosmisc/MakosMisc.java
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEffects.register(modEventBus);
        AllItems.register();
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }



}
