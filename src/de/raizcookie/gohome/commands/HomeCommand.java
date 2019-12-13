package de.raizcookie.gohome.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
  FileConfiguration cfg = SetHomeCommand.cfg;
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
    if (!(sender instanceof Player)) {
    	return false;
    }
      Player p = (Player)sender;
      if (!p.hasPermission("gohome.teleport")){
    	  p.sendMessage(Messages.cfg.getString("no_permission").replace("&", "§"));
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
        	  ConfigurationSection cs = cfg.getConfigurationSection(p.getName() + ".");
            int i = 0;
            String worlds = null;
            for (String arena : cs.getKeys(false)) {
              i++;
              worlds = arena;
            }
            if (i == 1 && (worlds != null) && SetHomeCommand.homes.exists()) {
              p.sendMessage(Messages.cfg.getString("teleport_success").replace("&", "§").replace("<home>", worlds));
              World world = Bukkit.getWorld(cfg.getString(p.getName() + "." + worlds + ".World"));
              double x = cfg.getDouble(p.getName() + "." + worlds + ".X");
              double y = cfg.getDouble(p.getName() + "." + worlds + ".Y");
              double z = cfg.getDouble(p.getName() + "." + worlds + ".Z");
              float yaw = (float)cfg.getDouble(p.getName() + "." + worlds + ".Yaw");
              float pitch = (float)cfg.getDouble(p.getName() + "." + worlds + ".Pitch");
              Location loc = new Location(world, x, y, z, yaw, pitch);
              p.teleport(loc);
            }
            else if (cfg.getConfigurationSection(p.getName()).getKeys(false) != null && SetHomeCommand.homes.exists()) {
              String homes = "";
              for (String key : cfg.getConfigurationSection(p.getName()).getKeys(false)) {
            	  homes = homes + " " + key;
              }
              if (homes != "") {
                p.sendMessage(Messages.cfg.getString("home_list").replace("&", "§").replace("<HOMES>", homes));
              } else {
                p.sendMessage(Messages.cfg.getString("not_set").replace("&", "§"));
              }
            }
            else {
              p.sendMessage(Messages.cfg.getString("not_set").replace("&", "§"));
            }
          }
          else {
            p.sendMessage(Messages.cfg.getString("not_set").replace("&", "§"));
          }
          // Zum Home von anderem Spieler teleportieren.
        } else if(args.length == 2) {
        	if(p.hasPermission("gohome.teleport.tostrangers")) {
        	 args[1] = args[1].toLowerCase();
             if (cfg.contains(args[0] + "." + args[1]) && SetHomeCommand.homes.exists() && SetHomeCommand.homes.length() > 1L)
             {
               World world = Bukkit.getWorld(cfg.getString(args[0] + "." + args[1] + ".World"));
               double x = cfg.getDouble(args[0] + "." + args[1] + ".X");
               double y = cfg.getDouble(args[0] + "." + args[1] + ".Y");
               double z = cfg.getDouble(args[0] + "." + args[1] + ".Z");
               float yaw = (float)cfg.getDouble(args[0] + "." + args[1] + ".Yaw");
               float pitch = (float)cfg.getDouble(args[0] + "." + args[1] + ".Pitch");
               Location loc = new Location(world, x, y, z, yaw, pitch);
               p.teleport(loc);
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
