package makosmisc.datagen;


import makosmisc.MakosMisc;
import makosmisc.reg.AllItems;
import makosmisc.loot.AddItemModifier;
import makosmisc.reg.AllEnchantments;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, MakosMisc.MOD_ID);
    }

    @Override
    protected void start() {

        add("flight_ring_from_end", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, new ItemStack(AllItems.FLIGHT_RING,1)));

        add("dash_bast_1", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(.45f).build()}, EnchantedBookItem.createForEnchantment(new EnchantmentInstance(AllEnchantments.DASH.get(),1))));
        add("dash_bast_2", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(.45f).build()}, EnchantedBookItem.createForEnchantment(new EnchantmentInstance(AllEnchantments.DASH.get(),1))));
        add("dash_bast_3", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(.45f).build()}, EnchantedBookItem.createForEnchantment(new EnchantmentInstance(AllEnchantments.DASH.get(),1))));
        add("dash_bast_4", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_hoglin_stable")).build(),
                LootItemRandomChanceCondition.randomChance(.45f).build()}, EnchantedBookItem.createForEnchantment(new EnchantmentInstance(AllEnchantments.DASH.get(),1))));

        add("hidden_bast_1", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(.45f).build()}, EnchantedBookItem.createForEnchantment(new EnchantmentInstance(AllEnchantments.HIDDEN.get(),1))));
        add("hidden_bast_2", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(.45f).build()}, EnchantedBookItem.createForEnchantment(new EnchantmentInstance(AllEnchantments.HIDDEN.get(),1))));
        add("hidden_bast_3", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(.45f).build()}, EnchantedBookItem.createForEnchantment(new EnchantmentInstance(AllEnchantments.HIDDEN.get(),1))));
        add("hidden_bast_4", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_hoglin_stable")).build(),
                LootItemRandomChanceCondition.randomChance(.45f).build()}, EnchantedBookItem.createForEnchantment(new EnchantmentInstance(AllEnchantments.HIDDEN.get(),1))));

    }
}
