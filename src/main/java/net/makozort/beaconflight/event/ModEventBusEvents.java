package net.makozort.beaconflight.event;

import net.makozort.beaconflight.BeaconFlight;
import net.makozort.beaconflight.content.entity.ModEntities;
import net.makozort.beaconflight.content.entity.custom.TestEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid = BeaconFlight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TEST.get(), TestEntity.createAttributes().build());
    }
}
