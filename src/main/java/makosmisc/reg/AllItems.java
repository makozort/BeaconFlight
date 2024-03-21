package makosmisc.reg;
import com.tterrag.registrate.util.entry.ItemEntry;
import makosmisc.content.item.FlightRingItem;
import net.minecraft.world.item.Item;
import static makosmisc.reg.ModRegistrate.REGISTRATE;


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
