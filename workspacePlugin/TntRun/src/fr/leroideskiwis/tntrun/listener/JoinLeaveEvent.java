package fr.leroideskiwis.tntrun.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.tntrun.Main;
import fr.leroideskiwis.tntrun.eventcustom.PlayerJoinGameEvent;
import fr.leroideskiwis.tntrun.eventcustom.PlayerQuitGame;
import fr.leroideskiwis.tntrun.game.GStatus;
import fr.leroideskiwis.tntrun.game.GamePlayer;
import fr.leroideskiwis.tntrun.listener.task.StartingTask;
import net.md_5.bungee.api.ChatColor;

public class JoinLeaveEvent implements Listener {

	private Main main;
	
	public JoinLeaveEvent(Main main) {
		this.main = main;
	}
	
	public double configGetDouble(String string){
		return main.getConfig().getDouble(string);
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinGameEvent e){
		
		/*
		 * déclarations des variables
		 * 
		 */
		
		Player p = e.getPlayer();
		double[] coohub = {configGetDouble("locations.hub.x"), configGetDouble("locations.hub.y"),configGetDouble("locations.hub.z")};
		double[] coospawn = {configGetDouble("locations.spawn.x"), configGetDouble("locations.spawn.y"), configGetDouble("locations.spawn.z")};
		GamePlayer gp = e.getGamePlayer();
		Location hub = new Location(p.getWorld(), coohub[0], coohub[1], coohub[2]);
		Location spawn = new Location(p.getWorld(), coospawn[0], coospawn[1], coospawn[2]);
		
		
		/*
		 * conditions qui return une erreur
		 * 
		 */
		
		if(!main.isStatus(GStatus.WAITING) && !main.isStatus(GStatus.FINISH)){
			p.sendMessage("§cErreur : le jeu a déjà commencé !");
			return;
		}
		
		if(main.getPlayers().contains(p)){
			p.sendMessage("§cErreur : vous êtes déjà en jeu");
			return;
		}
		
		/*
		 * teleportation au hub
		 * 
		 */
		
		p.teleport(hub);
		p.sendMessage("§aVous etes en train d'attendre au hub du tntrun");
		main.getPlayers().add(p);
		main.getPlayers().forEach(p2 -> p2.sendMessage(ChatColor.YELLOW+p.getName() + " a rejoint la partie ! (<player>/<maxplayer>)".replaceAll("<maxplayer>", main.getMaxPlayers()+"").replaceAll("<player>", ""+main.getPlayers().size())));
		
		/*
		 * lancement du jeu si le nombre de joueur maximum est atteint
		 * 
		 */
		
		if(main.getPlayers().size() == main.getMaxPlayers()){
			main.setStatus(GStatus.STARTING);
			
			BukkitRunnable runnable = new StartingTask(main, spawn);
			runnable.runTaskTimer(main, 1, 20);
		}
		
		p.getInventory().clear();
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitGame e){
		
		Player p = e.getPlayer();
		GamePlayer gp = e.getGamePlayer();
		
		p.sendMessage("§cVous avez quitté la partie !");
		main.setStatus(GStatus.WAITING);
		
		main.getPlayers().remove(p);
		main.getPlayers().forEach(p2 -> p2.sendMessage("§c"+p.getName() + " a quitté la partie ! (<player>/<maxplayer>)".replaceAll("<maxplayer>", main.getMaxPlayers()+"").replaceAll("<player>", ""+main.getPlayers().size())));
		
	}
	
}
