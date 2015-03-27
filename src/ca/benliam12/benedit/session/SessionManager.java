package ca.benliam12.benedit.session;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


/**
 * Class managing sessions
 * @author Benliam12
 * @version 1.0
 */
public class SessionManager 
{
	private HashMap<UUID, Session> sessions = new HashMap<>();
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
	
	public SessionManager()
	{
		
	}
	
	/**
	 * Method to create sessions in case server has reloaded
	 */
	public void setup()
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			this.addSession(player.getUniqueId());
		}
	}
	
	/**
	 * Method to get a specific session
	 * 
	 * @param key Username of the session's owner
	 * @return Object Session, returns null if session not found
	 */
	public Session getSession(UUID UUID)
	{
		return this.sessions.get(UUID);
	}
	
	/**
	 * Method to add a session
	 * 
	 * @param name UserName of the session's owner
	 */
	public Session addSession(UUID UUID)
	{
		if(!this.sessions.containsKey(UUID))
		{
			Session session = this.sessions.put(UUID, new Session(UUID));
			return session;
		} 
		return null;
	}
	
	/**
	 * Method to remove a session
	 * 
	 * @param name Name of the session's owner
	 */
	public void removeSession(UUID UUID)
	{
		if(this.sessions.containsKey(UUID))
		{
			this.sessions.remove(UUID);
		}
	}
}
