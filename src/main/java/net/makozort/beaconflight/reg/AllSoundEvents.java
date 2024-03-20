package net.makozort.beaconflight.reg;

import net.makozort.beaconflight.BeaconFlight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BeaconFlight.MOD_ID);
    public static final RegistryObject<SoundEvent> DASH =
            registerSoundEvent("dash");



    public static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent((new ResourceLocation(BeaconFlight.MOD_ID, name))));

    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}