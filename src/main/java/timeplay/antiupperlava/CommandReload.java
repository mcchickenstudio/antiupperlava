package timeplay.antiupperlava;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandReload implements CommandExecutor {

    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("AntiUpperLava");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("antiupperlava.reload")) {
                sender.sendMessage("AntiUpperLava reloading...");
                plugin.getPluginLoader().disablePlugin(plugin);
                plugin.reloadConfig();
                plugin.getPluginLoader().enablePlugin(plugin);
                sender.sendMessage("AntiUpperLava reloaded!");
            }
        }
        else {
            System.out.println("AntiUpperLava reloading...");
            plugin.reloadConfig();
            System.out.println("AntiUpperLava reloaded!");
        }
        return true;
    }
}
