package fr.leroideskiwis.utils;


import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R1.Packet;
import net.minecraft.server.v1_9_R1.PacketPlayOutResourcePackSend;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle.EnumTitleAction;


public class SpigotUtils {

	private JavaPlugin plugin;
	
	public SpigotUtils(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public void sendTitle(Player p, String title, String subtitle){
		IChatBaseComponent titlec = ChatSerializer.a("{\"text\":\""+title+"\"}");
		IChatBaseComponent subtitlec = ChatSerializer.a("{\"text\":\""+subtitle+"\"}");
		
		PacketPlayOutTitle subtitlePacket = null;
		
		if(subtitle != null) subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitlec);
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, titlec);
		
		sendPacket(p, titlePacket);
		if(subtitlePacket != null) sendPacket(p, subtitlePacket);
		
		
	}
	
	
	
	public void sendPacket(Player p, Packet<?> packet){
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
	
}
