package fr.leroideskiwis.hapsyplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.hapsyplugin.Main;
import net.md_5.bungee.api.ChatColor;

public class CommandMsg implements CommandExecutor {

	private Main main;
	
	public CommandMsg(Main main) {
		this.main = main;
	}

	private boolean sendMessage(Player p, String message){
		p.sendMessage(message);
		return true;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		
		if(sender instanceof Player){
			Player p = (Player) sender;
			
			if(args.length <= 1){
				p.sendMessage("§cvous devez mettre 2 arguments au minimum !");
				return true;

				}
			
				Player target = Bukkit.getPlayer(args[0]);
			
				if(target == null) return sendMessage(p, args[0]+" n'est pas connectée");
			
				StringBuilder msg = new StringBuilder();
			
				for(String part : args){
				if(part.equals(args[0])) continue;
				msg.append(part+" ");
				}
				
				String send = main.getConfig().getString("privateMessages.send").replaceAll("<target>", target.getDisplayName().replaceAll("<sender>", p.getDisplayName())).replaceAll("<message>", msg.toString());
				String receive = main.getConfig().getString("privateMessages.receive").replaceAll("<target>", target.getDisplayName().replaceAll("<sender>", p.getDisplayName())).replaceAll("<message>", msg.toString());
				
				p.sendMessage("§7["+ChatColor.GOLD+"Me -> "+p.getDisplayName()+"§7]"+" : "+msg.toString());
				target.sendMessage("§7["+ChatColor.GOLD+p.getDisplayName()+" -> Me§7] "+" : "+msg.toString());
				main.getReponse().remove(p);
				main.getReponse().put(target, p);
				//target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0f, 2.0f);
				
			
			
		} else {
			sender.sendMessage("§cvous devez etre un joueur pour executer cette commande");
		}
		
		return false;
	}

}
