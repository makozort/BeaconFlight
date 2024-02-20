package net.makozort.beaconflight.content.entity.client;

import net.makozort.beaconflight.BeaconFlight;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation TEST_LAYER = new ModelLayerLocation(
            new ResourceLocation(BeaconFlight.MOD_ID, "test_layer"), "main");
}
