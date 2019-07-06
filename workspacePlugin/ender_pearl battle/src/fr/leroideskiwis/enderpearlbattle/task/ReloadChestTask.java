package fr.leroideskiwis.enderpearlbattle.task;

import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.enderpearlbattle.Main;
import fr.leroideskiwis.enderpearlbattle.game.Gstatus;

public class ReloadChestTask extends BukkitRunnable{

	private Main main;
	
	public ReloadChestTask(Main main) {
		this.main = main;
	}

	@Override
	public void run() {
		if(!(main.isStatus(Gstatus.PLAYING))) cancel();
		
		main.getPlayers().forEach(p -> {
			
			p.sendMessage("§6Les coffres ont été reloads");
			main.reloadChest();
			p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0f, 1.0f);
			
		});
		
		
		
	}

}
