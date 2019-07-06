package fr.leroideskiwis.inventory.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.leroideskiwis.inventory.Main;

public class InteractionEvent implements Listener {
	
	private Main main;
	
	public InteractionEvent(Main main) {
		this.main = main;
	}
	
	private ItemStack itemWithMeta(Material item, String displayName){
		if(displayName == null || displayName.equals("")) return new ItemStack(item);
		
		ItemStack item2 = new ItemStack(item);
		ItemMeta meta = item2.getItemMeta();
		meta.setDisplayName(displayName);
		item2.setItemMeta(meta);
		return item2;
		
	}

	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player)e.getWhoClicked();
		String name = e.getInventory().getName();
		ItemStack current = e.getCurrentItem();
		
		
		
		if(name.contains("gamemodeMenu")){
			
			switch(current.getType()){
			
			case WOOD_AXE:
				p.setGameMode(GameMode.SURVIVAL);
				break;
			case DIAMOND_BLOCK:
				p.setGameMode(GameMode.CREATIVE);
				break;
			case WOOD_HOE:
				p.setGameMode(GameMode.ADVENTURE);
				break;
			case GLASS:
				p.setGameMode(GameMode.SPECTATOR);
				break;
			case ARROW:
				main.getInventories().stream().filter(menu -> menu.equals("utilsMenu")).forEach(menu -> {
					
					p.closeInventory();
					p.openInventory(menu);
					
				});
				break;
				
			default:
				break;
			
			}
			
		} else if(name.contains("utilsMenu")){
			switch (current.getType()) {
			case GOLD_BLOCK:
				main.getInventories().stream().filter(menu -> menu.equals("gamemodeMenu")).forEach(menu -> {
					p.closeInventory();
					p.openInventory(menu);
				});
				
				break;

			case DIAMOND_SWORD:
				p.setHealth(0.0);
				break;

			default:
				break;

			}
		}
		
	}
	
}
