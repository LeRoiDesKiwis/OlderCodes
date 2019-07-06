package fr.leroideskiwis.utilsplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.leroideskiwis.utilsplugin.Main;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_9_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle.EnumTitleAction;

public class CommandBroadcast implements CommandExecutor {

	private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(s instanceof Player){
			if(!((Player)s).isOp()){
				s.sendMessage("�cVous n'avez pas la permission d'executer cette commande !");
				return true;
			}
		}
		
		if(args.length == 0){
			s.sendMessage("�cIl doit y avoir plus de 1 arguments !");
			return true;
		}
		
		StringBuilder build = new StringBuilder();
		
		for(String part : args){
			build.append(part+" ");
		}
		
		String bc = ChatColor.translateAlternateColorCodes('&', build.toString());
		IChatBaseComponent titlec = ChatSerializer.a("{\"text\":\""+"�6Broadcast"+"\"}");
		IChatBaseComponent subtitlec = ChatSerializer.a("{\"text\":\""+"�e"+bc+"\"}");
		
		PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitlec);
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, titlec);
		
		
		for(Player p : Bukkit.getOnlinePlayers()){
			
			
			
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(subtitlePacket);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(titlePacket);
			
		}
		
		return false;
	}

}
