package net.aesircraft.AesirRPG;

import java.util.List;
import java.util.Random;
import org.bukkit.World;
import org.bukkit.entity.Player;


public class RPGWorlds
{
	public static void setScheduler(){
		AesirRPG.getStatic().getServer().getScheduler().scheduleAsyncRepeatingTask(AesirRPG.getStatic(), new Runnable() {

			@Override
			 public void run() {
		     send();
		 }
		}, 0L, 36000L);
	}
	public static void send(){
		World world=AesirRPG.getStatic().getServer().getWorld("world");
		List players=world.getPlayers();
		Random random=new Random(world.getWeatherDuration()+players.size()+world.getFullTime());
		Player player;
		int chance=random.nextInt(2);
		if (random.nextInt(199)==129){
			player=(Player)players.get(random.nextInt(players.size()));
			if (chance==0){
				player.sendMessage("§eYou feel a pusing void surround you.");
				player.sendMessage("§eA feeling of urgency rushes over you");
				player.sendMessage("§eas you feel the urge to vanquish");
				player.sendMessage("§eyour enemies in §bThe Ender§e.");
				player.sendMessage("§2Visit §bThe End§2 by taking a portal in");	
				player.sendMessage("§2§bYggdrassil§2 at /warp ygg.");
			}
			if (chance==1){
				player.sendMessage("§eYou feel a burning heat on the ground");
				player.sendMessage("§ebellow you and a feeling of dread washes");
				player.sendMessage("§eover you. The minions of the §bNether§e are");
				player.sendMessage("§emassing for war. You feel you must go and fight.");
				player.sendMessage("§2Visit §bThe Nether§2 by taking a portal in");	
				player.sendMessage("§2§bYggdrassil§2 at /warp ygg.");
			}
			if (chance==2){
				player.sendMessage("§eAs you observe your surroundings, a");
				player.sendMessage("§egreat shadow cast by the above §bSkyLands§e");
				player.sendMessage("§ewashes over you. A feeling of adventure");
				player.sendMessage("§edrives you to explore the floating valleys.");
				player.sendMessage("§2Visit §bThe SkyLands§2 by taking a portal in");	
				player.sendMessage("§2§bYggdrassil§2 at /warp ygg.");
			}
		}
			
	}
}
