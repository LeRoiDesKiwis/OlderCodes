package fr.leroideskiwis.banmanager.ban;

import org.bukkit.plugin.Plugin;

public class Ban {

	private String reason;
	private Plugin main;
	private String banned;
	private String banner;
	
	public Ban(Plugin main, String StringOfPlayerBanned, String StringOfPlayerBanner, String reason) {
		this.main = main;
		this.reason = reason;
		this.banned = StringOfPlayerBanned;
		this.banner = StringOfPlayerBanner;
		
	}

	public String getReason() {
		return reason;
	}

	public String getBanned() {
		return banned;
	}

	public String getBanner() {
		return banner;
	}
	
	
	
	
}
