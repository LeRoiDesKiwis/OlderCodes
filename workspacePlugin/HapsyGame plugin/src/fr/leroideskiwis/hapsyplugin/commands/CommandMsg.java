package fr.leroideskiwis.hapsyplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.hapsyplugin.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

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
				
				TextComponent send = new TextComponent(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("privateMessages.send").replaceAll("<message>", msg.toString()).replaceAll("<target>", target.getDisplayName()).replaceAll("<sender>", p.getDisplayName())));
				TextComponent receive = new TextComponent(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("privateMessages.receive").replaceAll("<message>", msg.toString()).replaceAll("<target>", target.getDisplayName()).replaceAll("<sender>", p.getDisplayName())));
				
				receive.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD+"répondre à " +ChatColor.GREEN+ p.getName()).create()));
				receive.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg "+p.getName()+" "));
				
				p.spigot().sendMessage(send);
				target.spigot().sendMessage(receive);
				main.getReponse().remove(p);
				main.getReponse().put(target, p);
				//target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0f, 2.0f);
				
			
			
		} else {
			sender.sendMessage("§cvous devez etre un joueur pour executer cette commande");
		}
		
		return false;
	}

}
