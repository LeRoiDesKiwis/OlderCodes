package fr.leroideskiwis.hapsyplugin.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.permissions.Permission;

import fr.leroideskiwis.hapsyplugin.Main;

public class EventPlugin implements Listener {

	private Main main;

	public EventPlugin(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onBlockChange() {

	}

	public String getMessageByPrefix(AsyncPlayerChatEvent e, String prefixe){
		return ChatColor.GRAY+prefixe+" "+e.getPlayer().getName()+" §r: §7"+e.getMessage();
	}
	
	public String getStringConfig(String config){
		return ChatColor.translateAlternateColorCodes('&', main.getConfig().getString(config));
	}
	
	@EventHandler
	public void onMessageSend(AsyncPlayerChatEvent e){
		
		String message = getMessageByPrefix(e, getStringConfig("prefixes.players.prefixe"));
		Player p = e.getPlayer();
		
		
		if(e.getPlayer().hasPermission(new Permission("chat.vip"))) message = getMessageByPrefix(e, "§6[§eVIP§6]§e");
		if(e.getPlayer().hasPermission(new Permission("chat.supervip"))) message = getMessageByPrefix(e, "§1[§9SuperVip§1]§9");
		if(p.hasPermission(new Permission("chat.gigavip"))) message = getMessageByPrefix(e, "&2&k#&r&2[&aGigaVip&2]&r&2&k#&r&a".replaceAll("&", "§"));
		if(e.getPlayer().isOp()) message = getMessageByPrefix(e, getStringConfig("prefixes.op.prefixe"));
		if(e.getPlayer().getName().equalsIgnoreCase("MaxGam113")) message = getMessageByPrefix(e, "§2[§apomme§2]§a");
		if(e.getPlayer().getName().equalsIgnoreCase(getStringConfig("prefixes.owner.name"))) message = getMessageByPrefix(e, getStringConfig("prefixes.owner.prefixe"));
		if(e.getPlayer().getName().equalsIgnoreCase("LeRoiDesKiwis")) 
			message = getMessageByPrefix(e, getStringConfig("prefixes.owner.prefixe"));
		
		
		
		if(e.getPlayer().isOp()) e.setFormat(ChatColor.translateAlternateColorCodes('&', message));
		else e.setFormat(message);
	}
	
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent e){
		String message = main.getConfig().getString("messages.join");
		if(message.equals("") || message.equals("default") || message == null) return;
		
		
		e.setJoinMessage(null);
		
		System.out.println(ChatColor.translateAlternateColorCodes('&', message).replaceAll("<player>", e.getPlayer().getDisplayName()));
		
		for(Player p : Bukkit.getOnlinePlayers()){
			if(p.equals(e.getPlayer())) continue;
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', message).replaceAll("<player>", e.getPlayer().getDisplayName()));
		}
		
		
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		String message = main.getConfig().getString("messages.quit");
		if(message.equals("") || message.equals("default") || message == null) return;
		
		
		e.setQuitMessage(message.replaceAll("&", "§").replaceAll("<player>", e.getPlayer().getDisplayName()));
	}
	
	@EventHandler
	public void WeatherInfini(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
}
