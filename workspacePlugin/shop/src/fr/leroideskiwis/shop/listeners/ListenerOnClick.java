package fr.leroideskiwis.shop.listeners;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.leroideskiwis.shop.Main;
import fr.leroideskiwis.utils.ItemBuilder;

public class ListenerOnClick implements Listener {

	private Main main;
	
	public ListenerOnClick(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e){
		
		if(e.getInventory().getName().equalsIgnoreCase(main.nameShop)){
			e.setCancelled(true);
			if(e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7clé du coffre des récompenses")){
				
				if(main.getEMain().getCoins().get((Player)e.getWhoClicked()) >= 30.0){
				e.getWhoClicked().getInventory().addItem(new ItemBuilder(Material.TRIPWIRE_HOOK)
						.setName("§7clé du coffre des recompenses")
						.setLore(Arrays.asList("","§8Cette clé te permet d'acceder au coffre des recompenses"))
						.toItemStack());
				
				e.getWhoClicked().sendMessage("§aVous avez reçu §7clé du coffre des recompenses");
				
			} else {
				e.setCancelled(true);
				e.getWhoClicked().sendMessage("§cErreur : vous n'avez pas assez d'argent ! argent requis : 30coins. vous avez " +main.getEMain().getCoins().get((Player)e.getWhoClicked()+"coins"));
			}
			}
			
		}
		
	}
	
}
