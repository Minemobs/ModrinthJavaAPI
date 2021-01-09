package fr.minemobs.modrinthjavapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        try {
            new MainClass().launchprog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String baseUrl = "https://api.modrinth.com/api/v1/";

    private char[] invalidChars = {"'".charAt(0), ')', '('};

    private void launchprog() throws Exception {
        System.out.println("Please write the name of the mod you want to search.");
        Scanner sc = new Scanner(System.in);
        String nameOfTheMod = sc.nextLine();
        ModrinthMod modrinthMod = getModrinthModfromName(nameOfTheMod);
        ArrayList<ModrinthVersion> modrinthVersions = new ArrayList<>();
        for (String version : modrinthMod.getVersions()) {
            modrinthVersions.add(getVersionFromNameOfTheVersion(version));
        }
        System.out.println("--------------------------------------");
        System.out.println(modrinthMod.toString());
    }

    private ModrinthMod getModrinthModfromName(String nameOfTheMod) {
        ModrinthMod mod = null;

        nameOfTheMod = nameOfTheMod.replaceAll(" ","-");

        for (char invalidChar : invalidChars) {
            if(contains(nameOfTheMod, invalidChar)){
                nameOfTheMod = nameOfTheMod.replace(String.valueOf(invalidChar),"");
            }
        }
        nameOfTheMod = nameOfTheMod.replace(' ','-').toLowerCase().replace("'","");
        String nameOfTheModFormatted = nameOfTheMod;
        try {
            URL url = new URL(baseUrl + "mod/" + nameOfTheModFormatted);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            mod = gson.fromJson(reader, ModrinthMod.class);
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return mod;
    }

    private ModrinthVersion getVersionFromNameOfTheVersion(String versionName) throws IOException {
        URL url = new URL(baseUrl + "version/" + versionName);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        ModrinthVersion modrinthVersion = gson.fromJson(reader, ModrinthVersion.class);
        reader.close();
        return modrinthVersion;
    }

    private boolean contains(String str, char chr ) {

        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) == chr)
                return true;
        return false;
    }
}
