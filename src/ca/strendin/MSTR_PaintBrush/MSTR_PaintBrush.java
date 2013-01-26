package ca.strendin.MSTR_PaintBrush;

import java.io.IOException;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MSTR_PaintBrush extends JavaPlugin {
	
	@Override
	public void onDisable() {
        MSTR_Comms.logThis(this.getDescription().getName() + " disabled"); 
	}

	@Override
	public void onEnable() {
		try {
			MSTR_Config.loadConfigFile(this);			
		} catch (IOException e) {
			MSTR_Comms.logThis("Unable to open config file");		
			try {
				MSTR_Config.createNewConfigFile(this);
			} catch (IOException e1) {
				MSTR_Comms.logThis(" Unable to create new log file!" + e1.getMessage());
			}
		}
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new MSTR_PlayerListener(this), this);
		
		getCommand("clearinv").setExecutor(new MSTR_ClearInvCommand(this));
		getCommand("paintbrush").setExecutor(new MSTR_PaintBrushToolCommand(this));
		getCommand("infotool").setExecutor(new MSTR_InfoToolCommand(this));
		getCommand("mpbreload").setExecutor(new MSTR_ReloadCommandCommand(this));
		
        MSTR_Comms.logThis(this.getDescription().getName() + " enabled");
	}

	@Override
	public void onLoad() {
        MSTR_Comms.logThis(this.getDescription().getName() + " loaded");
	}

}
