package fr.leroideskiwis.mysterychest.commands;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.mysterychest.Main;

public class CommandRegisterChest implements CommandExecutor {

	private Main main;
	
	public CommandRegisterChest(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		if(main.isPlayer(sender)){
			
			Player p = (Player)sender;
			
			Block targetBlock = p.getTargetBlock((Set<Material>) null, 3);
			
			if(targetBlock.getType() == Material.CHEST && targetBlock != null){
				
				Chest chest = (Chest)targetBlock.getState();
				
				chest.setCustomName(main.nameOfChest);
				
				p.sendMessage("§acoffre enregistré avec succès !");
				
			} else {
				p.sendMessage("§cErreur : le bloc visé n'est pas un coffre");
			}
			
		}
		
		
		return false;
	}

}
