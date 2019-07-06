package fr.leroideskiwis.tntfly.listener;

import java.util.Iterator;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import fr.leroideskiwis.tntfly.Main;

public class Tntfly implements Listener {
	
	private Main main;
	
	public Tntfly(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onExplode(EntityExplodeEvent e){
		
		Entity entity = e.getEntity();
		Iterator<Block> blocks = e.blockList().iterator();
		
		while(blocks.hasNext()){
			Block current = blocks.next();
			
			if(current.getType() == Material.TNT){
				current.setType(Material.AIR);
				
				
				Location location = current.getLocation().add(0.5, 0.25, 0.5);
				
				TNTPrimed tnt = (TNTPrimed)current.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
				tnt.setVelocity(new Vector(0, 0.25, 0));
				
				tnt.teleport(location);
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if(e.getCause() == DamageCause.FALL){
			e.setDamage(e.getDamage() / 20);
		}
	}
	
	@EventHandler
	public void onDamageByEntity(EntityDamageByEntityEvent e){
		
		if(e.getDamager() instanceof TNTPrimed){
			
			if(e.getEntity() instanceof Player){
				Player p = (Player) e.getEntity();
				TNTPrimed tnt = (TNTPrimed) e.getDamager();
				
				for(Entity entity : tnt.getNearbyEntities(2.5, 2.5, 2.5)){
					
					if(entity instanceof Player){
						if(p.equals((Player)entity)){
							p.setVelocity(p.getLocation().getDirection().multiply(e.getDamage()*10000).setY(1));
							e.setDamage(e.getDamage() / 10);
						}
					}
					
				}
				
			}
			
			
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		if(e.getAction() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK){
		Block block = e.getClickedBlock();
		
		if(e.getItem() != null && e.getItem().getType() == Material.FLINT_AND_STEEL){
			if(block != null && block.getType() == Material.TNT){
				
				String[] customNamesTnt = {"§cexplosion dans 4", "§cTNT FLY", "§cBOOM !", "§cskill", "§akiwi"};
				
				e.getClickedBlock().setType(Material.AIR);
				e.setCancelled(true);
				
				Location location = block.getLocation().add(0.5, 0.25, 0.5);
				
				TNTPrimed tnt = (TNTPrimed)block.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
				tnt.setVelocity(new Vector(0, 0.25, 0));
				tnt.setCustomNameVisible(true);
				tnt.setCustomName(customNamesTnt[new Random().nextInt(customNamesTnt.length)]);
				tnt.teleport(location);
				
			}
		}
		
		}
	}
	
}
