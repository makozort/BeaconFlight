package net.makozort.beaconflight.content.entity;

import net.makozort.beaconflight.BeaconFlight;
import net.makozort.beaconflight.content.entity.custom.TestEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.text.html.parser.Entity;

public class ModEntities {


    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BeaconFlight.MOD_ID);



    public static final RegistryObject<EntityType<TestEntity>> TEST =
            ENTITY_TYPES.register("test", () -> EntityType.Builder.of(TestEntity::new, MobCategory.AMBIENT).sized(2.0F,2.0F).build("test"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }


}
