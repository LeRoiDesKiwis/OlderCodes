package fr.leroideskiwis.murdermystery.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import fr.leroideskiwis.murdermystery.Main;
import fr.leroideskiwis.murdermystery.game.GPlayer;
import fr.leroideskiwis.murdermystery.game.Gstatus;
import fr.leroideskiwis.murdermystery.game.Role;

public class DamageEvents implements Listener {

	private Main main = Main.getInstance();
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		
		if(e.getEntity() instanceof Player){
			
			Player p = (Player)e.getEntity();
			GPlayer gp = main.foundGPlayer(p);
			
			if(e.getDamager() instanceof Player){
			Player damager = (Player)e.getDamager();
			GPlayer GDamager = main.foundGPlayer(damager);
				
		
			
			
			if(GDamager.isRole(Role.MURDER) && GDamager.getPlayer().getInventory().getItemInMainHand().getType() == Material.IRON_SWORD) main.eliminate(p);
			else e.setCancelled(true);
			
			
			
			} else if(e.getDamager() instanceof Arrow){
				
				ProjectileSource shooter = ((Arrow)e.getDamager()).getShooter();
				
				if(shooter instanceof Player){
					Player Pshooter = (Player)shooter;
					GPlayer GPshooter = main.foundGPlayer(Pshooter);
					
					if(GPshooter.isRole(Role.DETECTIVE)){
					
						if(gp.isRole(Role.MURDER)){
							
							main.eliminate(p);
							main.setStatus(Gstatus.FINISH);
							
							main.getPlayers().forEach(pl -> pl.sendMessage("§cBravo ! le murder a été tué !"));
							
						
							
							main.getPlayers().forEach(pl -> pl.teleport(main.getHubLocation()));
							
							main.getPlayers().clear();
							main.getGPlayers().clear();
							
						} else if(gp.isRole(Role.INNOCENT)){
							
							main.eliminate(Pshooter);
							Pshooter.sendMessage("§cVous avez tué un innocent :(");
							main.eliminate(p);
							
						}
						
					}
					
					
				}
				
			}
		}
		
	}
	
}
