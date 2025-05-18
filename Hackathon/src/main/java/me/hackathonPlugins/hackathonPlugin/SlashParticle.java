package me.hackathonPlugins.hackathonPlugin;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

public class SlashParticle {
    static Sound[] sounds = new Sound[6];
    static BukkitScheduler bukS = Bukkit.getScheduler();
    static int length = 100;
    public static void Slash(World world, Location location1, Location location2) {
        for (int i = -length; i < length; i++) {
            Vector vector = location2.toVector().subtract(location1.toVector()).divide(new Vector(length,length,length)).multiply((i+length));
            Location location = location1.clone().add(vector);
            double increment = Math.max(Math.sqrt(Math.sqrt(Math.abs(i))),1);
            Particle.DustOptions outside = new Particle.DustOptions(org.bukkit.Color.fromRGB(255, 255,255), (float)Math.max((8/increment),2));
            Particle.DustOptions inside = new Particle.DustOptions(org.bukkit.Color.fromRGB(0,0,0), (float)Math.max((7/increment),1.5));

            Location finalLocation = location.clone(); // Create a copy of the location
            bukS.runTaskLater(HackathonPlugin.getPlug(), () -> {
                world.spawnParticle(Particle.DUST, finalLocation, 0, outside);
                world.spawnParticle(Particle.DUST, finalLocation, 3, inside);


            }, (long)(i+length)/90);

            Block block = world.getBlockAt(finalLocation);

            if (block.getType().equals(Material.AIR)) {
                block.setType(Material.LIGHT);
                bukS.runTaskLater(HackathonPlugin.getPlug(), () -> {
                    block.setType(Material.AIR);
                },4);
            }

        }
        Location soundLoc = location1.toVector().midpoint(location2.toVector()).toLocation(world);
        world.playSound(soundLoc,Sound.ITEM_TRIDENT_HIT, SoundCategory.HOSTILE, 2,1);
        world.playSound(soundLoc,Sound.ITEM_TRIDENT_RETURN, SoundCategory.HOSTILE,4f,0.3f);
        world.playSound(soundLoc,Sound.ITEM_TRIDENT_THROW, SoundCategory.HOSTILE, 1f,0.05f);
    }
}
