package de.raizcookie.gohome.inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.raizcookie.gohome.commands.Messages;
import de.raizcookie.gohome.commands.SetHomeCommand;

public class CreateInventory implements Listener{
	
	  static FileConfiguration cfg = SetHomeCommand.cfg;
	
	public static void openGUI(Player p, String t, final String INV_NAME, final int INV_ROWS, final Material INV_MATERIAL){
		ArrayList<String> playerHomes = new ArrayList<String>();
		Inventory inventory = Bukkit.createInventory(null, 9*INV_ROWS, INV_NAME);
		for(String key : cfg.getConfigurationSection(t).getKeys(false)) 
			playerHomes.add(key);
		for (int i = 0; i < playerHomes.size(); i++) {
			ItemStack itemStack = new ItemStack(INV_MATERIAL);
			ItemMeta itemMeta = itemStack.getItemMeta();
			itemMeta.setDisplayName(playerHomes.get(i));
			itemStack.setItemMeta(itemMeta);
			inventory.setItem(i, itemStack);
		}
		p.openInventory(inventory);
	}
	
	@EventHandler
	public void handleGUIClick(InventoryClickEvent e) {
		if(!(e.getWhoClicked() instanceof Player)) return;
		Player p = (Player) e.getWhoClicked();
		if(e.getView().getTitle().contains("Homes of Player")) {
			String t = e.getView().getTitle().replace("Homes of Player ", "");
			String homeName = e.getCurrentItem().getItemMeta().getDisplayName();
			
			e.setCancelled(true);
			World world = Bukkit.getWorld(cfg.getString(t + "." + homeName + ".World"));
            double x = cfg.getDouble(t + "." + homeName + ".X");
            double y = cfg.getDouble(t + "." + homeName + ".Y");
            double z = cfg.getDouble(t + "." + homeName + ".Z");
            float yaw = (float)cfg.getDouble(t + "." + homeName + ".Yaw");
            float pitch = (float)cfg.getDouble(t + "." + homeName + ".Pitch");
            Location loc = new Location(world, x, y, z, yaw, pitch);
            p.teleport(loc);
            p.sendMessage(Messages.cfg.getString("teleport_success").replace("&", "§").replace("<home>", homeName));
		}
	}

}
