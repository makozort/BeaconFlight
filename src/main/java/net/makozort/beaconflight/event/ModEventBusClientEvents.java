package net.makozort.beaconflight.event;

import net.makozort.beaconflight.BeaconFlight;
import net.makozort.beaconflight.content.entity.client.CustomModel;
import net.makozort.beaconflight.content.entity.client.ModModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BeaconFlight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ModEventBusClientEvents {


    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.TEST_LAYER, CustomModel::createBodyLayer);
    }

}
