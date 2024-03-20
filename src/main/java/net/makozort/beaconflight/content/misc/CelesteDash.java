package net.makozort.beaconflight.content.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.logging.Level;

public class CelesteDash {
    public static void dash(Player player) {
        Vec3 currentVelocity = player.getDeltaMovement();
        player.hurtMarked = true;
        Vec3 lookVector = player.getLookAngle();
        Vec3 dashVelocity = lookVector.scale(1);
        Vec3 newVelocity = currentVelocity.add(dashVelocity);
        player.setDeltaMovement(newVelocity);
    }

    public static void waveDash(Player player) {
        Vec3 currentVelocity = player.getDeltaMovement();
        player.hurtMarked = true;
        Vec3 lookVector = player.getLookAngle();
        Vec3 dashVelocity = lookVector.scale(2);
        Vec3 newVelocity = currentVelocity.add(dashVelocity);
        player.setDeltaMovement(newVelocity);
    }

}
