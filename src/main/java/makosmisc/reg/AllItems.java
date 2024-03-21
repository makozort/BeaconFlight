<<<<<<<< Updated upstream:src/main/java/net/makozort/beaconflight/reg/AllItems.java
package net.makozort.beaconflight.reg;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.makozort.beaconflight.content.items.FlightRingItem;
import net.minecraft.world.item.Item;

import static net.makozort.beaconflight.ModRegistrate.REGISTRATE;
========
package makosmisc.reg;

import com.tterrag.registrate.util.entry.ItemEntry;
import makosmisc.content.item.FlightRingItem;
import net.minecraft.world.item.Item;

import static makosmisc.reg.ModRegistrate.REGISTRATE;
>>>>>>>> Stashed changes:src/main/java/makosmisc/reg/AllItems.java

public class AllItems {
    public static final ItemEntry<FlightRingItem> FLIGHT_RING = REGISTRATE.item("flight_ring",
                    p -> new FlightRingItem(props().fireResistant().stacksTo(1)))
            .register();

    private static Item.Properties props() {
        return new Item.Properties();
    }

    public static void register() {
    }
}
