package fr.leroideskiwis.enderpearlbattle.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.enderpearlbattle.Main;
import fr.leroideskiwis.enderpearlbattle.eventscustom.PlayerJoinGameEvent;
import fr.leroideskiwis.enderpearlbattle.game.Gstatus;
import fr.leroideskiwis.enderpearlbattle.task.StartingTask;
import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("unused")
public class CommandEnderJoin implements CommandExecutor {
	
	
	private Main main;
	
	public CommandEnderJoin(Main main) {
		this.main = main;
	}
	
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("§cvous devez être joueur pour executer cette commande");
		} else {
			
			
			
			Player p = (Player)sender;
			
			if(args.length == 1) {
				p = Bukkit.getPlayer(args[0]);
				
				if(p == null) {
					((Player)sender).sendMessage("§cErreur : "+args[0]+" n'existe pas ou n'est pas connecté");
					return true;
				}
			}
			
			Bukkit.getPluginManager().callEvent(new PlayerJoinGameEvent(p));
			
			
		
		
	}
		
		return false;

	
	
}
}
