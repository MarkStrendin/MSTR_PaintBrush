package ca.strendin.MSTR_PaintBrush;

import java.util.Hashtable;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class MSTR_BlockCache { // Storage for player block data
    private static Hashtable<Player,Block> PlayerBlockStorage = new Hashtable<Player,Block>();    
    
    public final static void removePlayerCache(Player player) {
        if (PlayerBlockStorage.containsKey(player)) {
            PlayerBlockStorage.remove(player);
        }
    }
    
   
    public final static void storeThisBlock(Player player, Block block) {
        
        if (MSTR_BlockTools.isPaintableBlock(block)) {
        	if (!MSTR_BlockTools.isItemOnBlacklist(block)) {        
	            if (PlayerBlockStorage.containsKey(player)) {
	                PlayerBlockStorage.remove(player);            
	            }
	            
	            PlayerBlockStorage.put(player, block);
	            
	            MSTR_Comms.logThis(player.getDisplayName() + " set their ink to " + block.getType() + " with data value " + block.getData());
	            MSTR_Comms.sendPlayer(player,"Ink set to: " + MSTR_Comms.itemColor + block.getType());	            
	        } else {
	        	MSTR_Comms.sendPlayerError(player, "Sorry, that block cannot be copied (blacklisted)");
	        }
        } else {
        	MSTR_Comms.sendPlayerError(player, "Sorry, that block cannot be copied (unsafe)");
        }
    }
    
    public final static void setThisBlockToStoredBlock(Player player, Block block ) {
        
        if (PlayerBlockStorage.containsKey(player)) {
            
            /*
             *  If the block could not be copied, chances are things will end badly if it
             *  is possible to paste over it, so only allow pasting over block types that
             *  are copyable.
             * 
             */
        	
            if (MSTR_BlockTools.isPaintableBlock(block)) {
            	if (!MSTR_BlockTools.isItemOnBlacklist(block)) {
	                Block copiedBlock = PlayerBlockStorage.get(player);
	                
	                if (MSTR_BlockTools.isPaintableBlock(copiedBlock)) {
	                    block.setType(copiedBlock.getType());
	                    block.setData(copiedBlock.getData());
	                }
            	} else {
            		MSTR_Comms.sendPlayerError(player, "Sorry, that block cannot be overwritten with this tool (blacklisted)");
            	}
            } else {
            	MSTR_Comms.sendPlayerError(player, "Sorry, that block cannot be overwritten with this tool (unsafe)");                
            }
            
        } else {
        	MSTR_Comms.sendPlayerError(player,"No block saved for you yet!");
        }        
    }

}
