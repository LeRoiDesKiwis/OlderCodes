package fr.leroideskiwis.banmanager.listener;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import fr.leroideskiwis.banmanager.Main;
import fr.leroideskiwis.banmanager.ban.Ban;


public class ListenerPlugin implements Listener {

	private Main main;
	
	public ListenerPlugin(Main main) {
		this.main = main;
	}


	
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		Player loginner = e.getPlayer();
		
		for(Ban ban : main.getBan()){
			if(loginner.equals(ban.getBanned())){
				e.setKickMessage("§cVous êtes banni définitivement du serveur par §e"+(ban.getBanner() != null ? ban.getBanner() : "la console")+" §cpour la raison suivante : §e"+ban.getReason());
				
			}
		}
	}
	
}
