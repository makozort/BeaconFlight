package makosmisc.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import makosmisc.MakosMisc;
import makosmisc.reg.AllEnchantments;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(HumanoidArmorLayer.class)
public abstract class ArmorMixin <T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {


    public ArmorMixin(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @Debug(export = true, print = true)
    @Inject(method = "renderArmorPiece", at = @At(value = "HEAD"),cancellable = true)
    private void renderArmorPiece(PoseStack pPoseStack, MultiBufferSource pBuffer, T pLivingEntity, EquipmentSlot pSlot, int pPackedLight, A pModel, CallbackInfo ci) {
        if (pLivingEntity instanceof Player player) {
            if (EnchantmentHelper.getItemEnchantmentLevel(AllEnchantments.HIDDEN.get(), player.getInventory().armor.get(pSlot.getIndex())) > 0 && player.getHealth() == player.getMaxHealth()) {
                ci.cancel();
            }
        }
    }
}
