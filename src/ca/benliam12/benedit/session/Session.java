package ca.benliam12.benedit.session;

import java.util.HashMap;
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
	private String name;
	
	/**
	 * HashMap containing all Objects stored in the session
	 */
	private HashMap<String,Object> infos = new HashMap<String,Object>();
	
	/**
	 * Constructor
	 * 
	 * @param name The user name of the session's owner
	 */
	public Session(String name)
	{
		this.name = name;
	}
	
	/**
	 * Get the name of the session's owner
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Getting specific Object stored in the infos HashMap
	 * 
	 * @param key Key of the HashMap to get the Object wanted
	 * @return The Object asked, returns null if the object does not exists
	 */
	public Object getInfo(String key)
	{
		if(this.infos.containsKey(key))
		{
			return this.infos.get(key);
		}
		return null;
	}
	
	/**
	 * Set informations
	 * 
	 * @param key Key where the Object will be stored in the session
	 * @param object Object to store
	 */
	public void setInfo(String key, Object object)
	{
		this.infos.put(key, object);
	}
}
