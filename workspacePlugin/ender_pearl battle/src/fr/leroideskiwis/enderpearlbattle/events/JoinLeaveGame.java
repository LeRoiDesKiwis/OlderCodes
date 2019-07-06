package fr.leroideskiwis.enderpearlbattle.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.enderpearlbattle.Main;
import fr.leroideskiwis.enderpearlbattle.eventscustom.PlayerJoinGameEvent;
import fr.leroideskiwis.enderpearlbattle.eventscustom.PlayerQuitGame;
import fr.leroideskiwis.enderpearlbattle.game.Gstatus;
import fr.leroideskiwis.enderpearlbattle.task.StartingTask;
import net.md_5.bungee.api.ChatColor;

public class JoinLeaveGame implements Listener {

	private Main main;

	public JoinLeaveGame(Main main) {
		this.main = main;
	}

	@EventHandler

	public void onJoinGame(PlayerJoinGameEvent e) {

		Player p = e.getPlayer();
		if (main.isStatus(Gstatus.FINISH))
			main.setStatus(Gstatus.WAITING);
		if (main.getMaxPlayers() != main.getPlayers().size() && !main.isStatus(Gstatus.PLAYING)) {

			if (main.getPlayers().contains(p)) {
				e.getPlayer().sendMessage("§cvous êtes déjà dans une partie !");
				return;
			}

			p.setHealth(20.0);
			p.setFoodLevel(20);
			p.setGameMode(GameMode.ADVENTURE);
			main.getPlayers().add(p);

			p.getInventory().clear();

			for (Player player : main.getPlayers()) {
				player.sendMessage(ChatColor.YELLOW + p.getDisplayName() + " a rejoint la partie ! ("
						+ main.getPlayers().size() + "/" + main.getMaxPlayers() + ")");
			}

			Location teleport = main.getLocationsSpawn().get(main.getPlayers().size());

			Location locglass = new Location(p.getWorld(), teleport.getX(), teleport.getY() - 1, teleport.getZ());
			
			if(locglass.getBlock().getType() == Material.AIR) locglass.getBlock().setType(Material.GLASS);
			
			p.getInventory().clear();
			
			/*main.getCages().forEach((location, block) -> {

				for (int x = 0; x != 5; x++) {
					for (int y = 0; y != 10; y++) {
						for (int z = 0; z != 5; y++) {
							
							Location locglasstemp = locglass2.add(x, y, z);
							
							if(locglasstemp.equals(location) && locglasstemp != null){
								
								locglasstemp.getBlock().setType(block.getType());
								
							}
							
							locglasstemp = locglass.subtract(x, y, z);
							
							if(locglasstemp.equals(location) && locglasstemp != null){
								
								locglasstemp.getBlock().setType(block.getType());
								
							}
							
						}
					}
				}

			});*/

			p.teleport(teleport);

			if (main.getPlayers().size() + 3 >= main.getMaxPlayers()) {
				main.setStatus(Gstatus.STARTING);

				BukkitRunnable starting = new StartingTask(main);

				starting.runTaskTimer(main, 0, 20);

			}
		} else
			p.sendMessage("§cLe jeu a déjà commencé !");

	}

	@EventHandler
	public void onLeaveGame(PlayerQuitGame e) {
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
