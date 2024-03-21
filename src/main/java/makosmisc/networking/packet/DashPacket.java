package makosmisc.networking.packet;

import makosmisc.content.capability.dash.CanDashProvider;
import makosmisc.content.misc.CelesteDash;
import makosmisc.reg.AllEnchantments;
import makosmisc.reg.AllSoundEvents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DashPacket {

    public DashPacket() {
    }

    public DashPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();
            if (EnchantmentHelper.getItemEnchantmentLevel(AllEnchantments.DASH.get(), player.getInventory().armor.get(0)) > 0 && !player.isPassenger()
                    && (player.gameMode.getGameModeForPlayer() == GameType.ADVENTURE || player.gameMode.getGameModeForPlayer() == GameType.SURVIVAL)) {
                    if (level.getBlockState(player.getOnPos()) == Blocks.AIR.defaultBlockState()) {
                        player.getCapability(CanDashProvider.DASH_CAP).ifPresent(dash -> {
                            if (dash.canDash()) {
                                dash.setDash(false);
                                dash.setWaveDash();
                                CelesteDash.dash(player);
                                player.level().playSound(null, player.getOnPos(), AllSoundEvents.DASH.get(), SoundSource.MASTER, 1, 1F);
                            }
                        });
                    }
            }
        });
        return true;
    }
}
