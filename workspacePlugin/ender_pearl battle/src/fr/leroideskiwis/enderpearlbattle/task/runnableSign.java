package fr.leroideskiwis.enderpearlbattle.task;

import org.bukkit.block.Sign;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.enderpearlbattle.Main;
import fr.leroideskiwis.enderpearlbattle.game.Gstatus;

public class runnableSign extends BukkitRunnable{

	private Main main;
	
	public runnableSign(Main main) {
		this.main = main;
	}

	@Override
	public void run() {
		for(Sign sign : main.getSigns()){
			if(main.isStatus(Gstatus.WAITING)) sign.setLine(2, "§6("+main.getPlayers().size()+"/"+main.getMaxPlayers()+")");
			else if(main.isStatus(Gstatus.STARTING)) sign.setLine(2, "§6démarrage...");
			else if(main.isStatus(Gstatus.PLAYING)) sign.setLine(2, "§6en jeu !");
			else if(main.isStatus(Gstatus.FINISH)) sign.setLine(2, "§6("+main.getPlayers().size()+"/"+main.getMaxPlayers()+")");
		}
		
	}

}
