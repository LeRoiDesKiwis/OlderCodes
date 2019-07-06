package fr.leroideskiwis.tntfly;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.tntfly.listener.Tntfly;

public class Main extends JavaPlugin implements Listener{

	/*@Override
	public void onEnable() {
		saveDefaultConfig();
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new Tntfly(this), this);
		pm.registerEvents(this, this);
		
	}*/
	
	
	public double getDoubleConfig(String string){
		return getConfig().getDouble(string);
		
	}
	
	
	//@EventHandler
	public void onInteract(PlayerInteractEvent e){
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null){
			BlockState blockState = e.getClickedBlock().getState();
			
			if(blockState instanceof Sign){
				Sign sign = (Sign)blockState;
				
				if(sign.getLine(0).equalsIgnoreCase("[teleport]") && sign.getLine(1).equalsIgnoreCase("tntfly")){
					
					Location hub = new Location(Bukkit.getWorld(getConfig().getString("hub.nameWorld")), getDoubleConfig("hub.x"), getDoubleConfig("hub.y"), getDoubleConfig("hub.z"));
					hub = new Location(Bukkit.getWorld("tntfly"), 0.0, 0.0, 0.0);
					
					e.getPlayer().teleport(hub);
					
				}
				
			}
			
		}
		
	}
	
}
