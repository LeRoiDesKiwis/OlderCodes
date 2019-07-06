package fr.leroideskiwis.tpaplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.tpaplugin.Main;
import fr.leroideskiwis.tpaplugin.TpRequest;
import fr.leroideskiwis.tpaplugin.enums.Type;

public class CommandTpa implements CommandExecutor {
	
	private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String arg2, String[] args) {
		
		if(main.senderIsPlayer(s)){
			
			Player requester = (Player)s;
			Player target = Bukkit.getPlayer(args[0]);
			
			if(target == null){
				requester.sendMessage("�cLe joueur \""+args[0]+"\" n'existe pas");
			} else {
				
				if(target.equals(requester)){
					requester.sendMessage("�cVous ne pouvez pas vous envoyer de demande de t�l�portation a vous m�me !");
					return true;
				}
				
				main.tpRequests.add(new TpRequest(requester, target, Type.TPA));
				
				target.sendMessage("�c"+requester.getName()+" �7vous demande S'il peut se t�l�porter vers vous !");
				target.sendMessage("�7Faites �c/tpyes ou /tpaccept �7Pour accepter la demande de t�l�portation");
				target.sendMessage("�7Faites �c/tpdeny ou /tpno �7pour la refuser");
				
				requester.sendMessage("�7Votre demande de t�l�portation a bien �t� envoy�e !");
				
			}
			
		}
		
		return false;
	}

}