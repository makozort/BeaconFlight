package makosmisc.content.misc;

import makosmisc.reg.AllSoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class CelesteDash {
    public static void dash(Player player) {
            player.level().playSound(null, player.getOnPos(), AllSoundEvents.DASH.get(), SoundSource.MASTER, 1, 1F);
            Vec3 currentVelocity = player.getDeltaMovement();
            player.hurtMarked = true;
            Vec3 lookVector = player.getLookAngle();
            Vec3 dashVelocity = lookVector.scale(1);
            Vec3 newVelocity = currentVelocity.add(dashVelocity);
            player.setDeltaMovement(newVelocity);
    }

    public static void waveDash(Player player) {
            player.level().playSound(null, player.getOnPos(), SoundEvents.NOTE_BLOCK_CHIME.get(), SoundSource.MASTER, 1, 1F);
            Vec3 currentVelocity = player.getDeltaMovement();
            player.hurtMarked = true;
            Vec3 lookVector = player.getLookAngle();
            Vec3 dashVelocity = lookVector.scale(1.25);
            Vec3 newVelocity = currentVelocity.add(dashVelocity);
            player.setDeltaMovement(newVelocity);
    }

}
