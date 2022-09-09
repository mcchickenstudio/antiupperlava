package timeplay.antiupperlava;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("antiupperlava").setExecutor(new CommandReload());
        System.out.println("§7AntiUpperLava is §aenabled");
        saveDefaultConfig();
        config = getConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("§7AntiUpperLava is §cdisabled");
    }

    @EventHandler
    public void Lava(PlayerBucketEmptyEvent event) {
        if ((event.getBucket().toString().contains("WATER")) && (config.getBoolean("disable-water")) && (event.getBlockClicked().getY() >= Integer.parseInt(config.getString("height")))) {
                    event.setCancelled(true
                    );
                    switch (config.getString("type")) {
                        case ("message"):
                            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("message")));
                            break;
                        case("actionbar"):
                            event.getPlayer().sendActionBar(ChatColor.translateAlternateColorCodes('&',config.getString("message")));
                            break;
                        case("subtitle"):
                            event.getPlayer().sendTitle("",ChatColor.translateAlternateColorCodes('&',config.getString("message")),20,60,30);
                            break;
                    }
                    if (!(config.getString("sound").equalsIgnoreCase("none"))) {
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.valueOf(config.getString("sound")),1,1);
                    }
        }
        if ((event.getBucket().toString().contains("LAVA")) && (config.getBoolean("disable-lava")) && (event.getBlockClicked().getY() >= Integer.parseInt(config.getString("height")))) {
                    event.setCancelled(true);
                    switch (config.getString("type")) {
                        case ("message"):
                            event.getPlayer().sendMessage(config.getString("message").replace('&', '§'));
                            break;
                        case("actionbar"):
                            event.getPlayer().sendActionBar(config.getString("message").replace('&','§'));
                            break;
                        case("subtitle"):
                            event.getPlayer().sendTitle("",config.getString("message").replace('&','§'));
                            break;
                    }
                    if (!(config.getString("sound").equalsIgnoreCase("none"))) {
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.valueOf(config.getString("sound")),1,1);
                    }
        }

    }

    @EventHandler
    public void Dispenser(BlockDispenseEvent event2) {
        if (config.getString("disable-dispenser").equalsIgnoreCase("true")) {
            if ((event2.getItem().toString().contains("WATER")) && (config.getBoolean("disable-water") && (event2.getBlock().getY() >= Integer.parseInt(config.getString("height"))))) {
                        event2.setCancelled(true);
            }
            if ((event2.getItem().toString().contains("LAVA")) && (config.getBoolean("disable-lava") && (event2.getBlock().getY() >= Integer.parseInt(config.getString("height"))))) {
                        event2.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void Ice(BlockFromToEvent event3) {
        if ((event3.getToBlock().toString().contains("WATER")) && (config.getBoolean("disable-water") && (event3.getBlock().getY() >= Integer.parseInt(config.getString("height"))))) {
                    event3.setCancelled(true);
        }
        if ((event3.getToBlock().toString().contains("LAVA")) && (config.getBoolean("disable-lava") && (event3.getBlock().getY() >= Integer.parseInt(config.getString("height"))))) {
                    event3.setCancelled(true);
        }
    }

}
