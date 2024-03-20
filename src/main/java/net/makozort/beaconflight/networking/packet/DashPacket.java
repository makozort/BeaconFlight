package net.makozort.beaconflight.networking.packet;

import net.makozort.beaconflight.content.capabilities.dash.CanDashProvider;
import net.makozort.beaconflight.content.misc.CelesteDash;
import net.makozort.beaconflight.reg.AllEnchantments;
import net.makozort.beaconflight.reg.AllSoundEvents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DashPacket {
    Boolean waveDash;

    public DashPacket(Boolean waveDash) {
        this.waveDash = waveDash;
    }

    public DashPacket(FriendlyByteBuf buf) {
        this.waveDash = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.waveDash);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();
            if (EnchantmentHelper.getItemEnchantmentLevel(AllEnchantments.DASH.get(), player.getInventory().armor.get(0)) > 0 && !player.isPassenger()
                    && (player.gameMode.getGameModeForPlayer() == GameType.ADVENTURE || player.gameMode.getGameModeForPlayer() == GameType.SURVIVAL)) {
                if (!this.waveDash) {
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
                } else {
                    if (level.getBlockState(player.getOnPos()) != Blocks.AIR.defaultBlockState()) {
                        player.getCapability(CanDashProvider.DASH_CAP).ifPresent(dash -> {
                            if (dash.canWaveDash()) {
                                dash.stopWaveDash();
                                CelesteDash.waveDash(player);
                                player.level().playSound(null, player.getOnPos(), SoundEvents.NOTE_BLOCK_CHIME.get(), SoundSource.MASTER, 1, 1F);
                            }
                        });
                    }
                }
            }
        });
        return true;
    }
}
