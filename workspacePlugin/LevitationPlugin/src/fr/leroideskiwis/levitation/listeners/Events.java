package fr.leroideskiwis.levitation.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public class Events implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e){
		
		
		
		Player mover = e.getPlayer();
		

		
		if(mover.getLocation().getBlockY() >= 255){
			
		if(mover.hasPotionEffect(PotionEffectType.LEVITATION)) mover.removePotionEffect(PotionEffectType.LEVITATION);
		
		}
		
		if(mover.getLocation().getY() <= -60){
			mover.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 10*20, 20));
		}
	}
	
}
