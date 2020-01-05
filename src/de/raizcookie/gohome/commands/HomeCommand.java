package de.raizcookie.gohome.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.raizcookie.gohome.inventory.CreateInventory;

public class HomeCommand implements CommandExecutor {
  FileConfiguration cfg = SetHomeCommand.cfg;
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
    if (!(sender instanceof Player)) {
    	return false;
    }
      Player p = (Player)sender;
      if (!p.hasPermission("gohome.teleport")){
    	  p.sendMessage(Messages.cfg.getString("no_permission").replace("&", "§"));
    	  return false;
      }
        Messages.check();
		if (args.length == 1)  {
          args[0] = args[0].toLowerCase();
          if ((cfg.contains(p.getName() + "." + args[0])) && (SetHomeCommand.homes.exists()) && (SetHomeCommand.homes.length() > 1L))  {
            World world = Bukkit.getWorld(cfg.getString(p.getName() + "." + args[0] + ".World"));
            double x = cfg.getDouble(p.getName() + "." + args[0] + ".X");
            double y = cfg.getDouble(p.getName() + "." + args[0] + ".Y");
            double z = cfg.getDouble(p.getName() + "." + args[0] + ".Z");
            float yaw = (float)cfg.getDouble(p.getName() + "." + args[0] + ".Yaw");
            float pitch = (float)cfg.getDouble(p.getName() + "." + args[0] + ".Pitch");
            Location loc = new Location(world, x, y, z, yaw, pitch);
            p.teleport(loc);
          }
          else {
            p.sendMessage(Messages.cfg.getString("home_is_null").replace("&", "§").replace("<ARG0>", args[0]));
          }
        }
        else if (args.length == 0) {
          if ((SetHomeCommand.homes.exists()) && (SetHomeCommand.homes.length() > 1L)) {
        	  if(cfg.getConfigurationSection(p.getName() + ".") == null) {
            	p.sendMessage(Messages.cfg.getString("not_set").replace("&", "§"));
            	return false;
            }
            if (SetHomeCommand.homes.exists()) {
            	 CreateInventory.openGUI(p, p.getName(), "Homes of Player " + p.getName(), 4, Material.RED_BED);
            }
          }
          else {
            p.sendMessage(Messages.cfg.getString("not_set").replace("&", "§"));
          }
          // Zum Home von anderem Spieler teleportieren.
        } else if(args.length == 2) {
        	if(p.hasPermission("gohome.teleport.tostrangers")) {
             if (cfg.contains(args[0]) && SetHomeCommand.homes.exists() && SetHomeCommand.homes.length() > 1L)
             {
            	 CreateInventory.openGUI(p, args[0], "Homes of Player " + args[0], 4, Material.RED_BED);
             }
             else {
               p.sendMessage(Messages.cfg.getString("home_is_null_stranger").replace("&", "§").replace("<ARG0>", args[0]));
             }
        	} else {
        		p.sendMessage(Messages.cfg.getString("no_permission").replace("&", "§"));
        	}
        }
        else {
          
          if(p.hasPermission("gohome.teleport.tostrangers"))
        	  p.sendMessage(Messages.cfg.getString("home_usage_admin").replace("&", "§"));
          else
        	  p.sendMessage(Messages.cfg.getString("home_usage").replace("&", "§"));
        }
    return false;
  }
}
