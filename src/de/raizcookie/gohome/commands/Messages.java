package de.raizcookie.gohome.commands;

import de.raizcookie.gohome.main.Main;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Messages{
  public static File messages = new File(Main.getPlugin().getDataFolder().getAbsolutePath(), "messages.yml");
  public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(messages);
  
  public static void save(){
    try{
      cfg.save(messages);
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }
  
  public static void create(){
    if ((!messages.exists()) || (messages.length() < 100L)){
      cfg.set("not_set", "&cYou did not place any homes!");
      cfg.set("home_usage", "&cUse &a/home <NAME>&c.");
      cfg.set("home_usage_admin", "&cUse &a/home <NAME>&c. To visit a strangers home use &a/home <PLAYERNAME> <HOMENAME>&c.");
      cfg.set("no_permission", "&cYou are missing the required permission!");
      cfg.set("teleport_success", "&aYou were successfully teleported to home &c<home>&a!");
      cfg.set("home_is_null", "&cHome <ARG0> does not exist.");
      cfg.set("home_is_null_stranger", "&cHome <ARG0> from the chosen player does not exist.");
      cfg.set("home_set", "&aHome &c<ARG0> &awas set!");
      cfg.set("home_exists", "&c<ARG0> does already exist!");
      cfg.set("home_set_usage", "&cUse &a/sethome &cor &asethome <NAME>!");
      cfg.set("home_del_usage", "&cUse &6/delhome &c<NAME>.");
      cfg.set("home_del_success", "&aHome &c<ARG0> &awas removed!");
      cfg.set("home_list", "&aHomes&f:&c <HOMES>");
      save();
    }
  }
  
  public static void check(){
    if (!cfg.contains("not_set")) {
      cfg.set("not_set", "&cYou did not place any homes!");
    }
    if (!cfg.contains("home_usage")) {
      cfg.set("home_usage", "&cUse &a/home <NAME>&c.");
    }
    if (!cfg.contains("home_usage_admin")) {
    	cfg.set("home_usage_admin", "&cUse &a/home <NAME>&c. To visit a strangers home use &a/home <PLAYERNAME> <HOMENAME>&c.");
      }
    if (!cfg.contains("no_permission")) {
      cfg.set("no_permission", "&cYou are missing the required permission!");
    }
    if (!cfg.contains("teleport_success")) {
      cfg.set("teleport_success", "&aYou were successfully teleported to home &c<home> &a!");
    }
    if (!cfg.contains("home_is_null")) {
      cfg.set("home_is_null", "&cHome <ARG0> does not exist.");
    }
    if (!cfg.contains("home_is_null_stranger")) {
        cfg.set("home_is_null_stranger", "&cHome <ARG0> from the chosen player does not exist.");
      }
    if (!cfg.contains("home_set")) {
      cfg.set("home_set", "&aHome&c <ARG0> &awas set!");
    }
    if (!cfg.contains("home_exists")) {
      cfg.set("home_exists", "&c<ARG0> does already exist!");
    }
    if (!cfg.contains("home_del_usage")) {
      cfg.set("home_del_success", "&aHome &c<HOME> &awas removed!");
    }
    if (!cfg.contains("home_set_usage")) {
      cfg.set("home_set_usage", "&cUse &a/sethome &cor &asethome <NAME>!");
    }
    if (!cfg.contains("home_del_success")) {
      cfg.set("home_del_success", "&aHome &c<HOME> &awas removed!");
    }
    if (!cfg.contains("home_list")) {
      cfg.set("home_list", "&aHomes&f:&c<HOMES>");
    }
    save();
  }
}
