package fr.leroideskiwis.enderpearlbattle.task;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.enderpearlbattle.Main;
import fr.leroideskiwis.enderpearlbattle.game.Gstatus;

public class GameCycleTask extends BukkitRunnable{

	private Main main;
	
	public GameCycleTask(Main main) {
		this.main = main;
	}	
	
	@Override
	public void run() {
			try {
				
					for(Player player : main.getPlayers()){
						
						ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL);
						
						
						enderpearl.setAmount(16);
						
						
						player.getInventory().setItem(8, enderpearl);
						
						player.setGameMode(GameMode.ADVENTURE);
						
						if(player.getLocation().getBlockY() <= 0){
							main.eliminate(player);
						}
						
						if(main.getPlayers().size() == 1){
							main.setStatus(Gstatus.FINISH);
						} else if(main.getPlayers().size() == 0) {
							main.getDeadPlayers().forEach((p) -> p.sendMessage("§6égalité !"));
							main.setStatus(Gstatus.FINISH);
							cancel();
						}
						
					}
					
				if(main.isStatus(Gstatus.FINISH)){
				
				Bukkit.broadcastMessage("§6PARTIE TERMINEE ! FELICITATION A " + main.getPlayers().get(0).getName()+ " :D");
				Thread.sleep(main.secondsToMillis(4.5));
				main.getPlayers().get(0).setGameMode(GameMode.CREATIVE);
				main.getPlayers().get(0).teleport(main.getHubLocation());
				main.getPlayers().get(0).getInventory().clear();
				main.getDeadPlayers().forEach((p) -> p.teleport(main.getHubLocation()));
				main.getDeadPlayers().forEach((p) -> p.setGameMode(GameMode.CREATIVE));
				main.getReloadChests().cancel();
				
					
				
				
				main.getDeadPlayers().clear();
				main.getPlayers().clear();
				
				cancel();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			

		
	}

}
