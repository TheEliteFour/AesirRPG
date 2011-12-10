package net.aesircraft.AesirRPG;

import org.bukkit.entity.Player;


public class Quester
{
	private Player player;
	
	public void load(Player player){
		this.player=player;
	}
	
	public Player getPlayer(){
		return player;
	}
	public String getQuest(){
		String quest="";
		
		return quest;
	}
	
	public boolean onQuest(){
		boolean bool=false;
		return bool;
	}
}
