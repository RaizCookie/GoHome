package de.raizcookie.gohome.main;

import de.raizcookie.gohome.commands.DelHomeCommand;
import de.raizcookie.gohome.commands.HomeCommand;
import de.raizcookie.gohome.commands.Messages;
import de.raizcookie.gohome.commands.SetHomeCommand;
import de.raizcookie.gohome.inventory.CreateInventory;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
  private static Main plugin;
  
  public void onEnable(){
    plugin = this;
    System.out.println("[GoHome] started!");
    getCommand("delhome").setExecutor(new DelHomeCommand());
    getCommand("home").setExecutor(new HomeCommand());
    getCommand("sethome").setExecutor(new SetHomeCommand());
    Messages.create();
    Messages.check();
    Bukkit.getPluginManager().registerEvents(new CreateInventory(), this);
  }
  
  public static Main getPlugin(){
    return plugin;
  }
  
  public void onDisable(){
    System.out.println("[GoHome] stopped!");
  }
}