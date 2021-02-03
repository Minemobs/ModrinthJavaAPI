package fr.minemobs.modrinthjavapi.get;

import fr.minemobs.modrinthjavapi.MainClass;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetTags {

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

}
