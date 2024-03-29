
package makosmisc.mixin.common;
import makosmisc.content.effect.ModEffects;
import makosmisc.reg.AllItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.Nullable;
import java.util.List;


@Mixin(BeaconBlockEntity.class)
public abstract class BeaconMixin extends BlockEntity {
    public BeaconMixin(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Debug(export = true, print = true)
    @Inject(method = "applyEffects", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void onApplyMaxTierEffects(Level pLevel, BlockPos pPos, int pLevels,
                                              @Nullable MobEffect pPrimary, @Nullable MobEffect pSecondary,
                                              CallbackInfo ci, double d0, int i, int j, AABB aabb, List<Player> list) {
        for (Player player : list) {
            if (CuriosApi.getCuriosInventory(player).map(inv -> inv.findFirstCurio(AllItems.FLIGHT_RING.asItem()).isPresent()).orElse(false)
                    || player.getMainHandItem().getItem() == AllItems.FLIGHT_RING.asItem()
                    || player.getOffhandItem().getItem() == AllItems.FLIGHT_RING.asItem()) {
                player.addEffect(new MobEffectInstance(ModEffects.FLIGHT.get(), j, 0, true, false));
            }
        }
    }
}
