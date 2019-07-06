package fr.leroideskiwis.murdermystery.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.murdermystery.Main;
import fr.leroideskiwis.murdermystery.game.GPlayer;
import fr.leroideskiwis.murdermystery.game.Gstatus;
import fr.leroideskiwis.murdermystery.game.Role;
import net.md_5.bungee.api.ChatColor;

public class GameCycle extends BukkitRunnable {

	@Override
	public void run() {
		
		if(Main.getInstance().getPlayers().size() == 1){
			Main.getInstance().setStatus(Gstatus.FINISH);
			
			Player winner = Main.getInstance().getPlayers().get(0);
			
			GPlayer Gwinner = Main.getInstance().foundGPlayer(winner);
			Main.getInstance().getPlayers().clear();
			Main.getInstance().getGPlayers().clear();
			Bukkit.broadcastMessage("§eLe jeu Murder mystery est fini ! le "+(Gwinner.isRole(Role.MURDER) ? "§cMurder" : (Gwinner.isRole(Role.DETECTIVE) ? ChatColor.DARK_BLUE+"Détective" : (Gwinner.isRole(Role.INNOCENT) ? "Innocent" : null)))+ " a gagné !");
		}

	}

}
