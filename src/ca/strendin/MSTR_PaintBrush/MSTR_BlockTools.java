package ca.strendin.MSTR_PaintBrush;

import org.bukkit.block.Block;

public class MSTR_BlockTools {
	
	/*
     * Checks to see if the item is on the allowed list
     */
    public static boolean isItemOnBlacklist(int itemID) {
        boolean returnMe = false;
        
        for (int checked_block : MSTR_Config.blacklistedBlocks) {
            if (checked_block == itemID) {
                returnMe = true;
            }
        }
        return returnMe;
    }  
    public static boolean isItemOnBlacklist(Block thisBlock) {
        boolean returnMe = false;

        int itemID = thisBlock.getTypeId();
        
        for (int checked_block : MSTR_Config.blacklistedBlocks) {
            if (checked_block == itemID) {
                returnMe = true;
            }
        }
        return returnMe;
    }
	
    public static boolean isPaintableBlock(Block block) {
        
        boolean returnMe = false;
        int blockid = block.getTypeId();
        
        for (int x : MSTR_Config.PaintSafeBlocks) {
            if (x == blockid) {
                returnMe = true;
            }
        }        
        return returnMe;     
    }
    public static boolean isPaintableBlock(int blockid) {
        boolean returnMe = false;
        
        for (int x : MSTR_Config.PaintSafeBlocks) {
            if (x == blockid) {
                returnMe = true;
            }
        }        
        return returnMe;        
    }

}
