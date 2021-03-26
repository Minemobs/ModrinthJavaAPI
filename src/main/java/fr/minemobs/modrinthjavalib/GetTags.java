package fr.minemobs.modrinthjavalib;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

public class GetTags {

    /**
     * Get a list of mod loaders
     * @return an array of Strings
     */
    public String[] getLoaders(){
        InputStreamReader inputStreamReader = null;
        try{
            URL url = new URL(MainClass.getBaseUrl() + "tag/loader");
            inputStreamReader = new InputStreamReader(url.openStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        return MainClass.getGson().fromJson(inputStreamReader, String[].class);
    }

    /**
     * Get a list of game versions
     * @return an array of Strings
     */
    public String[] getGameVersions(){
        InputStreamReader inputStreamReader = null;
        try{
            URL url = new URL(MainClass.getBaseUrl() + "tag/game_version");
            inputStreamReader = new InputStreamReader(url.openStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        return MainClass.getGson().fromJson(inputStreamReader, String[].class);
    }

    /**
     * Get all licenses on Modrinth
     * @return an array of HashMaps
     */
    @SuppressWarnings("unchecked")
    public HashMap<String, String>[] getLicenses(){
        InputStreamReader inputStreamReader = null;
        try{
            URL url = new URL(MainClass.getBaseUrl() + "tag/license");
            inputStreamReader = new InputStreamReader(url.openStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        return MainClass.getGson().fromJson(inputStreamReader, HashMap[].class);
    }

    /**
     * Get a list of categories
     * @return an array of Strings
     */
    public String[] getCategories(){
        InputStreamReader inputStreamReader = null;
        try{
            URL url = new URL(MainClass.getBaseUrl() + "tag/category");
            inputStreamReader = new InputStreamReader(url.openStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        return MainClass.getGson().fromJson(inputStreamReader, String[].class);
    }
}
