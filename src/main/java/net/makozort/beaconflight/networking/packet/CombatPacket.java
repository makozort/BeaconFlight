package net.makozort.beaconflight.networking.packet;

import net.makozort.beaconflight.content.capabilities.combatTracker.InCombatProvider;
import net.makozort.beaconflight.content.capabilities.dash.CanDashProvider;
import net.makozort.beaconflight.content.misc.CelesteDash;
import net.makozort.beaconflight.reg.AllEnchantments;
import net.makozort.beaconflight.reg.AllSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CombatPacket {
    Boolean inCombat;

    public CombatPacket(Boolean inCombat) {
        this.inCombat = inCombat;
    }

    public CombatPacket(FriendlyByteBuf buf) {
        this.inCombat = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.inCombat);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
           Player player = Minecraft.getInstance().player;

            player.getCapability(InCombatProvider.COMBAT_CAP).ifPresent(combat -> {
                combat.setInCombat(this.inCombat);
            });
        });
        return true;
    }
}
