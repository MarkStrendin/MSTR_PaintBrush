package ca.strendin.MSTR_PaintBrush;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class MSTR_InfoTool {
	
	public final static void infoToolHere(Player player, Block block) {
    	try {
	    	MSTR_Comms.sendPlayer(player, ChatColor.GREEN + "(" + block.getX() + "," + block.getY() + "," + block.getZ() + ") " + ChatColor.YELLOW + block.getType().name() + ChatColor.GRAY + " (id: " + ChatColor.WHITE + block.getTypeId() + ChatColor.GRAY + ") (data: " + ChatColor.WHITE + block.getData() + ChatColor.GRAY + ")");   	
    	} catch (Exception ex) {
    		MSTR_Comms.sendPlayerError(player, "Error retreiving block data");    		
    	}
    }

}
