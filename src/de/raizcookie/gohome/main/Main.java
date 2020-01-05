package de.raizcookie.gohome.main;

import de.raizcookie.gohome.commands.DeleteHome;
import de.raizcookie.gohome.commands.TeleportHome;
import de.raizcookie.gohome.methods.CreateInventory;
import de.raizcookie.gohome.methods.Messages;
import de.raizcookie.gohome.commands.SetHome;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
  private static Main plugin;
  
  public void onEnable(){
    plugin = this;
    System.out.println("[GoHome] started!");
    getCommand("delhome").setExecutor(new DeleteHome());
    getCommand("home").setExecutor(new TeleportHome());
    getCommand("sethome").setExecutor(new SetHome());
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