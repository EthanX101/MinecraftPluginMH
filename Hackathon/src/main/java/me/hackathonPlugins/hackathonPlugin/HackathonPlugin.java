package me.hackathonPlugins.hackathonPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class HackathonPlugin extends JavaPlugin {

    static SpigotMusicPlayer musicPl;
    public static HackathonPlugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("bruh");

        plugin = this;
        new SukunaDomain(this);
        new GetCoordsCommand(this);
        new DeleteStick(this);
        BigFloppa bigfloppa = new BigFloppa(this);

        Kamehameha kamehameha = new Kamehameha(this);
        musicPl = new SpigotMusicPlayer();

        musicPl.onEnable();

        getServer().getPluginManager().registerEvents(kamehameha, plugin);
        getServer().getPluginManager().registerEvents(bigfloppa, plugin);
        getServer().getPluginManager().registerEvents(new PlayerClickEvent(), plugin);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static HackathonPlugin getPlug() {
        return plugin;
    }
}
