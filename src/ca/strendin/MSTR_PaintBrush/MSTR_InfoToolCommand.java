package ca.strendin.MSTR_PaintBrush;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MSTR_InfoToolCommand implements CommandExecutor {

	public MSTR_InfoToolCommand(MSTR_PaintBrush mstr_PaintBrush) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {
		if (sender instanceof Player) {
			Player player = (Player)sender;
			if (MSTR_Permissions.canUsePaintBrush(player)) {
				givePlayerInfoTool(player);
			} else {
				MSTR_Comms.permDenyMsg(player);
			}
		} else {
			MSTR_Comms.sendtoCommandSender(sender, "Player command only!");
		}

		return true;
	}
	
	/*
     * Gives the player an info tool
     */
    public static void givePlayerInfoTool(Player thisPlayer) {
        ItemStack InfoToolItem = new ItemStack(MSTR_Config.infoToolID,(short)1,(byte)0);
        
        // Modify the item before sending it to the player
        ItemMeta meta = InfoToolItem.getItemMeta(); 
        
        // Rename the item
        meta.setDisplayName(MSTR_Config.infoToolName);        
        
        // Set the item's lore
        ArrayList<String> newLore = new ArrayList<String>();
        newLore.add("Click on a block to get information about it.");
        meta.setLore(newLore);
        
        // Commit changes to the item
        InfoToolItem.setItemMeta(meta);
        
        MSTR_Comms.sendPlayer(thisPlayer, "Giving Info tool: " + MSTR_Comms.itemColor + InfoToolItem.getType());
        MSTR_Comms.logThis("Giving " + thisPlayer.getDisplayName() + " a paintbrush tool (" + InfoToolItem.getType() + ")");
        
        thisPlayer.getInventory().addItem(InfoToolItem);
    }

}
