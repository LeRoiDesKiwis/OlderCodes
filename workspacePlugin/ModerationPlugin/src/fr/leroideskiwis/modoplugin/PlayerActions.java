package fr.leroideskiwis.modoplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerActions extends BukkitRunnable {

	@Override
	public void run() {
		
		for(Player p : Bukkit.getOnlinePlayers()){
			
			p.setFoodLevel(20);
			
		}

	}

}
