package me.hackathonPlugins.hackathonPlugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetCoordsCommand implements CommandExecutor {

    public GetCoordsCommand(HackathonPlugin plugin) {
        plugin.getCommand("getcoordinates").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            String cmd = command.getName();

            if (cmd.equals("getcoordinates")) {
                Player player = (Player)sender;
                Location location = player.getLocation();
                System.out.println(location.getX() + " " + location.getY() + " " + location.getZ());
            }
        }
        return false;
    }
}
