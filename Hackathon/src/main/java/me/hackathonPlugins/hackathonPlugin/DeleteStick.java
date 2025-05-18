package me.hackathonPlugins.hackathonPlugin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DeleteStick implements CommandExecutor {

    public DeleteStick(HackathonPlugin plugin) {
        plugin.getCommand("deletestick").setExecutor(this);
    }

    public ItemStack createStick() {
        ItemStack stick = new ItemStack(Material.STICK,1);


        ArrayList<String> coords = new ArrayList<String>();

        ItemMeta im = stick.getItemMeta();
        im.setItemName("Clear Stick");
        im.setLore(coords);
        im.setRarity(ItemRarity.EPIC);
        im.setEnchantmentGlintOverride(true);

        stick.setItemMeta(im);

        return stick;
    }

    public void giveStick(Player player) {

        player.getInventory().addItem(createStick());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            String cmd = command.getName();

            if (cmd.equals("deletestick")) {
                Player player = (Player)sender;
                giveStick(player);
            }
        }
        return false;
    }
}
