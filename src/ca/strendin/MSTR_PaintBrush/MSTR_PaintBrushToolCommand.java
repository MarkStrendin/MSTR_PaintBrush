package ca.strendin.MSTR_PaintBrush;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MSTR_PaintBrushToolCommand implements CommandExecutor {
	public MSTR_PaintBrushToolCommand(MSTR_PaintBrush mstr_PaintBrush) {}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {
		if (sender instanceof Player) {
			Player player = (Player)sender;
			if (MSTR_Permissions.canUsePaintBrush(player)) {
				givePlayerPaintbrushTool(player);
			} else {
				MSTR_Comms.permDenyMsg(player);
			}
		} else {
			MSTR_Comms.sendtoCommandSender(sender, "Player command only - what would you do with a paintbrush anyway?");
		}

		return true;
	}
	
	/*
     * Gives the player a paintbrush
     */
    public static void givePlayerPaintbrushTool(Player thisPlayer) {
        ItemStack PaintBrushToolItem = new ItemStack(MSTR_Config.paintBrushToolID,(short)1,(byte)0);
        
        // Modify the item before sending it to the player
        ItemMeta meta = PaintBrushToolItem.getItemMeta(); 
        
        // Rename the item
        meta.setDisplayName(MSTR_Config.paintbrushToolName);        
        
        // Set the item's lore
        ArrayList<String> newLore = new ArrayList<String>();
        newLore.add("Left click to store a block.");
        newLore.add("Right click to replace blocks with");
        newLore.add("the block you've stored.");
        meta.setLore(newLore);
        
        // Commit changes to the item
        PaintBrushToolItem.setItemMeta(meta);
        
        MSTR_Comms.sendPlayer(thisPlayer, "Giving Paintbrush tool: " + MSTR_Comms.itemColor + PaintBrushToolItem.getType());
        MSTR_Comms.logThis("Giving " + thisPlayer.getDisplayName() + " a paintbrush tool (" + PaintBrushToolItem.getType() + ")");
        
        thisPlayer.getInventory().addItem(PaintBrushToolItem);
    }

}
