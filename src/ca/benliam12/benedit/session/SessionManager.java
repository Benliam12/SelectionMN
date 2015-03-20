package ca.benliam12.benedit.session;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ca.benliam12.benedit.selection.Selection;


/**
 * Class managing sessions
 * @author Benliam12
 * @version 1.0
 */
public class SessionManager 
{
	/**
	 * Array of Sessions
	 */
	private HashMap<String,Session> sessions = new HashMap<>();
	
	/**
	 * Instance of SessionManager
	 */
	private static SessionManager instance = new SessionManager();
	
	/**
	 * Method to get Instance of SessionManager
	 * 
	 * @return Instance of SessionManager
	 */
	public static SessionManager getInstance()
	{
		return instance;
	}
	
	/**
	 * Constructor
	 */
	public SessionManager()
	{
		
	}
	
	/**
	 * Method to create sessions in case server has reloaded
	 */
	public void setup()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			this.addSession(p.getName());
			this.getSession(p.getName()).setInfo("bedit-selection", new Selection(this.getSession(p.getName())));
		}
	}
	
	/**
	 * Method to clear sessions
	 */
	public void clear()
	{
		for(Entry<String, Session> session : this.sessions.entrySet())
		{
			this.removeSession(session.getValue().getName());
			session = null;
		}
	}
	
	/**
	 * Method to get a specific session
	 * 
	 * @param key Username of the session's owner
	 * @return Object Session, returns null if session not found
	 */
	public Session getSession(String key)
	{
		return this.sessions.get(key);
	}
	
	/**
	 * Method to add a session
	 * 
	 * @param name UserName of the session's owner
	 */
	public void addSession(String name)
	{
		if(!this.sessions.containsKey(name))
		{
			this.sessions.put(name, new Session(name));
		} 
	}
	
	/**
	 * Method to remove a session
	 * 
	 * @param name Name of the session's owner
	 */
	public void removeSession(String name)
	{
		if(this.sessions.containsKey(name))
		{
			this.sessions.remove(name);
		}
	}
}
