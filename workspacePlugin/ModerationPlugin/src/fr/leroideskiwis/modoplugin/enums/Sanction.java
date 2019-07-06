package fr.leroideskiwis.modoplugin.enums;

public enum Sanction {

	WARN("warn"), MUTE("mute"), FREEZE("freeze"), KICK("kick"), BAN_OR_KICK_IF_OP("ban (kick si op)"), BAN("ban"), DEOP("deop");
	
	private String sanction;
	
	Sanction(String sanction){
		
		this.sanction = sanction;
		
	}
	
	public String getSanction(){
		return sanction;
	}
	
}
