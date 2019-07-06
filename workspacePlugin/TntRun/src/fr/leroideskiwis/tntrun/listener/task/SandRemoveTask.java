package fr.leroideskiwis.tntrun.listener.task;

import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.tntrun.Main;
import fr.leroideskiwis.tntrun.game.GStatus;


public class SandRemoveTask extends BukkitRunnable {

	private Main main;
	
	public SandRemoveTask(Main main) {
		this.main = main;
	}
	
	@Override
	public void run() {
		
		if(main.isStatus(GStatus.FINISH)) cancel();
				
		main.getPlayers().forEach(p -> {
			
			p.getLocation().subtract(0, 1, 0).getBlock().setType(Material.AIR);
			
		});
		
	}

}
