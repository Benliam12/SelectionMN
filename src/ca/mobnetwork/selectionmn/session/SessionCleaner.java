package ca.mobnetwork.selectionmn.session;

import java.util.Map.Entry;
import java.util.UUID;

public class SessionCleaner extends Thread 
{
	private boolean running = true;
	private SessionManager sessionManager = SessionManager.getInstance();
	
	public void pause()
	{
		this.running = false;
	}
	
	public void run()
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
