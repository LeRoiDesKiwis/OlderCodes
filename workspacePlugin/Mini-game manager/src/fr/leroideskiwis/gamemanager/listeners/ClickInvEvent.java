package fr.leroideskiwis.gamemanager.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.permissions.Permission;

import fr.leroideskiwis.enderpearlbattle.eventscustom.PlayerJoinGameEvent;
import fr.leroideskiwis.gamemanager.Main;

public class ClickInvEvent implements Listener {

	private Main main;

	public ClickInvEvent(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();

		if (e.getInventory().getName().equals(main.compassInventory.getName())) {

			switch (e.getCurrentItem().getType()) {

			case ENDER_PEARL:
				Bukkit.getPluginManager().callEvent(new PlayerJoinGameEvent(p));
				p.closeInventory();
				break;

			case TNT:
				
				if(p.hasPermission(new Permission("chat.vip"))){
					
				Bukkit.getPluginManager().callEvent(new fr.leroideskiwis.tntrun.eventcustom.PlayerJoinGameEvent(p));
				p.closeInventory();
				
				} else {
			
				p.sendMessage("§cCe jeu est en cours de développement, vous devez etre §eVIP §cpour y accéder !");
				}
				
				break;

			case LAVA_BUCKET:
				
				Bukkit.getPluginManager().callEvent(new fr.leroideskiwis.floorlava.eventscustom.PlayerJoinGameEvent(p));
				p.closeInventory();
				break;
				
			default:
				break;

			}

		}

		if (main.bypass.contains(p))
			return;

		e.setCancelled(true);

	}

	@EventHandler
	public void onJette(PlayerDropItemEvent e) {

		if (main.bypass.contains(e.getPlayer()))
			return;
		e.setCancelled(true);
		e.getPlayer().sendMessage("§cVous n'avez pas la permission de jeter cette item !");

	}
	
	@EventHandler
	public void onInteractEvent(PlayerInteractEvent e){
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(main.bypass.contains(e.getPlayer())) return ;
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cVous n'avez pas la permission d'interagir avec les blocs ici !");
			
		}
		
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		if(main.bypass.contains(e.getPlayer())) return;
		e.setCancelled(true);
		e.getPlayer().sendMessage("§cVous n'avez pas la permission de placer ici !");
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		if(main.bypass.contains(e.getPlayer())) return;
		e.setCancelled(true);
		e.getPlayer().sendMessage("§cVous n'avez pas la permission de casser ici !");
	}
	
}
