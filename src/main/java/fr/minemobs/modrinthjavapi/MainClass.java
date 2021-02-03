package fr.minemobs.modrinthjavapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.minemobs.modrinthjavapi.get.GetTags;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainClass {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    String token = "";

    private static Logger LOGGER = LogManager.getLogger(MainClass.class);

    private static OkHttpClient client = new OkHttpClient().newBuilder()
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) {
        try {
            new MainClass().launchprog(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String baseUrl = "https://api.modrinth.com/api/v1/";

    private void launchprog(String[] args) throws Exception {
        if(args.length == 0 || args[0] == null || args[0].isEmpty()) {
            System.out.println("Write your token. If you don't know how to get a token please go on this page : https://modrinth.com/dashboard/settings" +
                    "\n And copy your token");
            Scanner sc = new Scanner(System.in);
            token = sc.nextLine();
        }else {
            if(args[0].toUpperCase().contains("TOKEN=")){
                token = args[0].toLowerCase().replace("token=","");
            }
        }

        System.out.println("Please write the name of the mod you want to search.");
        Scanner sc = new Scanner(System.in);
        String nameOfTheMod = sc.nextLine();
        ModrinthMod modrinthMod = ModrinthMod.getModrinthModfromName(nameOfTheMod);
        ArrayList<ModrinthVersion> modrinthVersions = new ArrayList<>();
        for (String version : modrinthMod.getVersions()) {
            modrinthVersions.add(ModrinthVersion.getVersionFromNameOfTheVersion(version));
        }
        System.out.println("--------------------------------------");
        System.out.println(modrinthMod.formatDate(modrinthMod.getPublished()).toString());
        System.out.println("--------------------------------------");
        System.out.println(gson.toJson(modrinthMod));
        /*UploadVersion uploadVersion = new UploadVersion("lrZKwHNJ", new String[]{"puffertweaks-1.1.jar"}, "v1.3", "Test version",
                "I only test the api", new String[]{}, new String[]{"1.16.5", "1.16.4"}, ReleaseChannel.RELEASE, new String[]{Loaders.FORGE.getS()},
                false, new File("src/main/resources/someUslessResources/puffertweaks-1.1.jar"));
        ModrinthVersion version = uploadVersion.uploadVersionToModrinth(token);*/
        User user = User.getMySelf(token);
    }

    public static boolean contains(String str, char chr) {

        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) == chr)
                return true;
        return false;
    }

    public static Gson getGson() {
        return gson;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static OkHttpClient getClient() {
        return client;
    }
}
