package kr.sbxt.theia.theiacore;

import org.bukkit.Server;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * THEIA Core Plugin
 */
public final class TheiaCore extends JavaPlugin
{
	private static volatile Server _currentServer;
	private static volatile TheiaCore _currentInstance;
	private static volatile ItemFactory _itemFactory;
	
	@Override
	public void onEnable()
	{
		_currentInstance = this;
		_currentServer = this.getServer();
		_itemFactory = _currentServer.getItemFactory();
		
	}
	
	/**
	 * JAVADOC in English
	 * @return 
	 */
	public static TheiaCore getInstance()
	{
		return _currentInstance;
	}
	
	/**
	 * JAVADOC with ...
	 * @return 
	 */
	public static Server getCurrentServer()
	{
		return _currentServer;
	}
	
	public static ItemFactory getItemFactory()
	{
		return _itemFactory;
	}
}
