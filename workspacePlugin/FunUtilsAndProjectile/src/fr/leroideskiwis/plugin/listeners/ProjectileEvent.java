package fr.leroideskiwis.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ProjectileEvent implements Listener {

	@EventHandler
	public void onProjectileHitEvent(ProjectileHitEvent e){
		
		if(e.getEntity() != null && e.getEntity().getType() == EntityType.SNOWBALL){
			
			/*if(e.getEntity().get){
				Bukkit.broadcastMessage("Hello world !");
			}*/
			
		}
		
	}
	
	
}
