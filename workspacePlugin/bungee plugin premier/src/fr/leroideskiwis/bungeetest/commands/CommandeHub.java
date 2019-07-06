package fr.leroideskiwis.bungeetest.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;


public class CommandeHub extends Command {

	public CommandeHub(String name) {
		super(name);
	}

	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer){
			
			ProxiedPlayer p = (ProxiedPlayer)sender;
			p.sendMessage(new TextComponent("�7t�l�portation en cours..."));
			p.connect(ProxyServer.getInstance().getServerInfo("lobby"));
			
		} else {
			sender.sendMessage(new TextComponent("�cvous devez etre joueur pour executer cette commande"));
		}
		
	}

}
