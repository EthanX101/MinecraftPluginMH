package me.hackathonPlugins.hackathonPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SukunaDomain implements CommandExecutor {

    public SukunaDomain(HackathonPlugin plugin) {
        plugin.getCommand("sukunashrine").setExecutor(this);
    }

    public ItemStack createDomain() {
        ItemStack shrine = new ItemStack(Material.NETHER_STAR,1);



        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Malevolent Shrine");

        ItemMeta im = shrine.getItemMeta();
        im.setItemName("Domain Expansion");
        im.setLore(lore);
        im.setRarity(ItemRarity.EPIC);
        im.setEnchantmentGlintOverride(true);

        shrine.setItemMeta(im);

        return shrine;
    }

    public void giveShrine(Player player) {
        player.getInventory().addItem(createDomain());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            String cmd = command.getName();

            if (cmd.equals("sukunashrine")) {
                Player player = (Player)sender;
                giveShrine(player);
            }
        }
        return false;
    }
}
