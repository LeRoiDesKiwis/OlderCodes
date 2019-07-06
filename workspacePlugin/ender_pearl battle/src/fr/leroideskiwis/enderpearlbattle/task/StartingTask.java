package fr.leroideskiwis.enderpearlbattle.task;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.enderpearlbattle.Main;
import fr.leroideskiwis.enderpearlbattle.game.Gstatus;

public class StartingTask extends BukkitRunnable {

	int i = 10;
	private Main main;

	public StartingTask(Main main) {
		this.main = main;
	}

	@Override
	public void run() {

		try {
			if (main.isStatus(Gstatus.STARTING)) {

				main.getPlayers().forEach(p -> {
					p.setGameMode(GameMode.ADVENTURE);
					p.setLevel(i);
				});

				if (main.getMaxPlayers() + 3 > main.getPlayers().size()) {

					main.getPlayers().forEach(p -> {
						p.sendMessage("§cpas assez de joueurs pour commencer la partie !");
						p.setLevel(0);

					});

					main.setStatus(Gstatus.WAITING);
					cancel();
				}

				main.getPlayers().forEach(p -> {

					if (i == 10 || i <= 5) {
						p.sendMessage("§6début de la partie dans " + i + "s");
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
					}
				});

				if (i == 1) {

					main.reloadChest();

					/*for (int i = 0; i < main.getLocationsSpawn().size(); i++) {
						System.out.println(i);
						for (int x = 0; x != 4; x++) {
							System.out.println(x);
							for (int y = 0; y != 10; y++) {
								System.out.println(y);
								for (int z = 0; z != 4; z++) {
									System.out.println(z);
									System.out.println("variable location enregistrement (+)");
									Location location = main.getLocationsSpawn().get(i).add(x, y, z);
									System.out.println(location);
									if (location.getBlock().getType() == Material.STAINED_GLASS || location.getBlock().getType() == Material.GLASS) {
										System.out.println("glass ou stained glass remplacée (franglais mdr)");
										main.getCages().put(location, location.getBlock());
										location.getBlock().setType(Material.AIR);

									}
									System.out.println("variable location enregistrement (-)");
									location = main.getLocationsSpawn().get(i).subtract(x, y, z);

									if (location.getBlock().getType() == Material.STAINED_GLASS || location.getBlock().getType() == Material.GLASS) {
										System.out.println("glass ou stained glass remplacée (franglais mdr)");
										main.getCages().put(location, location.getBlock());
										location.getBlock().setType(Material.AIR);

									}
								}
							}
						}
						
					}*/
					
					main.getPlayers().forEach(p -> {
						
						p.getLocation().subtract(0, 1, 0).getBlock().setType(Material.AIR);
					
					});

					main.setStatus(Gstatus.PLAYING);
					main.getReloadChests().runTaskTimer(main, 30 * 20, 30 * 20);
					new GameCycleTask(main).runTaskTimer(main, 0, 1);
					cancel();

				}

				i--;
			}
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getClass());

		}

	}
}
