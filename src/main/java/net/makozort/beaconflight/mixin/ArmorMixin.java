package net.makozort.beaconflight.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.makozort.beaconflight.BeaconFlight;
import net.makozort.beaconflight.content.capabilities.combatTracker.InCombatProvider;
import net.makozort.beaconflight.effect.ModEffects;
import net.makozort.beaconflight.reg.AllEnchantments;
import net.makozort.beaconflight.reg.AllItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.Nullable;
import java.util.List;


@Mixin(HumanoidArmorLayer.class)
public abstract class ArmorMixin <T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {


    public ArmorMixin(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @Inject(method = "renderArmorPiece", at = @At(value = "HEAD"),cancellable = true)
    private void renderArmorPiece(PoseStack pPoseStack, MultiBufferSource pBuffer, T pLivingEntity, EquipmentSlot pSlot, int pPackedLight, A pModel, CallbackInfo ci) {
        if (pLivingEntity instanceof Player player) {
            if (EnchantmentHelper.getItemEnchantmentLevel(AllEnchantments.SEETHROUGH.get(), player.getInventory().armor.get(pSlot.getIndex())) > 0) {
                player.getCapability(InCombatProvider.COMBAT_CAP).ifPresent(combat -> {
                    if (!combat.getInCombat()) {
                        ci.cancel();
                    }
                });
            }
        }
    }
}
