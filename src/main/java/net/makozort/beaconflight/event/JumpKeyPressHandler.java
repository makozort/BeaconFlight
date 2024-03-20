package net.makozort.beaconflight.event;

import net.makozort.beaconflight.BeaconFlight;
import net.makozort.beaconflight.networking.ModPackets;
import net.makozort.beaconflight.networking.packet.CombatPacket;
import net.makozort.beaconflight.networking.packet.DashPacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;


@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = BeaconFlight.MOD_ID)
public class JumpKeyPressHandler {

    private static final long DOUBLE_PRESS_INTERVAL = 300;
    private static long lastJumpKeyPressTime = 0;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        int jumpKeyCode = Minecraft.getInstance().options.keyJump.getKey().getValue();

        if (event.getKey() == jumpKeyCode && event.getAction() == GLFW.GLFW_PRESS) {
            long currentTime = System.currentTimeMillis();
            long timeSinceLastPress = currentTime - lastJumpKeyPressTime;

            if (timeSinceLastPress <= DOUBLE_PRESS_INTERVAL) {
                ModPackets.sendToServer(new DashPacket(false));
                lastJumpKeyPressTime = 0;
            } else {
                ModPackets.sendToServer(new DashPacket(true));
                lastJumpKeyPressTime = currentTime;
            }
        }
    }
}
