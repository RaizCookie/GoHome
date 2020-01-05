package de.raizcookie.gohome.commands;

import de.raizcookie.gohome.main.Main;
import de.raizcookie.gohome.methods.Messages;

import java.io.File;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetHome implements CommandExecutor{
  public static File homes = new File(Main.getPlugin().getDataFolder().getAbsolutePath(), "homes.yml");
  public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(homes);
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
    if (!(sender instanceof Player))
    	return false;
      Player p = (Player)sender;
      if (!p.hasPermission("gohome.set")){
    	  p.sendMessage(Messages.cfg.getString("no_permission").replace("&", "§"));
    	  return false;
      }
        Messages.check();
        if (args.length == 1){
          if (!cfg.contains(p.getName() + "." + args[0].toLowerCase()))
          {
            Location loc = p.getLocation();
            cfg.set(p.getName() + "." + args[0].toLowerCase() + ".World", loc.getWorld().getName());
            cfg.set(p.getName() + "." + args[0].toLowerCase() + ".X", Double.valueOf(loc.getX()));
            cfg.set(p.getName() + "." + args[0].toLowerCase() + ".Y", Double.valueOf(loc.getY()));
            cfg.set(p.getName() + "." + args[0].toLowerCase() + ".Z", Double.valueOf(loc.getZ()));
            cfg.set(p.getName() + "." + args[0].toLowerCase() + ".Yaw", Float.valueOf(loc.getYaw()));
            cfg.set(p.getName() + "." + args[0].toLowerCase() + ".Pitch", Float.valueOf(loc.getPitch()));
            
            try {
				cfg.save(homes);
			} catch (IOException e) {
				e.printStackTrace();
			}
            p.sendMessage(Messages.cfg.getString("home_set").replace("&", "§").replace("<ARG0>", args[0]));
          }
          else{
            p.sendMessage(Messages.cfg.getString("home_exists").replace("&", "§").replace("<ARG0>", args[0]));
          }
        }
        else if (args.length == 0){
          Location loc = p.getLocation();
          cfg.set(p.getName() + ".home" + ".World", loc.getWorld().getName());
          cfg.set(p.getName() + ".home" + ".X", Double.valueOf(loc.getX()));
          cfg.set(p.getName() + ".home" + ".Y", Double.valueOf(loc.getY()));
          cfg.set(p.getName() + ".home" + ".Z", Double.valueOf(loc.getZ()));
          cfg.set(p.getName() + ".home" + ".Yaw", Float.valueOf(loc.getYaw()));
          cfg.set(p.getName() + ".home" + ".Pitch", Float.valueOf(loc.getPitch()));
          try {
			cfg.save(homes);
		} catch (IOException e) {
			e.printStackTrace();
		}
          p.sendMessage(Messages.cfg.getString("home_set").replace("&", "§").replace("<ARG0> ", ""));
        }
        else{
          p.sendMessage(Messages.cfg.getString("home_set_usage").replace("&", "§"));
        }
    return false;
  }
}
