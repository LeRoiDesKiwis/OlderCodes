package fr.leroideskiwis.utilsplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.utilsplugin.Main;

public class CommandGod implements CommandExecutor {

	private Main main;

	public CommandGod(Main main){
		this.main = main;
	}
	
	public boolean sendMessage(Player p, String message){
		p.sendMessage(message);
		return true;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(main.SenderIsPlayer(s)){
			
			Player p = (Player)s;
			
			if(!p.isOp()){
				
				p.sendMessage("�cVous n'avez pas la permission d'executer cette commande !");
				
				return true;
			}
			
			if(main.getGods().containsKey(p) && args.length == 0){
				main.getGods().remove(p);
				p.sendMessage("�cmode Dieu d�sactiv� !");
			} else {
				
				Integer i = 0;
				
				if(args.length != 0){
				if(args[0].equalsIgnoreCase("full")) i = 1;
				else if(args.length >= 2 && args[1].equalsIgnoreCase("no") && args[0].equalsIgnoreCase("full")) i = 3;
				else if(args.length != 1 && args[0].equalsIgnoreCase("no")) i = 2;
				else return sendMessage(p, "�cd�sol�, nous n'avons pas compris votre argument :/");

				}
				
				main.getGods().put(p,  i);
				p.sendMessage("�amode Dieu activ� !");
			}
			
		}
		
		return false;
	}

}