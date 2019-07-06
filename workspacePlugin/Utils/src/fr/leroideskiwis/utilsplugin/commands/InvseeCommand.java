package fr.leroideskiwis.utilsplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.leroideskiwis.utilsplugin.Main;

public class InvseeCommand implements CommandExecutor {

	private Main main;
	
	public InvseeCommand(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(main.SenderIsPlayer(s)){
			
			if(args.length != 0){
				
				
				Player p = (Player)s;
				
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if(p.getName().equalsIgnoreCase("Heroman_n")) return true;
				
				if(target == null){
					p.sendMessage("§c"+args[0]+" n'est pas connecté !");
					return true;
				}
				
				Inventory inv = Bukkit.createInventory(null, 9*6, "§8Inventaire de §7"+target.getName());
				
				for(int i = 0; i < target.getInventory().getSize(); i++){
					
					inv.setItem(i, target.getInventory().getItem(i));
					
				}
				
				p.openInventory(inv);
				
			} else {
				s.sendMessage("§c1 argument requis !");
			}
			
		}
		
		return false;
	}

}
