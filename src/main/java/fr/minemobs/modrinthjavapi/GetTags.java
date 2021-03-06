package fr.minemobs.modrinthjavapi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GetTags {

    /**
     * Get a list of mod loaders
     * @return an array of Strings
     */
    public static String[] getLoaders(){
        InputStreamReader inputStreamReader = null;
        try{
            URL url = new URL(MainClass.baseUrl + "tag/loader");
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
    public static String[] getGameVersions(){
        InputStreamReader inputStreamReader = null;
        try{
            URL url = new URL(MainClass.baseUrl + "tag/game_version");
            inputStreamReader = new InputStreamReader(url.openStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        return MainClass.getGson().fromJson(inputStreamReader, String[].class);
    }

    /**
     * Get all licenses on Modrinth
     * See an example of how to use this function here {@link #getAllLicenses()}
     * @return an array of HashMaps
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, String>[] getLicenses(){
        InputStreamReader inputStreamReader = null;
        try{
            URL url = new URL(MainClass.baseUrl + "tag/license");
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
    public static String[] getCategories(){
        InputStreamReader inputStreamReader = null;
        try{
            URL url = new URL(MainClass.baseUrl + "tag/category");
            inputStreamReader = new InputStreamReader(url.openStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        return MainClass.getGson().fromJson(inputStreamReader, String[].class);
    }

    private void getAllLicenses(){
        HashMap<String, String>[] licenses = GetTags.getLicenses();
        for (HashMap<String, String> licens : licenses) {
            for (Map.Entry<String, String> entry : licens.entrySet()) {
                String s = entry.getKey();
                String s2 = entry.getValue();
                System.out.println(s + " : " + s2);
            }
        }
    }

}
