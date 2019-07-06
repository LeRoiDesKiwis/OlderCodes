package fr.leroideskiwis.shop.listener;

import java.util.Locale;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.leroideskiwis.shop.Main;

public class Events implements Listener {

	private Main main;

	public Events(Main main){
		this.main = main;
	}
	
	@EventHandler
	public void onClickOnShop(InventoryClickEvent e){
		
		Player p = (Player)e.getWhoClicked();
		
		if(e.getInventory().getName().equalsIgnoreCase("§eShop")){
			
			switch(e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase(Locale.ROOT)){
			
			case "§evip":
				
				p.
				break;
				
			
			
			}
			
		}
		
	}
	
}
