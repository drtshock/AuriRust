package me.shock.rust;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

	
	protected FileConfiguration config;
	File file;
	
	public static Main plugin;
	
	public void onEnable()
	{
		
		PluginManager pm = getServer().getPluginManager();
		
		loadConfig();
		saveConfig();
	    pm.registerEvents(new RainListener(this), this);
	    
	    
	}
	
	public void onDisable()
	{
		
	}
	
	private void loadConfig()
	{
		this.file = new File(getDataFolder() + "/config.yml");
		
		getConfig().addDefault("Damage.Rain", 5);
		getConfig().addDefault("Damage.Water", 5);
		getConfig().addDefault("Damage.Swing", 1);
		getConfig().addDefault("Message.Swing", "Stop swinging for nothing!");
		
		if (!this.file.exists())
		{	
			getConfig().options().copyDefaults(true);
		}
	}
	
}
