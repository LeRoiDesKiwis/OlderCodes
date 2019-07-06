package fr.leroideskiwis.hapsyplugin.commands;

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

			TextComponent send = new TextComponent(main.getConfig().getString("privateMessages.send").replaceAll("&", "§").replaceAll("<target>", target.getDisplayName()).replaceAll("<sender>", p.getDisplayName()).replaceAll("<message>", msg.toString()));
			TextComponent receive = new TextComponent(main.getConfig().getString("privateMessages.receive").replaceAll("&", "§").replaceAll("<target>", target.getDisplayName()).replaceAll("<sender>", p.getDisplayName()).replaceAll("<message>", msg.toString()));
			
			receive.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD+"répondre à " +ChatColor.GREEN+ p.getName()).create()));
			receive.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg "+p.getName()+" "));
			
			p.spigot().sendMessage(send);
			target.spigot().sendMessage(receive);
			
		} else {
			sender.sendMessage("vous devez être un joueur pour executer cette commande !");
		}
		
		return true;
	}

}
