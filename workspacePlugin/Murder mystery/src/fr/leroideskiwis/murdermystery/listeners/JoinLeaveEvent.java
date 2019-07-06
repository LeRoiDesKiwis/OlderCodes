package fr.leroideskiwis.murdermystery.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.murdermystery.Main;
import fr.leroideskiwis.murdermystery.eventscustom.PlayerJoinGameEvent;
import fr.leroideskiwis.murdermystery.eventscustom.PlayerQuitGame;
import fr.leroideskiwis.murdermystery.game.GPlayer;
import fr.leroideskiwis.murdermystery.game.Gstatus;
import fr.leroideskiwis.murdermystery.tasks.StartingTask;

public class JoinLeaveEvent implements Listener {

	private Main main = Main.getInstance();

	@EventHandler
	public void onJoin(PlayerJoinGameEvent e) {
		Player p = e.getPlayer();
		if (main.isStatus(Gstatus.FINISH))
			main.setStatus(Gstatus.WAITING);
		if (!main.isStatus(Gstatus.WAITING)) {

			if (main.getPlayers().contains(p)) {
				e.getPlayer().sendMessage("§cvous êtes déjà dans une partie !");
				return;
			}

			p.setHealth(20.0);
			p.setFoodLevel(20);
			p.setGameMode(GameMode.ADVENTURE);
			main.getPlayers().add(p);
			main.getGPlayers().add(new GPlayer(p));

			p.getInventory().clear();

			for (Player player : main.getPlayers()) {
				player.sendMessage(ChatColor.YELLOW + p.getDisplayName() + " a rejoint la partie ! ("
						+ main.getPlayers().size() + "/" + main.getMaxPlayers() + ")");
			}

		}
		
		Location spawn = new Location(main.getDefaultWorld(), main.getConfig().getDouble("teleports.spawn.x"), main.getConfig().getDouble("teleports.spawn.y"), main.getConfig().getDouble("teleports.spawn.z"));
		
		p.teleport(spawn);
		
		BukkitRunnable startingTask = new StartingTask();
		startingTask.runTaskTimer(Main.getInstance(), 10, 20);
		
	}

	@EventHandler
	public void onQuit(PlayerQuitGame e) {

		Player p = (Player) e.getPlayer();

		if (!main.getPlayers().contains(p)) {
			p.sendMessage("§cvous n'êtes pas dans une partie !");
			return;
		}

		main.getPlayers().remove(p);

		p.sendMessage("§cvous avez quitté le jeu");

		for (Player player : main.getPlayers()) {
			player.sendMessage("§c" + p.getDisplayName() + " a quitté la partie ! (" + main.getPlayers().size() + "/"
					+ main.getMaxPlayers() + ")");
		}

		if (main.getPlayers().contains(p) && main.isStatus(Gstatus.PLAYING))
			main.eliminate(p);

		p.setGameMode(GameMode.SURVIVAL);

		p.teleport(main.getHubLocation());

	}

}
