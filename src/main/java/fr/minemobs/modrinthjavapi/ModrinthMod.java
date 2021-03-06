package fr.minemobs.modrinthjavapi;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ModrinthMod {

    private final String id;
    private final String slug;
    private final String team;
    private final String title;
    private final String description;
    private final String body;
    private final String body_url;
    private final String published;
    private final String updated;
    private final String status;
    private final HashMap<String, String> license;
    private final String client_side;
    private final String server_side;
    private final int downloads;
    private final String[] categories;
    private final String[] versions;
    private final String icon_url;
    private final String issues_url;
    private final String source_url;
    private final String wiki_url;
    private final String discord_url;
    private final String[] donation_urls;

    /**
     * If you want to use this class you will need to use {@link #getModrinthMod(String)} or {@link #getModFromUser(User)}
     * @param id The id of your mod
     * @param team The id of the team that has ownership of this mod
     * @param title The title or name of the mod
     * @param description A short description of the mod
     * @param status The status of the mod - {@link ModStatus}
     * @param license The license of the mod
     * @param client_side The support range for the client mod - {@link SideStatus}
     * @param server_side The support range for the client mod - {@link SideStatus}
     * @param downloads The total number of downloads the mod has
     * @param categories A list of the categories that the mod is in
     * @param versions A list of ids for versions of the mod
     * @param icon_url The URL of the icon of the mod
     * @param issues_url An optional link to where to submit bugs or issues with the mod
     * @param source_url An optional link to the source code for the mod
     * @param wiki_url An optional link to the mod's wiki page or other relevant information
     * @param discord_url An optional link to the mod's discord
     * @param donation_urls An optional list of all donation links the mod has
     * @param published The date that this version was published
     * @param updated The date at which the mod was updated
     */
    private ModrinthMod(String id, String slug, String team, String title, String description, String body, String body_url, String published,
                       String updated, String status, HashMap<String, String> license, String client_side, String server_side, int downloads, String[] categories,
                       String[] versions, String icon_url, String issues_url, String source_url, String wiki_url, String discord_url, String[] donation_urls) {
        this.id = id;
        this.slug = slug;
        this.team = team;
        this.title = title;
        this.description = description;
        this.body = body;
        this.body_url = body_url;
        this.published = published;
        this.updated = updated;
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
        this.donation_urls = donation_urls;
    }

    public LocalDateTime formatDate(String dateAsString){
        LocalDateTime date;
        String _dateAsString;

        int index = dateAsString.indexOf(".");
        _dateAsString = dateAsString.substring(0, index);
        _dateAsString+="Z";
        date = LocalDateTime.parse(dateAsString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        return date;
    }

    @Override
    public String toString() {
        return "ModrinthMod{" +
                "id='" + id + '\'' +
                ", slug='" + slug + '\'' +
                ", team='" + team + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                ", body_url='" + body_url + '\'' +
                ", published=" + published +
                ", updated='" + updated + '\'' +
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
                ", donation_urls=" + Arrays.toString(donation_urls) +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getSlug() {
        return slug;
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

    public String getBody() {
        return body;
    }

    public String getBody_url() {
        return body_url;
    }

    public String getPublished() {
        return published;
    }

    public String getUpdated() {
        return updated;
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

    public String[] getDonation_urls() {
        return donation_urls;
    }

    /**
     * Return a mod from it's name / id
     * @param nameOfTheMod the name of the mod or the mod id
     * @return {@link ModrinthMod}
     */
    public static ModrinthMod getModrinthMod(String nameOfTheMod) {
        String _nameOfTheMod;
        char[] invalidChars = {"'".charAt(0), ')', '('};
        ModrinthMod mod = null;

        _nameOfTheMod = nameOfTheMod.replaceAll(" ","-");

        for (char invalidChar : invalidChars) {
            if(MainClass.contains(_nameOfTheMod, invalidChar)){
                _nameOfTheMod = _nameOfTheMod.replace(String.valueOf(invalidChar),"");
            }
        }

        String nameOfTheModFormatted = _nameOfTheMod.replace(' ','-').replace("'","");
        try {
            URL url = new URL(MainClass.getBaseUrl() + "mod/" + nameOfTheModFormatted);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            mod = MainClass.getGson().fromJson(reader, ModrinthMod.class);
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return mod;
    }

    /**
     * @param token Your Modrinth token
     **/
    public void deleteMod(String token){
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create("", mediaType);
        Request request = new Request.Builder()
                .url(MainClass.getBaseUrl() + "mod/" + this.id)
                .method("DELETE", body)
                .addHeader("Authorization", token)
                .build();
        try {
            MainClass.getClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a list of mod from an user
     * @param user {@link User}
     * @return an array of mods {@link ModrinthMod}
     */
    public static ArrayList<ModrinthMod> getModFromUser(User user){
        String userid = user.getId();
        InputStreamReader inputStreamReader = null;
        try{
            URL url = new URL(MainClass.getBaseUrl() + "user/" + userid + "/mods");
            inputStreamReader = new InputStreamReader(url.openStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        String[] modsId = MainClass.getGson().fromJson(inputStreamReader, String[].class);
        ArrayList<ModrinthMod> userMods = new ArrayList<>();
        for (String s : modsId) {
            userMods.add(getModrinthMod(s));
        }
        return userMods;
    }
}