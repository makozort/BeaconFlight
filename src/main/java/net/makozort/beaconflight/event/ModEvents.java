package net.makozort.beaconflight.event;

import net.makozort.beaconflight.BeaconFlight;
import net.makozort.beaconflight.content.capabilities.combatTracker.InCombatProvider;
import net.makozort.beaconflight.content.capabilities.combatTracker.InCombat;
import net.makozort.beaconflight.content.capabilities.dash.CanDash;
import net.makozort.beaconflight.content.capabilities.dash.CanDashProvider;
import net.makozort.beaconflight.content.enchantment.DashEnchantment;
import net.makozort.beaconflight.content.enchantment.SeeThroughEnchantment;
import net.makozort.beaconflight.effect.ModEffects;
import net.makozort.beaconflight.networking.ModPackets;
import net.makozort.beaconflight.networking.packet.CombatPacket;
import net.makozort.beaconflight.reg.AllEnchantments;
import net.makozort.beaconflight.reg.AllItems;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;


public class ModEvents {
    @Mod.EventBusSubscriber(modid = BeaconFlight.MOD_ID)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onLiving(LivingEvent.LivingTickEvent event) {
            Entity entity = event.getEntity();
            if (entity.level() instanceof ServerLevel serverLevel) {
                if (entity instanceof Player player) {
                    if (!(player.isCreative() || player.isSpectator())) {
                        if (player.getAbilities().mayfly && (!player.hasEffect(ModEffects.FLIGHT.get()))) {
                            player.getAbilities().flying = false;
                            player.getAbilities().mayfly = false;
                            player.onUpdateAbilities();
                        }
                        player.getCapability(CanDashProvider.DASH_CAP).ifPresent(dash -> {
                            if (!dash.canDash()) {
                                serverLevel.sendParticles(ParticleTypes.END_ROD, player.getOnPos().getCenter().x, player.getOnPos().getCenter().y, player.getOnPos().getCenter().z, 1, 0, 0, 0, 0);
                                if (player.isInFluidType()) {
                                    dash.setDash(true);
                                }
                            }
                        });
                    }
                }
            }
        }
        @SubscribeEvent
        public static void onFall(LivingFallEvent event) {
            if (event.getEntity() instanceof  Player player) {
                player.getCapability(CanDashProvider.DASH_CAP).ifPresent(dash -> {
                        if (!dash.canDash()) {
                            event.setCanceled(true);
                            dash.setDash(true);
                        }
                });
            }
        }
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                    event.addCapability(new ResourceLocation(BeaconFlight.MOD_ID, "dash"), new CanDashProvider());
                    event.addCapability(new ResourceLocation(BeaconFlight.MOD_ID, "combat"), new InCombatProvider());
            } else if (event.getObject().level() instanceof ClientLevel) {
                event.addCapability(new ResourceLocation(BeaconFlight.MOD_ID, "combat"), new InCombatProvider());
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if (event.isWasDeath()) {
                event.getOriginal().getCapability(CanDashProvider.DASH_CAP).ifPresent(oldStore -> {
                    event.getEntity().getCapability(CanDashProvider.DASH_CAP).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(InCombatProvider.COMBAT_CAP).ifPresent(oldStore -> {
                    event.getEntity().getCapability(InCombatProvider.COMBAT_CAP).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(CanDash.class);
            event.register(InCombat.class);
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if (event.side == LogicalSide.SERVER) {
                event.player.getCapability(InCombatProvider.COMBAT_CAP).ifPresent(combat -> {
                    if (combat.checkCombat()) {
                        if (combat.getCombat() -1 == 0)
                            if (event.player instanceof  ServerPlayer player)
                                ModPackets.sendToPlayerAndTackers(new CombatPacket(false),player);
                        combat.minCombat();
                    }
                });
                event.player.getCapability(CanDashProvider.DASH_CAP).ifPresent(dash -> {
                    if (dash.canWaveDash()) {
                        dash.minWaveDash();
                    }
                });
            }
        }

        @SubscribeEvent
        public static void hurtEvent(LivingHurtEvent event) {
            if (event.getEntity() instanceof Player player) {
                player.getCapability(InCombatProvider.COMBAT_CAP).ifPresent(InCombat::setCombatTimer);
            }
            if (event.getEntity() instanceof ServerPlayer player) {
                ModPackets.sendToPlayerAndTackers(new CombatPacket(true),player);
            }
        }


        @SubscribeEvent
        public static void trackEvent(PlayerEvent.StartTracking event) {
            if (event.getEntity() instanceof  ServerPlayer player) {
                event.getTarget().getCapability(InCombatProvider.COMBAT_CAP).ifPresent(combat -> {
                    if (combat.checkCombat()) {
                            ModPackets.sendToPlayerAndTackers(new CombatPacket(combat.getInCombat()),player);
                        combat.minCombat();
                    }
                });
            }
        }
    }
}

