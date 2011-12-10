package net.aesircraft.AesirRPG;

import com.herocraftonline.dev.heroes.Heroes;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class AesirRPG extends JavaPlugin
{
	public static final Logger logger = Logger.getLogger("Minecraft");
	private static AesirRPG instance = null;
	public static boolean heroes=false;
	public static Heroes hero;
	public static AesirRPG getStatic()    
	{
		return instance;
	}
	private void setStatic()
	{
		instance = this;
	}

	@Override
	public void onDisable()
	{
		logger.info("AesirRPG is INACTIVE!");
	}

	@Override
	public void onEnable()
	{
		setStatic();
		PluginManager pm = getServer().getPluginManager();
		if (pm.isPluginEnabled("Hereos")){
		heroes=true;
		hero=(Heroes) pm.getPlugin("Heroes");
	    }
		logger.info("AesirRPG is ACTIVE!");
		RPGWorlds.setScheduler();
	}
	
}
