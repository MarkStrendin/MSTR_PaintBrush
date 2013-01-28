package ca.strendin.MSTR_PaintBrush;

import org.bukkit.entity.Player;


public class MSTR_Permissions {
        
    private static boolean hasPermission(Player thisplayer, String permName) {
        return thisplayer.hasPermission(permName);
    }
    
    public static boolean canClearInventory(Player player) {
        return hasPermission(player,"mstr_paintbrush.clearinv");         
    }
    
    public static boolean canUsePaintBrush(Player player) {
        return hasPermission(player,"mstr_paintbrush.paintbrush");         
    }
    
    public static boolean canUseInfoTool(Player player) {
        return hasPermission(player,"mstr_paintbrush.infotool");         
    }
    
    public static boolean canReloadConfig(Player player) {
        return hasPermission(player,"mstr_paintbrush.reload");         
    }
}
