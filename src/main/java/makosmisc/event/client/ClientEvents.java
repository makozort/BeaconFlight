package makosmisc.event.client;

import makosmisc.MakosMisc;
import makosmisc.networking.packet.DashPacket;
import makosmisc.networking.ModPackets;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;


@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MakosMisc.MOD_ID)
public class ClientEvents {

    private static final long DOUBLE_PRESS_INTERVAL = 300;
    private static long lastJumpKeyPressTime = 0;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        int jumpKeyCode = Minecraft.getInstance().options.keyJump.getKey().getValue();

        if (event.getKey() == jumpKeyCode && event.getAction() == GLFW.GLFW_PRESS) {
            long currentTime = System.currentTimeMillis();
            long timeSinceLastPress = currentTime - lastJumpKeyPressTime;

            if (timeSinceLastPress <= DOUBLE_PRESS_INTERVAL) {
                ModPackets.sendToServer(new DashPacket());
                lastJumpKeyPressTime = 0;
            } else {
                lastJumpKeyPressTime = currentTime;
            }
        }
    }
}
