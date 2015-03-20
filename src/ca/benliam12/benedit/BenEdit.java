package ca.benliam12.benedit;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ca.benliam12.benedit.commands.command;
import ca.benliam12.benedit.listener.PlayerListener;
import ca.benliam12.benedit.session.SessionManager;

/**
 * 
 * @author Benliam12
 * @version 1.0
 */
public class BenEdit extends JavaPlugin
{
	/**
	 * Console Logger
	 */
	public static Logger log = Logger.getLogger("minecraft");
	
	/**
	 * Instance of main class
	 */
	private static BenEdit instance;
	
	/**
	 * Getting instance of BenEdit
	 * 
	 * @return BenEdit instance
	 */
	
	public static BenEdit getInstance()
	{
		return instance;
	}
	/**
	 * Plugin enabled
	 */
	public void onEnable()
	{
		instance = this;
		SessionManager.getInstance().setup();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerListener(),this);
		command cmd = new command();
		getCommand("bset").setExecutor(cmd);
		getCommand("bpaste").setExecutor(cmd);
		getCommand("bcopy").setExecutor(cmd);
		log.info("[BenEdit] Enabled !");
	}
	
	/**
	 * Plugin disabled
	 */
	public void onDisable()
	{
		SessionManager.getInstance().clear();
	}
}
