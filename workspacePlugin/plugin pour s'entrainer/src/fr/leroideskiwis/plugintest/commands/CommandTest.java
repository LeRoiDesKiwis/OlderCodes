package fr.leroideskiwis.plugintest.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.plugintest.Main;

public class CommandTest implements CommandExecutor {

	private Main main;
	
	public CommandTest(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		if(sender instanceof Player){
			
			Player p = (Player)sender;
			Location locp = p.getLocation();
			
			for(int x = 0; x < Integer.parseInt(args[0]); x++){
				for(int y = 0; y < Integer.parseInt(args[1]); y++){
					for(int z = 0; z < Integer.parseInt(args[2]); z++){
						
						Location loc = new Location(p.getWorld(), locp.getX()+x, locp.getY()+y, locp.getZ()+z);
						loc.getBlock().setType(Material.STONE);
						
					}
				}
			}
			
		} else {
			sender.sendMessage("§cErreur : vous devez etre un joueur pour executer cette commande !");
		}
		
		return false;
	}

}
