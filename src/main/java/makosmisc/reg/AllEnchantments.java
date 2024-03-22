package makosmisc.reg;

import makosmisc.MakosMisc;
import makosmisc.content.enchantment.DashEnchantment;
import makosmisc.content.enchantment.HiddenEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllEnchantments {


    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MakosMisc.MOD_ID);

    public static RegistryObject<Enchantment> DASH =
            ENCHANTMENTS.register("dash",
                    () -> new DashEnchantment(Enchantment.Rarity.VERY_RARE,
                            EnchantmentCategory.ARMOR, EquipmentSlot.FEET));
    public static RegistryObject<Enchantment> HIDDEN =
            ENCHANTMENTS.register("hidden",
                    () -> new HiddenEnchantment(Enchantment.Rarity.COMMON,
                            EnchantmentCategory.ARMOR,EquipmentSlot.HEAD,EquipmentSlot.CHEST,EquipmentSlot.LEGS, EquipmentSlot.FEET));
    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
