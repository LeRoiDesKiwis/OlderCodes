package fr.leroideskiwis.tntrun.listener.task;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.tntrun.Main;
import fr.leroideskiwis.tntrun.game.GStatus;

public class StartingTask extends BukkitRunnable {

	private Main main;
	private int rebours = 10;
	private Location spawn;
	
	public StartingTask(Main main, Location spawn) {
		this.main = main;
		this.spawn = spawn;
	}
	
	@Override
	public void run() {
		
		/*
		 * v�rification du nombre de joueur : si il n'est pas �gal au nombre maximum, alors cancel
		 * 
		 */
		
		if(main.getPlayers().size() < main.getMaxPlayers()){
			
			main.getPlayers().forEach(p -> p.sendMessage("�cPas assez de joueurs : compte a rebours arret� !"));
			cancel();
			return;
		}
		
		/*
		 * decr�mentation de la variable rebours
		 */
		
		rebours--;
		
		/*
		 * si la variable rebours est � 0, lancer le jeu
		 * 
		 */
		
		main.getPlayers().forEach(p -> p.sendMessage(rebours+""));
		
		if(rebours == 0){
			main.setStatus(GStatus.PLAYING);
			
			main.getPlayers().forEach(p -> {
				
				p.teleport(spawn);
				p.sendMessage("�aQue le meilleur gagne !");
				
				
				
			});
			
			BukkitRunnable gameCycle = new GameCycle(main);
			BukkitRunnable enlevageDeSableSousPied = new SandRemoveTask(main);
			enlevageDeSableSousPied.runTaskTimer(main, 20*3, 7);
			gameCycle.runTaskTimer(main, 0, 1);
			cancel();
			
		}
		
	}

}