package fr.leroideskiwis.enderpearlbattle.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.leroideskiwis.enderpearlbattle.Main;
import fr.leroideskiwis.enderpearlbattle.game.Gstatus;

public class ListenerPlugin implements Listener {

	private Main main;
	
	public ListenerPlugin(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Action action = e.getAction();
		
	
		
		if(action == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null){
			
			BlockState blockclick = e.getClickedBlock().getState();
			
			if(blockclick instanceof Sign){
				Sign sign = (Sign)blockclick;
				if(sign.getLine(0).equalsIgnoreCase("[teleport]") && sign.getLine(1).equalsIgnoreCase("enderpearlgame")){
					
					main.getSigns().add(sign);
					e.getPlayer().performCommand("enderpearlgame");
					
					
				}
				
			}
			
		}
	}
	
	@EventHandler
	public void onDead(PlayerDeathEvent e){
		if(main.getPlayers().contains((Player)e.getEntity())) main.eliminate(e.getEntity());
		e.setKeepInventory(true);
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		if(main.getPlayers().contains(e.getPlayer())) main.eliminate(e.getPlayer());
	}
	
	@EventHandler
	public void onPvp(EntityDamageByEntityEvent e){
		Entity victim = e.getEntity();
		Entity damager = e.getDamager();
		
		if(victim instanceof Player){
			
			 if(damager instanceof Player && (main.isStatus(Gstatus.WAITING) || main.isStatus(Gstatus.STARTING))){
				e.setCancelled(true);
			}
			
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		Entity victim = e.getEntity();
		
		if(victim instanceof Player){
			
			if(e.getCause() == DamageCause.ENTITY_ATTACK && !(main.isStatus(Gstatus.PLAYING))) return;
			
			Player pVictim = (Player)victim;
			
			Block block = new Location(pVictim.getWorld(), pVictim.getLocation().getX(), pVictim.getLocation().getY(), pVictim.getLocation().getZ()).getBlock();
			
			for(int i = 0; i < 5; i++){
				
				//if(!(e.getCause() == DamageCause.FALL)) {
				block = new Location(pVictim.getWorld(), pVictim.getLocation().getX() + i, pVictim.getLocation().getY(), pVictim.getLocation().getZ() + i).getBlock();
				if(block.getType() == Material.BARRIER || block.getType() == Material.STONE) main.eliminate(pVictim);
				block = new Location(pVictim.getWorld(), pVictim.getLocation().getX() - i, pVictim.getLocation().getY(), pVictim.getLocation().getZ() - i).getBlock();
				if(block.getType() == Material.BARRIER || block.getType() == Material.STONE) main.eliminate(pVictim);
			}
			
			//}
			
			if(e.getCause() == DamageCause.LAVA) main.eliminate(pVictim);
			if(!main.isStatus(Gstatus.DISABLE) && main.isStatus(Gstatus.WAITING) || main.isStatus(Gstatus.STARTING)) e.setCancelled(true);
			
		}
		
	}
	

	
	
}
