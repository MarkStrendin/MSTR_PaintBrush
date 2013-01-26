package ca.strendin.MSTR_PaintBrush;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class MSTR_Config {
    public static String paintbrushToolName = "Paintbrush tool";
    public static int paintBrushToolID = 294; // Golden Hoe
    
    public static String infoToolName = "Block info tool";
    public static int infoToolID = 284; // Golden Shovel
	
    /*
     * These blocks play nicely with the paint tool    
     */
    public static int[] PaintSafeBlocks = {
    	1,2,3,4,5,7,12,13,14,15,16,17,18,19,20,21,22,24,25,35,41,42,43,44,45,46,47,48,49,53,56,57,58,60,67,73,74,
    	79,80,82,84,85,86,87,88,89,91,92,98,99,100,101,102,103,108,109,110,112,113,114,118,121,123,124,128,125,
    	126,129,133,134,135,136,139,151,152,153,155,156};
        
    public static ArrayList<Integer> blacklistedBlocks = new ArrayList<Integer>();
    
    private static String configFileName = "MSTR_PaintBrush.config";  
    
    public static Properties configSettings = new Properties();

    
    public static void loadConfigFile(MSTR_PaintBrush plugin) throws IOException {
    	MSTR_Config.blacklistedBlocks.clear();
    	MSTR_Config.configSettings.clear();
        
        FileInputStream fs = new FileInputStream(new File(plugin.getDataFolder(), configFileName));
    
        configSettings.load(fs);
        
        int givenPaintBrushToolID = MSTR_Config.paintBrushToolID;
        int givenInfoToolID = MSTR_Config.infoToolID;
        
        try {
        	givenInfoToolID = Integer.parseInt(configSettings.getProperty("infotoolid","" + infoToolID).trim());
        } catch (Exception e) { MSTR_Comms.logThis("infotoolid was set to an insane value - check your config file"); }
        
        try {
            givenPaintBrushToolID = Integer.parseInt(configSettings.getProperty("paintbrushtoolid","" + paintBrushToolID).trim());
        } catch (Exception e) { MSTR_Comms.logThis("paintbrushtoolid was set to an insane value - check your config file"); }
        
    
        MSTR_Config.paintBrushToolID = givenPaintBrushToolID;
        MSTR_Config.infoToolID = givenInfoToolID;
        
        
        /*
         * 7 - bedrock
         * 79 - ice
         * 46 - TNT
         * 
         */
        String defaultDeniedBlocks = "7,46,79";
        
        // Parse the item blacklist
        String splitMe = configSettings.getProperty("blacklist",defaultDeniedBlocks); 
        
        String workingString[] = splitMe.split(",");
        
        for (String thisString : workingString) {
            // Do some sanity checking on this to make sure it's a number
            try {
                if (Integer.parseInt(thisString.trim()) > 0) {
                	blacklistedBlocks.add(Integer.parseInt(thisString.trim()));    
                }
            } catch (Exception e) { /* Do nothing - it just won't add the invalid number to the blacklist */}            
        }
        
        fs.close();
        MSTR_Comms.logThis("Config file loaded!");
		
	}

	public static void createNewConfigFile(MSTR_PaintBrush plugin) throws IOException {
		MSTR_Comms.logThis("Attempting to create config file...");
        // Check to see if the directory is there
        
        File pluginDirectory = plugin.getDataFolder(); 
        
        if (pluginDirectory.exists() != true) {        
            pluginDirectory.mkdir();           
        }
        
        File configFile = new File(pluginDirectory, configFileName);
        
        if (configFile.exists() != true) {
            configFile.createNewFile();
        }
        
        try {
        	writeNewConfigFileContents(configFile);
            MSTR_Comms.logThis("Successfully created new config file");
            loadConfigFile(plugin);
        } catch (Exception e) {
        	MSTR_Comms.logThis("Could not write to config file!");
        }
		
	}
	
	public static void writeNewConfigFileContents(File thisfile) throws IOException {
        
        BufferedWriter fB = new BufferedWriter(new FileWriter(thisfile));        
        fB.write("#");
        fB.newLine();
        fB.write("# MSTR_PaintBrush configuration file");
        fB.newLine();
        fB.write("#"); 
        fB.newLine();
        fB.newLine();
        
        fB.write("# Paintbrush tool");
        fB.newLine();
        fB.write("#");
        fB.newLine();
        fB.write("# The item with this ID number will be used as");
        fB.newLine();
        fB.write("# the paintbrush tool. DEFAULT IS " + paintBrushToolID);
        fB.newLine();
        fB.write("#");
        fB.newLine();
        fB.write("#  Right clicking will \"copy\" the block hit with the tool");
        fB.newLine();
        fB.write("#");
        fB.newLine();
        fB.write("#  Left clicking will \"paste\" the copied block onto");
        fB.newLine();
        fB.write("#   the clicked block");
        fB.newLine();
        fB.newLine();
        fB.write("paintbrushtoolid = " + paintBrushToolID);
        fB.newLine();
        fB.newLine();
        fB.newLine();
        
        fB.write("# Info tool");
        fB.newLine();
        fB.write("#");
        fB.newLine();
        fB.write("# The item with this ID number will be used as");
        fB.newLine();
        fB.write("# the info tool. DEFAULT IS " + infoToolID);
        fB.newLine();
        fB.write("#");
        fB.newLine();
        fB.write("#  Clicking on a block with this will give you information about the block.");
        fB.newLine();
        fB.write("#");
        fB.newLine();
        fB.newLine();
        fB.write("infotoolid = " + infoToolID);
        fB.newLine();
        fB.newLine();
        fB.newLine();
        
        fB.write("# Denied Items");
        fB.newLine();
        fB.write("#");
        fB.newLine();
        fB.write("# These items are not allowed to be painted (comma separated list)");
        fB.newLine();
        fB.newLine();
        fB.write("blacklist = 7,46,79");
        fB.close();
        
    }

}
