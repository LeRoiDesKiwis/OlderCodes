package fr.leroideskiwis.murdermystery;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.murdermystery.game.GPlayer;
import fr.leroideskiwis.murdermystery.game.Gstatus;
import fr.leroideskiwis.murdermystery.listeners.DamageEvents;
import fr.leroideskiwis.murdermystery.listeners.JoinLeaveEvent;

public class Main extends JavaPlugin {

	private List<Player> players = new ArrayList<>();
	private List<GPlayer> Gplayer = new ArrayList<>();
	
	
	
	public List<GPlayer> getGPlayers() {
		return Gplayer;
	}

	public List<Player> getPlayers() {
		return players;
	}

	private Gstatus status;
	
	public boolean isStatus(Gstatus status){
		return this.status == status;
	}

	public void setStatus(Gstatus status){
	this.status = status;
	}
	
	private static Main instance;
	
	public GPlayer foundGPlayer(Player p){
		
		for(GPlayer gp : Gplayer){
			
			if(gp.getPlayer().equals(p)) return gp;
			
		}
		
		return null;
		
	}
	/* TODO
	 * ON ENABLE
	 *
	 */
	
	@Override
	public void onEnable() {
		instance = this;
		setStatus(Gstatus.WAITING);
		
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new JoinLeaveEvent(), this);
		pm.registerEvents(new DamageEvents(), this);
		
	}

	public World getDefaultWorld(){
		return Bukkit.getWorld("world");
	}
	
	public static Main getInstance(){
		return instance;
	}
	
	public int getMaxPlayers(){
		return getConfig().getInt("maxplayers");
	}

	public void eliminate(Player p) {
		players.remove(p);
		p.sendMessage("§cVous êtes éliminé !");
		
		p.setGameMode(GameMode.SPECTATOR);
	}

	public Location getHubLocation() {
		return new Location(Bukkit.getWorld("world"), getConfig().getDouble("teleports.hub.x"), getConfig().getDouble("teleports.hub.y"), getConfig().getDouble("teleports.hub.z"));
	}

}
