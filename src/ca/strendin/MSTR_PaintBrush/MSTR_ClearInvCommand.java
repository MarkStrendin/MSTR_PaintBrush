package ca.strendin.MSTR_PaintBrush;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MSTR_ClearInvCommand implements CommandExecutor {

	public MSTR_ClearInvCommand(MSTR_PaintBrush mstr_PaintBrush) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {
		if (sender instanceof Player) {
			Player player = (Player)sender;
			player.getInventory().clear();
	        MSTR_Comms.logThis(player.getDisplayName() + " cleared their inventory");
	        MSTR_Comms.sendPlayer(player,"Inventory cleared!");
		} else {
			MSTR_Comms.sendtoCommandSender(sender, "You don't even have an inventory!");
		}
        return true;			
	}

}
