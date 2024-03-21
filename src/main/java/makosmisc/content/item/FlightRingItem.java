<<<<<<<< Updated upstream:src/main/java/net/makozort/beaconflight/content/items/FlightRingItem.java
package net.makozort.beaconflight.content.items;
========
package makosmisc.content.item;
>>>>>>>> Stashed changes:src/main/java/makosmisc/content/item/FlightRingItem.java

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlightRingItem extends Item {
    public FlightRingItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.makosmisc.flightring.tooltip").withStyle(ChatFormatting.DARK_PURPLE));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
