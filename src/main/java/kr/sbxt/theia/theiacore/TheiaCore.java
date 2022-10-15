package kr.sbxt.theia.theiacore;

import kr.sbxt.theia.theiacore.util.Comp;
import org.bukkit.Server;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * THEIA Core
 * 코어
 */
public final class TheiaCore extends JavaPlugin
{
	private volatile static ItemFactory itemFactory;
	
	@Override
	public void onEnable()
	{
		getServer().getConsoleSender().sendMessage(Comp.t("Hello from THEIA Core!"));
		itemFactory = getServer().getItemFactory();
	}
	
	public static ItemFactory getItemFactory()
	{
		return itemFactory;
	}
}
