package fr.leroideskiwis.mysterychest.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.leroideskiwis.mysterychest.Main;

public class ClickOnInv implements Listener {
	
	private Main main;
	
	public ClickOnInv(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		
		if((e.getClickedInventory() != null ? e.getClickedInventory() : e.getInventory()).getName().equals(main.nameOfChest)) e.setCancelled(true);
		
	}
	
}
