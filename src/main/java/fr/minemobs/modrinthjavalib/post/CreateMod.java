package fr.minemobs.modrinthjavalib.post;


import fr.minemobs.modrinthjavalib.Loaders;
import fr.minemobs.modrinthjavalib.MainClass;
import fr.minemobs.modrinthjavalib.ModrinthMod;
import fr.minemobs.modrinthjavalib.SideStatus;
import fr.minemobs.modrinthjavalib.DonationLink;
import fr.minemobs.modrinthjavalib.ReleaseChannel;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateMod {

    private final String modName;
    private final String modSlug;
    private final String modDescription;
    private final String modBody;
    private final InitialVersionData[] initialVersionData;
    private final String[] categories;
    private final String issuesUrl;
    private final String sourceUrl;
    private final String wikiUrl;
    private final String licenseUrl;
    private final String discordUrl;
    private final boolean isDraft;
    private final SideStatus clientSide;
    private final SideStatus serverSide;
    private final String licenseId;
    private final DonationLink[] donationUrls;

    public static class InitialVersionData {
        private final String[] fileParts;
        private final String versionNumber;
        private final String versionTitle;
        private final String versionBody;
        private final String[] dependencies;
        private final String[] gameVersions;
        private final ReleaseChannel releaseChannel;
        private final Loaders[] loaders;
        private final boolean featured;

        //Note: FileParts aren't supported at this time
        public InitialVersionData(String[] fileParts, @NotNull String versionNumber, @NotNull String versionTitle,
                                  @NotNull String versionBody, @NotNull String[] dependencies, @NotNull String[] gameVersions, @NotNull ReleaseChannel releaseChannel,
                                  @NotNull Loaders[] loaders, boolean featured) {
            this.fileParts = fileParts;
            this.versionNumber = versionNumber;
            this.versionTitle = versionTitle;
            this.versionBody = versionBody;
            this.dependencies = dependencies;
            this.gameVersions = gameVersions;
            this.releaseChannel = releaseChannel;
            this.loaders = loaders;
            this.featured = featured;
        }

        @Override
        public String toString() {
            return
                    "file_parts\": []," +
                    "\"version_number\":\"" + versionNumber + '\"' +
                    ", \"version_title\":\"" + versionTitle + '\"' +
                    ", \"version_body\":\"" + versionBody + '\"' +
                    ", \"dependencies\":" + arrayToString(dependencies) +
                    ", \"game_versions\":" + arrayToString(gameVersions) +
                    ", \"release_channel\":\"" + releaseChannel.getS() + "\"" +
                    ", \"loaders\":" + arrayToString(loaders) +
                    ", \"featured\":" + featured +
                    "}";
        }
    }

    public CreateMod(@NotNull String modName, @NotNull String modSlug, @NotNull String modDescription, @NotNull String modBody, @NotNull InitialVersionData[] initialVersionData,
                     @NotNull String[] categories, String issuesUrl, String sourceUrl, String wikiUrl, String licenseUrl, String discordUrl, boolean isDraft, SideStatus clientSide,
                     SideStatus serverSide, String licenseId, DonationLink[] donationUrls) {
        this.modName = modName;
        this.modSlug = modSlug;
        this.modDescription = modDescription;
        this.modBody = modBody;
        this.initialVersionData = initialVersionData;
        this.categories = categories;
        this.issuesUrl = issuesUrl;
        this.sourceUrl = sourceUrl;
        this.wikiUrl = wikiUrl;
        this.licenseUrl = licenseUrl;
        this.discordUrl = discordUrl;
        this.isDraft = isDraft;
        this.clientSide = clientSide;
        this.serverSide = serverSide;
        this.licenseId = licenseId;
        this.donationUrls = donationUrls;
    }

    public String getModName() {
        return modName;
    }

    public String getModSlug() {
        return modSlug;
    }

    public String getModDescription() {
        return modDescription;
    }

    public String getModBody() {
        return modBody;
    }

    public InitialVersionData[] getInitialVersionData() {
        return initialVersionData;
    }

    public String[] getCategories() {
        return categories;
    }

    public String getIssuesUrl() {
        return issuesUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public String getDiscordUrl() {
        return discordUrl;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public SideStatus getClientSide() {
        return clientSide;
    }

    public SideStatus getServerSide() {
        return serverSide;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public DonationLink[] getDonationUrls() {
        return donationUrls;
    }

    private static String arrayToString(InitialVersionData[] datas) {
        if (datas == null) {
            return "null";
        }

        int iMax = datas.length - 1;
        if (iMax == -1) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[').append('{').append('"');
        for (int i = 0; ; i++) {
            b.append(datas[i]);
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append("\", \"");
        }
    }


    private static String arrayToString(Loaders[] loaders){
        ArrayList<String> loadersList = new ArrayList<>();
        for (Loaders loader : loaders) {
            loadersList.add(loader.getS());
        }
        return arrayToString(loadersList.toArray(new String[0]));
    }

    private static String arrayToString(String[] s){
        if (s == null) {
            return "null";
        }

        int iMax = s.length - 1;
        if (iMax == -1) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[').append('"');
        for (int i = 0; ; i++) {
            b.append(s[i]);
            if (i == iMax) {
                return b.append('"').append(']').toString();
            }
            b.append("\", \"");
        }
    }

    @Override
    public String toString() {

        return String.format("{" +
                        "\"mod_name\": \"%s\"," +
                        "\"mod_slug\": \"%s\"," +
                        "\"mod_description\": \"%s\"," +
                        "\"mod_body\": \"%s\"," +
                        "\"initial_versions\": %s," +
                        "\"categories\": %s," +
                        "\"issues_url\": %s," +
                        "\"wiki_url\": %s," +
                        "\"license_url\": %s," +
                        "\"discord_url\": %s," +
                        "\"is_draft\": %b," +
                        "\"client_side\": \"%s\"," +
                        "\"server_side\": \"%s\"," +
                        "\"license_id\": \"%s\"," +
                        "\"donation_urls\": %s" +
                        "}",
                this.modName, this.modSlug, this.modDescription, this.modBody, arrayToString(this.initialVersionData), arrayToString(this.categories), this.issuesUrl,
                this.wikiUrl, this.licenseUrl, this.discordUrl, this.isDraft, this.clientSide.getS(), this.serverSide.getS(), this.licenseId, Arrays.toString(this.donationUrls));
    }

    public ModrinthMod createMod(String token){
        try{
            String dataForm = this.toString();

            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("data", dataForm).build();
            Request request = new Request.Builder()
                    .url(MainClass.getBaseUrl() + "mod")
                    .method("POST", body)
                    .addHeader("Authorization", token)
                    .build();
            Response response = MainClass.getClient().newCall(request).execute();
            String responseText = response.body().string();
            return MainClass.getGson().fromJson(responseText, ModrinthMod.class);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
