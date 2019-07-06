package fr.leroideskiwis.tntrun.listener.task;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.tntrun.Main;
import fr.leroideskiwis.tntrun.game.GStatus;

public class GameCycle extends BukkitRunnable {

	private Main main;
	
	public GameCycle(Main main) {
		this.main = main;
	}
	
	@Override
	public void run() {
		
		/*
		 * éliminer un joueur si il tombe dans le vide
		 * 
		 */
		
		main.getPlayers().forEach(p -> {
			
			
			
			
			if(p.getLocation().getY() < 4) main.eliminate(p);
			
			
			
			
		});
		
		/*
		 * terminer la partie si il ne reste qu'un joueur
		 * 
		 */
		
		
		if(main.getPlayers().size() == 1){
			
			
			
			Player winner = main.getPlayers().get(0);
			
			winner.sendMessage("§aFélicitation, vous avez gagné !");
			
			main.getPlayers().clear();
			
			main.setStatus(GStatus.FINISH);
			
			new BukkitRunnable(){

				@Override
				public void run() {
					
					Firework firework = winner.getWorld().spawn(winner.getLocation(), Firework.class);
					FireworkMeta metaf = firework.getFireworkMeta();
					metaf.addEffect(FireworkEffect.builder().withColor(Color.RED).withColor(Color.ORANGE).build());
					firework.setFireworkMeta(metaf);
					
				}
				
			}.runTaskTimer(main, 0, 10);
			cancel();
			
		}

	}

}
