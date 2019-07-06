package fr.leroideskiwis.tntrun;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.tntrun.commands.ElimineCommand;
import fr.leroideskiwis.tntrun.commands.JoinCommand;
import fr.leroideskiwis.tntrun.commands.LeaveCommand;
import fr.leroideskiwis.tntrun.game.GStatus;
import fr.leroideskiwis.tntrun.listener.JoinLeaveEvent;

public class Main extends JavaPlugin{
	
	private GStatus status;
	private List<Player> players = new ArrayList<>();
	
	/*
	 * tous les getters
	 * 
	 */
	
	public List<Player> getPlayers(){ 
		return players; 
	}
	
	@Override
	public void onEnable() {
		
		/*
		 * gestion de la config
		 * 
		 */
		
		saveDefaultConfig();
		
		/*
		 * enregistrement des commandes et des listeners
		 * 
		 */
				
		PluginManager pm = getServer().getPluginManager();
		registerCommand("tntrun", new JoinCommand(this));
		registerCommand("leavetnt", new LeaveCommand(this));
		registerCommand("eliminetnt", new ElimineCommand(this));
		pm.registerEvents(new JoinLeaveEvent(this), this);
		
		
		/*
		 * configuration du jeu
		 * 
		 */
		
		
		setStatus(GStatus.WAITING);
		
		
	}
	
	public boolean sendMessage(Player p, String msg){
		p.sendMessage(msg);
		return true;
	}
	
	public boolean sendMessage(CommandSender s, String msg){
		s.sendMessage(msg);
		return true;
	}
	
	public void eliminate(Player p){
		p.sendMessage("§cVous avez êtez eliminé !");
		getPlayers().remove(p);
		p.setGameMode(GameMode.SPECTATOR);
	}
	
	public void registerCommand(String command, CommandExecutor executor){
		getCommand(command).setExecutor(executor);
	}
	
	public void setStatus(GStatus status){
		this.status = status;
	}
	
	public boolean isStatus(GStatus status){
		return this.status == status;
	}
	
	/**
	 * recupere le nombre de joueurs maximum dans la partie. configurable dans le config.yml
	 * 
	 */
	
	public int getMaxPlayers(){
		return getConfig().getInt("maxPlayers");
	}
	
	/**
	 * say "Vous devez être un joueur pour executer cette commande" if the sender isn't instance of Player
	 * 
	 * @param sender the sender of the message
	 * @return true is the sender is an instance of Player
	 */
	
	public boolean senderIsPlayer(CommandSender sender){
		
		if(!(sender instanceof Player)) sender.sendMessage("§cErreur : vous devez être un joueur pour executer cette commande !");
		
		return sender instanceof Player;
	}
	
}
