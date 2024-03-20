package net.makozort.beaconflight.networking;

import net.makozort.beaconflight.BeaconFlight;
import net.makozort.beaconflight.networking.packet.CombatPacket;
import net.makozort.beaconflight.networking.packet.DashPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPackets {

    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(BeaconFlight.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(DashPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DashPacket::new)
                .encoder(DashPacket::toBytes)
                .consumerMainThread(DashPacket::handle)
                .add();

        net.messageBuilder(CombatPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CombatPacket::new)
                .encoder(CombatPacket::toBytes)
                .consumerMainThread(CombatPacket::handle)
                .add();


    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToPlayerAndTackers(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), message);
    }
}
