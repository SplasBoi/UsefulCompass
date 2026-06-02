package splasboi.useful_compass.client.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import splasboi.useful_compass.UsefulCompass;

public class PlayerInventoryUtil {
    public static boolean hasItem(Player player, Item item) {
        if (player == null) {
            return false;
        }

        if (hasItemInCursorStack(player, item)) {
            return true;
        }

        return player.getInventory().contains(item.getDefaultInstance());
    }

    public static boolean hasItemInCursorStack(Player player, Item item) {
        ItemStack cursorStack = player.containerMenu.getCarried();

        if (cursorStack.isEmpty()) {
            return false;
        }

        return cursorStack.getItem() == item;
    }
}
