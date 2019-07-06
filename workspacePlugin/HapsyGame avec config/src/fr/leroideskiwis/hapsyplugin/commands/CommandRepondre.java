package fr.leroideskiwis.hapsyplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.hapsyplugin.Main;
import net.md_5.bungee.api.ChatColor;

public class CommandRepondre implements CommandExecutor {

	private Main main;
	
	public CommandRepondre(Main main) {
		this.main = main;
	}
	
	public boolean sendMessage(Player target, String msg){
		target.sendMessage(msg);
		return true;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		
		if(sender instanceof Player){
			
			Player p = (Player)sender;
			if(!main.getReponse().containsKey(p)) return sendMessage(p, "§cvous n'avez personne a qui repondre !");
			
			StringBuilder msg = new StringBuilder();
			for(String part : args){
				msg.append(part+" ");
			}
			
			Player target = main.getReponse().get(p);

			String send = main.getConfig().getString("privateMessages.send").replaceAll("&", "§").replaceAll("<target>", target.getDisplayName().replaceAll("<sender>", p.getDisplayName())).replaceAll("<message>", msg.toString());
			String receive = main.getConfig().getString("privateMessages.receive").replaceAll("<target>", target.getDisplayName().replaceAll("<sender>", p.getDisplayName())).replaceAll("&", "§").replaceAll("<message>", msg.toString());
			
			p.sendMessage(send);
			target.sendMessage(receive);
			
		} else {
			sender.sendMessage("vous devez être un joueur pour executer cette commande !");
		}
		
		return true;
	}

}
