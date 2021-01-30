package fr.minemobs.modrinthjavapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.minemobs.modrinthjavapi.get.GetUser;
import fr.minemobs.modrinthjavapi.post.UploadVersion;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class MainClass {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static String token = "";

    public static String getToken() {
        return token;
    }

    public static void main(String[] args) {
        try {
            new MainClass().launchprog(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String baseUrl = "https://api.modrinth.com/api/v1/";

    private char[] invalidChars = {"'".charAt(0), ')', '('};

    private void launchprog(String[] args) throws Exception {
        if(args.length == 0 || args[0] == null || args[0].isEmpty()) {
            System.out.println("Write your token. If you don't know how to get a token please go on this page : https://modrinth.com/dashboard/settings" +
                    "\n And copy your token");
            Scanner sc = new Scanner(System.in);
            token = sc.nextLine();
        } else {
            if(args[0].contains("TOKEN=")){
                token = args[0].toUpperCase().replace("TOKEN=","");
            }
        }
        /*
        System.out.println("Please write the name of the mod you want to search.");
        Scanner sc = new Scanner(System.in);
        String nameOfTheMod = sc.nextLine();
        ModrinthMod modrinthMod = getModrinthModfromName(nameOfTheMod);
        ArrayList<ModrinthVersion> modrinthVersions = new ArrayList<>();
        for (String version : modrinthMod.getVersions()) {
            modrinthVersions.add(getVersionFromNameOfTheVersion(version));
        }
        System.out.println("--------------------------------------");
        System.out.println(modrinthMod.formatDate(modrinthMod.getPublished()).toString());
        System.out.println("--------------------------------------");
        System.out.println(gson.toJson(modrinthMod));
        GetUser user = getMySelf(token);
        System.out.println("user = " + user.toString());*/
        UploadVersion uploadVersion = new UploadVersion("lrZKwHNJ", new String[]{"puffertweaks-v1-0-0.jar"}, "v1.3", "Test version",
                "I only test the api", new String[]{}, new String[]{"1.16.5", "1.16.4"}, ReleaseChannel.RELEASE, new String[]{Loaders.FORGE.getS()},
                false, new File("src/main/resources/someUslessResources/","puffertweaks-1.1.jar"));
        ModrinthVersion version = uploadVersion.uploadVersionToModrinth();
        System.out.println(version.toString());
    }

    private GetUser getMySelf(){
        try {
            URL url = new URL(baseUrl + "user");
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.modrinth.com/api/v1/user/@Minemobs")
                    .method("GET", null)
                    .addHeader("Authorization", token)
                    .build();
            Response response = client.newCall(request).execute();
            GetUser user = gson.fromJson(Objects.requireNonNull(response.body()).string(), GetUser.class);
            response.close();
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    public static Gson getGson() {
        return gson;
    }
}
