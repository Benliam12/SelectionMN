package ca.mobnetwork.selectionmn.session;

import java.util.UUID;

import ca.mobnetwork.selectionmn.selection.Selection;
/**
 * Class of individual Session
 * @author Benliam12
 * @version 1.0
 *
 */
public class Session 
{
	/**
	 * Name of the session's owner
	 */
	private UUID UUID;
	private Clipboard clipboard;
	private Selection selection;
	private long time;
	private boolean online;
	
	
	/**
	 * Constructor
	 * 
	 * @param name The user name of the session's owner
	 */
	public Session(UUID UUID)
	{
		this.UUID = UUID;
	}
	
	public Session setOnline(boolean isonline)
	{
		this.online = isonline;
		return this;
	}
	
	public boolean isOnline()
	{
		return this.online;
	}
	
	public Session setTime(long time)
	{
		this.time = time;
		return this;
	}
	
	public long getTime()
	{
		return this.time;
	}
	
	/**
	 * Get the name of the session's owner
	 * 
	 * @return the name
	 */
	public UUID getUUID()
	{
		return this.UUID;
	}
	
	/**
	 * Getting specific Object stored in the infos HashMap
	 * 
	 * @param key Key of the HashMap to get the Object wanted
	 * @return The Object asked, returns null if the object does not exists
	 */
	public Clipboard getClipboard()
	{
		return this.clipboard;
	}
	
	public Selection getSelection()
	{
		if(this.selection == null)
		{
			this.selection = new Selection(this);
		}
		return this.selection;
	}
	
	/**
	 * Set informations
	 * 
	 * @param key Key where the Object will be stored in the session
	 * @param object Object to store
	 */
	public Clipboard setClipboard(Clipboard clipboard)
	{
		this.clipboard = clipboard;
		return clipboard;
	}
	
	public Selection setSelection(Selection selection)
	{
		this.selection = selection;
		return selection;
	}
}
