package fr.minemobs.modrinthjavapi;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;

public class ModrinthMod {

    private String id;
    private String team;
    private String title;
    private String description;
    private String status;
    private HashMap<String, String> license;
    private String client_side;
    private String server_side;
    private int downloads;
    private String[] categories;
    private String[] versions;
    private String icon_url;
    private String issues_url;
    private String source_url;
    private String wiki_url;
    private String discord_url;
    private String donation_url;
    private String published;
    private String updated;

    public ModrinthMod(String id, String team, String title, String description, String status, HashMap<String, String> license, String client_side,
                       String server_side, int downloads, String[] categories, String[] versions, String icon_url,
                       String issues_url, String source_url, String wiki_url, String discord_url, String donation_url,
                       String published, String updated) {
        this.id = id;
        this.team = team;
        this.title = title;
        this.description = description;
        this.status = status;
        this.license = license;
        this.client_side = client_side;
        this.server_side = server_side;
        this.downloads = downloads;
        this.categories = categories;
        this.versions = versions;
        this.icon_url = icon_url;
        this.issues_url = issues_url;
        this.source_url = source_url;
        this.wiki_url = wiki_url;
        this.discord_url = discord_url;
        this.donation_url = donation_url;

        this.published = published;
        this.updated = updated;
    }

    public LocalDateTime formatDate(String dateAsString){
        LocalDateTime date;

        int index = dateAsString.indexOf(".");
        dateAsString = dateAsString.substring(0, index);
        dateAsString+="Z";
        date = LocalDateTime.parse(dateAsString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        return date;
    }

    @Override
    public String toString() {
        return "ModrinthMod{" +
                "id='" + id + '\'' +
                ", team='" + team + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", license=" + license +
                ", client_side='" + client_side + '\'' +
                ", server_side='" + server_side + '\'' +
                ", downloads=" + downloads +
                ", categories=" + Arrays.toString(categories) +
                ", versions=" + Arrays.toString(versions) +
                ", icon_url='" + icon_url + '\'' +
                ", issues_url='" + issues_url + '\'' +
                ", source_url='" + source_url + '\'' +
                ", wiki_url='" + wiki_url + '\'' +
                ", discord_url='" + discord_url + '\'' +
                ", donation_url='" + donation_url + '\'' +
                ", published='" + published + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getTeam() {
        return team;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public HashMap<String, String> getLicense() {
        return license;
    }

    public String getClient_side() {
        return client_side;
    }

    public String getServer_side() {
        return server_side;
    }

    public int getDownloads() {
        return downloads;
    }

    public String[] getCategories() {
        return categories;
    }

    public String[] getVersions() {
        return versions;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public String getIssues_url() {
        return issues_url;
    }

    public String getSource_url() {
        return source_url;
    }

    public String getWiki_url() {
        return wiki_url;
    }

    public String getDiscord_url() {
        return discord_url;
    }

    public String getDonation_url() {
        return donation_url;
    }

    public String getPublished() {
        return published;
    }

    public String getUpdated() {
        return updated;
    }

    public static ModrinthMod getModrinthModfromName(String nameOfTheMod) {
        char[] invalidChars = {"'".charAt(0), ')', '('};
        ModrinthMod mod = null;

        nameOfTheMod = nameOfTheMod.replaceAll(" ","-");

        for (char invalidChar : invalidChars) {
            if(MainClass.contains(nameOfTheMod, invalidChar)){
                nameOfTheMod = nameOfTheMod.replace(String.valueOf(invalidChar),"");
            }
        }

        nameOfTheMod = nameOfTheMod.replace(' ','-').toLowerCase().replace("'","");
        String nameOfTheModFormatted = nameOfTheMod;
        try {
            URL url = new URL(MainClass.baseUrl + "mod/" + nameOfTheModFormatted);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            mod = MainClass.getGson().fromJson(reader, ModrinthMod.class);
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return mod;
    }

    public static String deleteMod(String modid, String token){
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(MainClass.baseUrl + "mod/" + modid)
                .method("DELETE", body)
                .addHeader("Authorization", token)
                .build();
        String responseText = null;
        try {
            Response response = MainClass.getClient().newCall(request).execute();
            responseText = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseText;
    }
}