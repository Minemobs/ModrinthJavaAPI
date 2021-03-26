package fr.minemobs.modrinthjavalib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.minemobs.modrinthjavalib.post.CreateMod;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainClass {

    private final static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private final static Logger LOGGER = LogManager.getLogger(MainClass.class);
    private final static String baseUrl = "https://api.modrinth.com/api/v1/";

    private final static OkHttpClient client = new OkHttpClient().newBuilder()
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

    private void launchprog(String[] args) {
        String token = "";
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
        CreateMod createMod = new CreateMod("TestModYou", "testminemobsmod", "A Test mod", "I test my api written in java to see if everything works",
                new CreateMod.InitialVersionData[]{new CreateMod.InitialVersionData(/*Not supported*/null, "v1.0", "First release", "Test version", new String[]{},
                        new String[]{"1.16.4"}, ReleaseChannel.ALPHA, new Loaders[]{Loaders.FORGE, Loaders.FABRIC}, false)},
                new String[]{"misc"}, null, null, null, null, null, true, SideStatus.UNSUPPORTED, SideStatus.UNSUPPORTED, "mit", new DonationLink[]{}
        );

        System.out.println(createMod.createMod(token).toString());
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

    public static String getBaseUrl() {
        return baseUrl;
    }
}
