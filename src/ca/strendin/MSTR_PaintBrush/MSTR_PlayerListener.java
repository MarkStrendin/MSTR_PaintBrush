package ca.strendin.MSTR_PaintBrush;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class MSTR_PlayerListener implements Listener {
	public MSTR_PlayerListener(MSTR_PaintBrush mstr_PaintBrush) {}	
	
	@EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        MSTR_BlockCache.removePlayerCache(event.getPlayer()); 
    }
	
	private boolean isPlayerHoldingInfoTool(Player player) {
		if (player.getItemInHand().getTypeId() > 0) {
			ItemMeta itemInHandMeta = player.getItemInHand().getItemMeta();        	
        	if (itemInHandMeta.hasDisplayName()) {
        		if (itemInHandMeta.getDisplayName().contentEquals(MSTR_Config.infoToolName)) {        		
    				if (player.getItemInHand().getTypeId() == MSTR_Config.infoToolID) {
    					return true;    					
    				}
        		}
        	}			
		}		
		return false;		
	}
	
	private boolean isPlayerHoldingPaintbrushTool(Player player) {
		if (player.getItemInHand().getTypeId() > 0) {
			ItemMeta itemInHandMeta = player.getItemInHand().getItemMeta();        	
        	if (itemInHandMeta.hasDisplayName()) {
        		if (itemInHandMeta.getDisplayName().contentEquals(MSTR_Config.paintbrushToolName)) {        		
    				if (player.getItemInHand().getTypeId() == MSTR_Config.paintBrushToolID) {
    					return true;    					
    				}
        		}
        	}			
		}		
		return false;
	}
	
	@EventHandler        
    public void onPlayerInteract(PlayerInteractEvent event) { 
    	Player player = event.getPlayer();
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
        	if (isPlayerHoldingPaintbrushTool(player)) {
        		if (MSTR_Permissions.canUsePaintBrush(player)) {	                            
                    MSTR_BlockCache.storeThisBlock(player, event.getClickedBlock());
                    event.setCancelled(true);	                            
                }        		
        	} else if(isPlayerHoldingInfoTool(player)) {
        		if (MSTR_Permissions.canUseInfoTool(player)) {
        			MSTR_InfoTool.infoToolHere(player, event.getClickedBlock());
                    event.setCancelled(true);	    
        		}
        	}
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
        	if (isPlayerHoldingPaintbrushTool(player)) {
        		if (MSTR_Permissions.canUsePaintBrush(player)) {	                            
                    MSTR_BlockCache.setThisBlockToStoredBlock(player, event.getClickedBlock());
                    event.setCancelled(true);	                            
                }        		
        	} else if(isPlayerHoldingInfoTool(player)) {
        		if (MSTR_Permissions.canUseInfoTool(player)) {
        			MSTR_InfoTool.infoToolHere(player, event.getClickedBlock());
                    event.setCancelled(true);	    
        		}
        	}        	
        } 
	} 	

}
