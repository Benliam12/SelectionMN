package ca.mobnetwork.selectionmn.session;

import java.util.HashMap;
import java.util.TimerTask;
import java.util.UUID;
import java.util.Map.Entry;

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
	private Thread thread;
	private SessionCleaner sessionCleaner;
	
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
		this.sessionCleaner = new SessionCleaner();
		this.thread = new Thread(this.sessionCleaner);
		this.thread.start();
		
	}
	
	public void stop()
	{
		this.sessionCleaner.pause();
		this.thread.interrupt();
		
		this.thread = null;
		this.sessionCleaner = null;
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
	
	public HashMap<UUID,Session> getSessions()
	{
		return this.sessions;
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
	
	
	private class SessionTracker extends TimerTask
	{
		
		private boolean running = true;
		private SessionManager sessionManager = SessionManager.getInstance();
		
		public void pause()
		{
			this.running = false;
		}
		
		public void run()
		{
			synchronized (SessionManager.this)
			{
				while(this.running)
				{
					for(Entry<UUID, Session> sessionlist : sessionManager.getSessions().entrySet())
					{
						Session session = sessionlist.getValue();
						if(!session.isOnline())
						{
							long elapseTime = System.currentTimeMillis() - session.getTime();
							if(elapseTime < 300)
							{
								sessionManager.removeSession(sessionlist.getKey());
							}
						}
					}
					try{
						Thread.sleep(1000);
					}
					catch(InterruptedException ex)
					{
						this.running = false;
					}
				}
			}
		}
	}
}
