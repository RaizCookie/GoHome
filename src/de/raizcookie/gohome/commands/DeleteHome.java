package de.raizcookie.gohome.commands;

import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.raizcookie.gohome.methods.Messages;

public class DeleteHome implements CommandExecutor{
  public static FileConfiguration cfg = SetHome.cfg;
  public void save(){
    try{
      cfg.save(SetHome.homes);
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player))
    	return false;
      Player p = (Player)sender;
    if (!p.hasPermission("gohome.delete")) {
    	p.sendMessage(Messages.cfg.getString("no_permission").replace("&", "§"));
    	return false;
    }
    
    Messages.check();
    
        if (args.length == 1) {
          if (!(SetHome.homes.exists()) && !(SetHome.homes.length() > 1L))  {
        	  p.sendMessage(Messages.cfg.getString("not_set").replace("&", "§"));
        	  return false;
          }
          
            args[0] = args[0].toLowerCase();
            if (!cfg.contains(p.getName() + "." + args[0])) {
              p.sendMessage(Messages.cfg.getString("home_is_null").replace("&", "§").replace("<ARG0>", args[0]));
            }
            else {
              cfg.set(p.getName() + "." + args[0], null);
              save();
              p.sendMessage(Messages.cfg.getString("home_del_success").replace("&", "§").replace("<ARG0>", args[0]));
            }
        }
        else if (args.length == 0)  {
          if (SetHome.homes.exists() && SetHome.homes.length() > 1L)  {
            ConfigurationSection cs = cfg.getConfigurationSection(p.getName() + ".");
            int i = 0;
            String homeName = "";
            for (String homes : cs.getKeys(false))  {
              i++;
              homeName = homes;
            }
            if (homeName != "" && (SetHome.homes.length() > 1L))   {
              if (i != 1) {
                p.sendMessage(Messages.cfg.getString("home_del_usage").replace("&", "§"));
              }
              else {
                cfg.set(p.getName() + "." + homeName, null);
                save();
                p.sendMessage(Messages.cfg.getString("home_del_success").replace("&", "§").replace("<ARG0>", homeName));
              }
            }
            else {
              p.sendMessage(Messages.cfg.getString("not_set").replace("&", "§"));
            }
          }
          else  {
            p.sendMessage(Messages.cfg.getString("not_set").replace("&", "§"));
          }
        }
        else {
          p.sendMessage(Messages.cfg.getString("home_del_usage").replace("&", "§"));
        }
    return false;
  }
}
