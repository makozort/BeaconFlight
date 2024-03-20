package net.makozort.beaconflight.datagen;


import net.makozort.beaconflight.BeaconFlight;
import net.makozort.beaconflight.loot.AddItemModifier;
import net.makozort.beaconflight.reg.AllEnchantments;
import net.makozort.beaconflight.reg.AllItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, BeaconFlight.MOD_ID);
    }

    @Override
    protected void start() {

        add("flight_ring_from_end", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, new ItemStack(AllItems.FLIGHT_RING,1)));

        add("dash_bast_1", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(.3f).build()}, EnchantedBookItem.createForEnchantment(new EnchantmentInstance(AllEnchantments.DASH.get(),1))));
        add("dash_bast_2", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(.3f).build()}, EnchantedBookItem.createForEnchantment(new EnchantmentInstance(AllEnchantments.DASH.get(),1))));
        add("dash_bast_3", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(.3f).build()}, EnchantedBookItem.createForEnchantment(new EnchantmentInstance(AllEnchantments.DASH.get(),1))));
        add("dash_bast_4", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_hoglin_stable")).build(),
                LootItemRandomChanceCondition.randomChance(.3f).build()}, EnchantedBookItem.createForEnchantment(new EnchantmentInstance(AllEnchantments.DASH.get(),1))));

    }
}
