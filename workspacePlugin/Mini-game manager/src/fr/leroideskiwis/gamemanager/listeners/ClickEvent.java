package fr.leroideskiwis.gamemanager.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.leroideskiwis.gamemanager.Main;

public class ClickEvent implements Listener {

	private Main main;
	
	public ClickEvent(Main main){
		this.main = main;
	}
	
	@EventHandler
	public void onClickOnCompass(PlayerInteractEvent e){
		
		Action action = e.getAction();
		
		if(action == Action.LEFT_CLICK_AIR || action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){
			
			
			
			if(e.getItem() != null && e.getItem().getType() == Material.WATCH && e.getItem().getItemMeta().getDisplayName().contains("Sélecteur de jeux")){
				if(action == Action.RIGHT_CLICK_BLOCK) e.setCancelled(true);
				e.getPlayer().openInventory(main.compassInventory);
			}
			
		}
		
	}
	
}
