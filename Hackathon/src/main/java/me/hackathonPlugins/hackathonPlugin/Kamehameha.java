package me.hackathonPlugins.hackathonPlugin;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class Kamehameha implements CommandExecutor, Listener {

    public ItemStack khhItem;

    public Kamehameha(HackathonPlugin plugin) {

        NamespacedKey recipeKey = new NamespacedKey(plugin, "khhRecipe");
        ShapelessRecipe khhRecipe = new ShapelessRecipe(recipeKey, this.createKhh());
        khhRecipe.addIngredient(Material.DIAMOND);
        plugin.getServer().addRecipe(khhRecipe);

        plugin.getCommand("kamehameha").setExecutor(this);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.khhItem = this.createKhh();

    }

    public ItemStack createKhh() {

        ItemStack khh = new ItemStack(Material.DIAMOND, 1);
        ItemMeta im = khh.getItemMeta();

        String blue = String.valueOf(ChatColor.BLUE);
        im.setDisplayName(blue + String.valueOf(ChatColor.BOLD) + "Kamehameha" );
        khh.setItemMeta(im);

        return khh;

    }

    public void giveKhh(Player player) {

        player.getInventory().addItem(new ItemStack[]{this.createKhh()});

    }

    @EventHandler
    public void handlePlayerCraft(CraftItemEvent event) {

        ItemStack i = event.getCurrentItem();
        ItemMeta im = i.getItemMeta();
        if (im.getDisplayName().equals(this.khhItem.getItemMeta())) {

            HumanEntity ent = event.getWhoClicked();
            String blue = String.valueOf(ChatColor.BLUE);

            ent.sendMessage(blue + String.valueOf(ChatColor.ITALIC) + "AWAKEN, " + "Legendary Warrior...");

        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            String cmd = command.getName();
            if (cmd.equals("kamehameha")) {
                Player player = (Player) sender;
                this.giveKhh(player);
                String blue = String.valueOf(ChatColor.BLUE);
                player.sendMessage(blue + String.valueOf(ChatColor.ITALIC) + "AWAKEN, " + "Legendary Warrior...");

            }

        }

        return false;

    }

    @EventHandler
    public void blast(PlayerInteractEvent event) {

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();

            if (hand == null) {

                return;

            }

            ItemMeta im = hand.getItemMeta();
            if (im.getDisplayName().equals(this.khhItem.getItemMeta().getDisplayName())) {

                if (event.getPlayer().getCooldown(Material.DIAMOND) == 0) {

                    event.getPlayer().setCooldown(Material.DIAMOND, 100);
                    LargeFireball fireball = event.getPlayer().launchProjectile(LargeFireball.class);

                }

            }

        }

    }

}
