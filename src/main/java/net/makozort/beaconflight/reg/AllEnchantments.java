package net.makozort.beaconflight.reg;

import net.makozort.beaconflight.BeaconFlight;
import net.makozort.beaconflight.content.enchantment.DashEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllEnchantments {


    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, BeaconFlight.MOD_ID);

    public static RegistryObject<Enchantment> DASH =
            ENCHANTMENTS.register("dash",
                    () -> new DashEnchantment(Enchantment.Rarity.VERY_RARE,
                            EnchantmentCategory.ARMOR, EquipmentSlot.FEET));
    public static RegistryObject<Enchantment> SEETHROUGH =
            ENCHANTMENTS.register("see_through",
                    () -> new DashEnchantment(Enchantment.Rarity.COMMON,
                            EnchantmentCategory.ARMOR,EquipmentSlot.HEAD,EquipmentSlot.CHEST,EquipmentSlot.LEGS, EquipmentSlot.FEET));
    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
