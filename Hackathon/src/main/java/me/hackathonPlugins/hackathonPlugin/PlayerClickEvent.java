package me.hackathonPlugins.hackathonPlugin;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class PlayerClickEvent implements Listener {
    World shrineworld = Bukkit.getWorlds().get(0);
    BukkitScheduler bukS = Bukkit.getScheduler();
    HackathonPlugin plugin = HackathonPlugin.getPlug();

    @EventHandler
    public void playerClicked(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // CHECKS IF IT WAS A RIGHT CLICK
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack item = event.getItem();

            if (item!= null) {
                // CHECKS IF IT IS SUKUNA DOMAIN
                if (item.getItemMeta().getItemName().equals("Domain Expansion") && item.getType().equals(Material.NETHER_STAR)) {
                    Location playerLocation = player.getLocation();
                    Vector lookDir = playerLocation.getDirection().multiply(15);
                    Location shrineloc1 = new Location(shrineworld,17,-49,16);
                    Location shrineloc2 = new Location(shrineworld,-6,-63,-5);
                    Vector midPt = shrineloc2.clone().toVector().midpoint(shrineloc1.clone().toVector());
                    Location location = new Location(playerLocation.getWorld(),(int)playerLocation.getX()-lookDir.getX() - midPt.getX(),playerLocation.getY(),(int)playerLocation.getZ()-lookDir.getZ()-midPt.getZ());
                    Bukkit.broadcastMessage(ChatColor.RED + "Domain Expansion:");
                    player.getWorld().playSound(playerLocation,Sound.BLOCK_END_PORTAL_SPAWN, SoundCategory.HOSTILE, 1f,0.5f);

                    bukS.runTaskLater(plugin, new Runnable() {
                        public void run() {

                            Block[] blocks = new Block[6279];
                            Bukkit.broadcastMessage(ChatColor.RED + "Malevolent Shrine");
                            HackathonPlugin.musicPl.playSong(player,"malevolentshrine");
                            for (int x = 0; x < shrineloc1.getX() - shrineloc2.getX(); x++) {
                                for (int y = 0; y < shrineloc1.getY() - shrineloc2.getY(); y++) {
                                    for (int z = 0; z < shrineloc1.getZ() - shrineloc2.getZ(); z++) {

                                        Block template = shrineworld.getBlockAt((int) shrineloc2.getX() + x, (int) shrineloc2.getY() + y, (int) shrineloc2.getZ() + z);
                                        Block newPos = player.getWorld().getBlockAt((int) location.getX() + x, (int) location.getY() + y, (int) location.getZ() + z);
                                        BlockData old = newPos.getBlockData();
                                        Material oldmat = newPos.getType();
                                        newPos.setType(template.getType());
                                        newPos.setBlockData(template.getBlockData());
                                        bukS.runTaskLater(plugin, new Runnable() {
                                            public void run() {
                                                newPos.setType(oldmat);
                                                newPos.setBlockData(old);
                                            }
                                        }, 350);
                                    }
                                }
                            }

                            bukS.runTaskLater(plugin, new Runnable() {
                                public void run() {
                                    for (int i = 0; i < 300; i++) {
                                        bukS.runTaskLater(plugin, new Runnable() {
                                            public void run() {
                                                Location randomLoc1 = new Location(player.getWorld(),Math.random()*50-25,Math.random()*50-25,Math.random()*50-25);
                                                Location randomLoc2 = new Location(player.getWorld(),Math.random()*50-25,Math.random()*50-25,Math.random()*50-25);
                                                randomLoc1.subtract(midPt);
                                                randomLoc2.subtract(midPt);

                                                SlashParticle.Slash(player.getWorld(), location.clone().add(randomLoc1), location.clone().add(randomLoc2));
                                            }
                                        },i);
                                    }
                                }
                            },30);
                        }
                    },30);

                }

                if (item.getItemMeta().getItemName().equals("Clear Stick") && item.getType().equals(Material.STICK)) {
                    if (item.getItemMeta().getLore() == null) {
                        ItemMeta im = item.getItemMeta();
                        ArrayList<String> coords = new ArrayList<String>();
                        coords.add(String.valueOf(event.getClickedBlock().getLocation().getX()));
                        coords.add(String.valueOf(event.getClickedBlock().getLocation().getY()));
                        coords.add(String.valueOf(event.getClickedBlock().getLocation().getZ()));
                        im.setLore(coords);
                        item.setItemMeta(im);
                    } else {
                        Location loc1 = new Location(player.getWorld(),Double.parseDouble(item.getItemMeta().getLore().get(0)),Double.parseDouble(item.getItemMeta().getLore().get(1)),Double.parseDouble(item.getItemMeta().getLore().get(2)));
                        int xVal = (int)(Math.max(loc1.getX(),event.getClickedBlock().getLocation().getX()) - Math.min(loc1.getX(),event.getClickedBlock().getLocation().getX()));
                        int yVal = (int)(Math.max(loc1.getY(),event.getClickedBlock().getLocation().getY()) - Math.min(loc1.getY(),event.getClickedBlock().getLocation().getY()));
                        int zVal = (int)(Math.max(loc1.getZ(),event.getClickedBlock().getLocation().getZ()) - Math.min(loc1.getZ(),event.getClickedBlock().getLocation().getZ()));

                        xVal = Math.max(xVal,1);
                        yVal = Math.max(yVal,1);
                        zVal = Math.max(zVal,1);
                        ItemMeta im = item.getItemMeta();
                        im.setLore(null);
                        System.out.println(xVal + " " + yVal + " " + zVal);
                        item.setItemMeta(im);

                        for (int x = -1; x <= xVal;x++) {
                            for (int y = -1; y <= yVal;y++) {
                                for (int z = -1; z <= zVal;z++) {
                                    Block newPos = player.getWorld().getBlockAt((int)Math.min(loc1.getX(),event.getClickedBlock().getLocation().getX())+x,(int)Math.min(loc1.getY(),event.getClickedBlock().getLocation().getY())+y,(int)Math.min(loc1.getZ(),event.getClickedBlock().getLocation().getZ())+z);
                                    newPos.setType(Material.AIR);
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}
