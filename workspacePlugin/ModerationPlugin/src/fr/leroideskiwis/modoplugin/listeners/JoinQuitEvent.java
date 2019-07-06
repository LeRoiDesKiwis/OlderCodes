package fr.leroideskiwis.modoplugin.listeners;

import fr.leroideskiwis.modoplugin.Main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class JoinQuitEvent implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		
		if(Main.getInstance().freezes.contains(e.getPlayer())){
			
			Bukkit.getBanList(BanList.Type.NAME).addBan(e.getPlayer().getName(), "Quitte pendant freeze", null, null);
			
		}
		
	}


	
}
