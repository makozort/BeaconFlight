package makosmisc.content.effect;
import makosmisc.reg.AllItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import top.theillusivec4.curios.api.CuriosApi;

public class FlightEffect extends MobEffect {
    public FlightEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof Player player) {
            if (!player.getAbilities().mayfly) {
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();
            }
        }
    }
    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (pLivingEntity instanceof Player player &&
                !CuriosApi.getCuriosInventory(player).map(inv -> inv.findFirstCurio(AllItems.FLIGHT_RING.asItem()).isPresent()).orElse(false) && player.getMainHandItem().getItem()
                != AllItems.FLIGHT_RING.asItem() && player.getOffhandItem().getItem()
                != AllItems.FLIGHT_RING.asItem()) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 3));
        }
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}

