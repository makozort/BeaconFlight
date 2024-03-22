package makosmisc.event.common;
import makosmisc.MakosMisc;
import makosmisc.content.capability.dash.CanDash;
import makosmisc.content.capability.dash.CanDashProvider;
import makosmisc.content.effect.ModEffects;
import makosmisc.content.misc.CelesteDash;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = MakosMisc.MOD_ID)
public class ModEvents {
        @SubscribeEvent
        public static void onLiving(LivingEvent.LivingTickEvent event) {
            Entity entity = event.getEntity();
            if (entity.level() instanceof ServerLevel serverLevel) {
                if (entity instanceof Player player) {
                    player.getCapability(CanDashProvider.DASH_CAP).ifPresent(dash -> {
                        if (!dash.canDash()) {
                            serverLevel.sendParticles(ParticleTypes.END_ROD, player.getOnPos().getCenter().x, player.getOnPos().getCenter().y, player.getOnPos().getCenter().z, 1, 0, 0, 0, 0);
                            if (player.isInFluidType() || player.onGround()) {
                                dash.setDash(true);
                            }
                        }
                    });
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
        public static void onJump(LivingEvent.LivingJumpEvent event) {
            if (event.getEntity() instanceof Player player) {
                if (player.onGround()) {
                    player.getCapability(CanDashProvider.DASH_CAP).ifPresent(dash -> {
                        if (dash.canWaveDash()) {
                            dash.stopWaveDash();
                            CelesteDash.waveDash(player);
                        }
                    });
                }
            }
        }

        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                event.addCapability(new ResourceLocation(MakosMisc.MOD_ID, "dash"), new CanDashProvider());
            }
        }


        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(CanDash.class);

        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if (event.side == LogicalSide.SERVER) {
                event.player.getCapability(CanDashProvider.DASH_CAP).ifPresent(dash -> {
                    if (dash.canWaveDash()) {
                        dash.minWaveDash();
                    }
                });
            }
        }
}


