package fr.leroideskiwis.murdermystery.tasks;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.murdermystery.Main;
import fr.leroideskiwis.murdermystery.game.GPlayer;
import fr.leroideskiwis.murdermystery.game.Role;
import net.md_5.bungee.api.ChatColor;

public class StartingTask extends BukkitRunnable {

	private int i = 10;
	
	@Override
	public void run() {
		
		if(Main.getInstance().getPlayers().size() != Main.getInstance().getMaxPlayers()){
			
			Main.getInstance().getPlayers().forEach(p -> p.sendMessage("§cCompte a rebours annulé ! trop peu de joueurs."));
			
			cancel();
			i = 10;
		}
		
		if(i == 10 || i <= 5){
			
			Main.getInstance().getPlayers().forEach(p -> p.sendMessage("§cDébut de la partie dans §e"+i));
			
		}
		
		
		if(i == 0){
			
			Main.getInstance().getPlayers().forEach(p -> p.sendMessage("§cLa partie a commencé ! Bonne chance a tous !"));
			
			Main.getInstance().getGPlayers().get(new Random().nextInt(Main.getInstance().getGPlayers().size())).setRole(Role.MURDER);
			
			GPlayer GP = Main.getInstance().getGPlayers().get(new Random().nextInt(Main.getInstance().getGPlayers().size()));
			
			while(GP.isRole(Role.MURDER)){
				GP = Main.getInstance().getGPlayers().get(new Random().nextInt(Main.getInstance().getGPlayers().size()));
			}
			
			GP.setRole(Role.DETECTIVE);
			
			BukkitRunnable gameCycle = new GameCycle();
			
			gameCycle.runTaskTimer(Main.getInstance(), 0, 1);
			
			Main.getInstance().getGPlayers().forEach(gp -> {
				
				String message = "§7Vous êtes §aInnocent !";
				if(gp.isRole(Role.MURDER)) {
					message = "§7Vous êtes §cMurder !";
					gp.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_SWORD));
				}
				
				if(gp.isRole(Role.DETECTIVE)) {
					message = "§7Vous êtes "+ChatColor.DARK_BLUE+" Détective !";
					gp.getPlayer().getInventory().addItem(new ItemStack(Material.BOW));
					gp.getPlayer().getInventory().addItem(new ItemStack(Material.ARROW));
				}
				
				gp.getPlayer().setPlayerListName("§kiiiiiiiiiiii");
				//gp.getPlayer().set
				gp.getPlayer().sendMessage(message);
				
			});
			
			cancel();
		}
		
		i--;
	}

}
