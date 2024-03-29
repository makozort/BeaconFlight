package makosmisc.content.effect;


import makosmisc.MakosMisc;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModEffects {
    public static DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MakosMisc.MOD_ID);
    public static final RegistryObject<MobEffect> FLIGHT = MOB_EFFECTS.register("flight",
            () -> new FlightEffect(MobEffectCategory.BENEFICIAL, 3124687));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }


}