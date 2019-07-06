package fr.leroideskiwis.tpaplugin;

import org.bukkit.entity.Player;

import fr.leroideskiwis.tpaplugin.enums.Type;

public class TpRequest {

	private Player requester;
	private Player target;
	private Type type;
	
	public TpRequest(Player requester, Player target, Type type){
		this.requester = requester;
		this.target = target;
		this.type = type;
	}
	
	public Player getRequester(){
		return requester;
	}
	
	public Player getTarget(){
		return target;
	}
	
	private Type getType(){
		return type;
	}
	
}
