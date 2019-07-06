package fr.leroideskiwis.tpaplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.tpaplugin.Main;
import fr.leroideskiwis.tpaplugin.TpRequest;

public class CommandTpDeny implements CommandExecutor {

	private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
	
		if(main.senderIsPlayer(s)){
			
			Player p = (Player)s;
			
			TpRequest This = null;
			
			for(TpRequest tpr : main.tpRequests){
				
				if(tpr.getTarget().equals(p)){
					
					This = tpr;
					
				}
				
			}
			
			if(This == null){
				p.sendMessage("�cVous n'avez pas de demandes de t�l�portation en attente !");
				return true;
			} else {
				p.sendMessage("�7Demande de t�l�portation refus�e ! !");
				main.tpRequests.remove(This);
				This.getRequester().sendMessage("�c"+p.getName()+" �a refus�e votre demande de t�l�portation !");
			}
			
		}
		
		return false;
	}

}
