
package makosmisc;

import com.mojang.logging.LogUtils;

import makosmisc.content.effect.ModEffects;
import makosmisc.networking.ModPackets;
import makosmisc.reg.AllEnchantments;
import makosmisc.reg.AllItems;
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





    public MakosMisc() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEffects.register(modEventBus);
        AllItems.register();
        AllEnchantments.register(modEventBus);
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ModPackets::register);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }



}
