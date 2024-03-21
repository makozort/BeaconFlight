package makosmisc.content.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class DashEnchantment extends Enchantment {
    public DashEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }


    public int getMinCost(int pLevel) {
        return 1 + pLevel * 10;
    }

    public int getMaxCost(int pLevel) {
        return this.getMinCost(pLevel) + 5;
    }


    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}
