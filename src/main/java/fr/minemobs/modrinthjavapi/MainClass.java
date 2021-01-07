package fr.minemobs.modrinthjavapi;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.ietf.jgss.GSSContext;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainClass {

    Gson gson = new Gson();

    public static void main(String[] args) {
        try {
            new MainClass().launchprog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String baseUrl = "https://api.modrinth.com/api/v1/";

    private void launchprog() throws Exception {
        URL url = new URL(baseUrl + "mod/puffertweaks");
        InputStreamReader reader = new InputStreamReader(url.openStream());
        ModrinthMod mod = gson.fromJson(reader, ModrinthMod.class);
        reader.close();
        url = new URL(baseUrl + "version/1vmDQ3Cu");
        reader = new InputStreamReader(url.openStream());
        ModrinthVersion modrinthVersion = gson.fromJson(reader, ModrinthVersion.class);
        System.out.println(modrinthVersion.toString());
    }

}
