package me.hackathonPlugins.hackathonPlugin;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class BigFloppa implements Listener {

    public ItemStack bigFloppaItem;

    public BigFloppa(HackathonPlugin plugin) {

        NamespacedKey recipeKey = new NamespacedKey(plugin, "floppaRecipe");
        ShapelessRecipe floppaRecipe = new ShapelessRecipe(recipeKey, this.createBigFloppa());
        floppaRecipe.addIngredient(Material.SALMON);
        floppaRecipe.addIngredient(Material.DIAMOND);
        floppaRecipe.addIngredient(Material.DIAMOND);
        plugin.getServer().addRecipe(floppaRecipe);
        this.bigFloppaItem = this.createBigFloppa();

    }

    public ItemStack createBigFloppa() {

        ItemStack bf = new ItemStack(Material.SALMON, 1);

        ItemMeta im = bf.getItemMeta();

        im.setDisplayName("Big Floppa'" );
        bf.setItemMeta(im);

        return bf;

    }

    public void giveBigFloppa(Player player) {

        player.getInventory().addItem(new ItemStack[]{this.createBigFloppa()});

    }

    @EventHandler
    public void handlePlayerCraft(CraftItemEvent event) {

        ItemStack i = event.getCurrentItem();
        ItemMeta im = i.getItemMeta();
        if (im.getDisplayName().equals(this.bigFloppaItem.getItemMeta().getDisplayName())) {

            HumanEntity ent = event.getWhoClicked();
            ent.sendMessage("Big Floppa' time");

        }

    }

}




