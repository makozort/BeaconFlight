package net.makozort.beaconflight.event;

import net.makozort.beaconflight.BeaconFlight;
import net.makozort.beaconflight.effect.ModEffects;
import net.makozort.beaconflight.reg.AllItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class ModEvents {
    @Mod.EventBusSubscriber(modid = BeaconFlight.MOD_ID)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onLootTableLoad(LootTableLoadEvent event) {
            if (event.getName().equals(BuiltInLootTables.END_CITY_TREASURE)) {
                LootPool.Builder builder = LootPool.lootPool()
                        .name("RING_POOL")
                        .setRolls(UniformGenerator.between(2.0f, 2.0f))  // How many times this loot pool will roll (1 means it will roll once)
                        .add(LootItem.lootTableItem(AllItems.FLIGHT_RING.get()) // Pass your Custom Item here
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 1.0f)))); // Adjust the count of the item (0 means none)

                event.getTable().addPool(builder.build());
            }
        }
        @SubscribeEvent
        public static void onLiving(LivingEvent.LivingTickEvent event) {
            Entity entity = event.getEntity();
            if (entity.getLevel() instanceof ServerLevel) {
                if (entity instanceof Player player) {
                    if (!(player.isCreative() || player.isSpectator()))  {
                        if (player.getAbilities().mayfly && (!player.hasEffect(ModEffects.FLIGHT.get()))) {
                            player.getAbilities().flying = false;
                            player.getAbilities().mayfly = false;
                            player.onUpdateAbilities();
                        }
                    }
                }
            }
        }
    }
}
