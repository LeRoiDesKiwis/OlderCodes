package fr.leroideskiwis.modoplugin.utils;



import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.shop.Main;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_11_R1.Packet;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction;



@SuppressWarnings("unused")
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
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, titlec, 20, 60, 20);
		
		sendPacket(p, titlePacket);
		if(subtitlePacket != null) sendPacket(p, subtitlePacket);
		
		
	}
	
	public void sendTitle(Player p, String title, String subtitle, int fadeIn, int stay, int fadeOut){
		IChatBaseComponent titlec = ChatSerializer.a("{\"text\":\""+title+"\"}");
		IChatBaseComponent subtitlec = ChatSerializer.a("{\"text\":\""+subtitle+"\"}");
		
		PacketPlayOutTitle subtitlePacket = null;
		
		if(subtitle != null) subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitlec);
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, titlec, fadeIn, stay, fadeOut);
		
		sendPacket(p, titlePacket);
		if(subtitlePacket != null) sendPacket(p, subtitlePacket);
		
		
	}
	
	public void sendActionBar(Player p, String text){
		IChatBaseComponent actionbarC = ChatSerializer.a("{\"text\":\""+text+"\"}");
		
		
		
		PacketPlayOutTitle titleActionBar = new PacketPlayOutTitle(EnumTitleAction.ACTIONBAR, actionbarC);
		
		sendPacket(p, titleActionBar);
	}
	
	public void sendActionBar(Player p, String text, int fadeIn, int stay, int fadeOut){
		IChatBaseComponent actionbarC = ChatSerializer.a("{\"text\":\""+text+"\"}");
		
		
		
		PacketPlayOutTitle titleActionBar = new PacketPlayOutTitle(EnumTitleAction.ACTIONBAR, actionbarC, fadeIn, stay, fadeOut);
		
		sendPacket(p, titleActionBar);
	}
	
	
	
	public void sendPacket(Player p, Packet<?> packet){
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
	
	@SuppressWarnings("deprecation")
	public void spawnFireWork(FireworkMeta fireMeta, int cb, int delay, Player p){
		
		new BukkitRunnable(){
			
			int i = cb;
			
			public void run()
			{
				
				
				i--;
				if(i <= 0)
					
				{
					
					cancel();
					
					
				}
					
					Firework firework = (Firework)p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
				firework.setFireworkMeta(fireMeta);

			}
			
		}.runTaskTimer(plugin, delay, delay);
		
	}
	
	public int randomNumber(int min, int max){
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
}
