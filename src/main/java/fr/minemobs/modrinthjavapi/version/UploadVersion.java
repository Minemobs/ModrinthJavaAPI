package fr.minemobs.modrinthjavapi.version;

import fr.minemobs.modrinthjavapi.Loaders;
import fr.minemobs.modrinthjavapi.MainClass;
import fr.minemobs.modrinthjavapi.ReleaseChannel;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class UploadVersion {
    /**
     * ModRinth mod_id
     */
    String modrinthModId;
    String[] file_parts;
    String version_number;
    String version_title;
    String version_body;
    String[] dependencies;
    String[] game_versions;
    ReleaseChannel releaseChannel;
    String[] loaders;
    boolean featured;
    File file;

    /**
     *
     * @param modrinthModId The id of your mod
     * @param file_parts An array of the multipart field names of each file that goes with this version
     * @param version_number A string that describes this version; should be something similar to semver, but isn't require to have a specific format
     * @param version_title The human readable name of the version
     * @param version_body A description of the version
     * @param dependencies A list of dependencies of this version; this must be specified as an array of version ids of other mods' versions
     * @param game_versions A list of game versions that this version supports
     * @param releaseChannel What type of release that this version is; release, beta, or alpha {@link ReleaseChannel}
     * @param loaders An array of the mod loaders that this mod supports {@link Loaders}
     * @param featured Whether the version should be featured on the mod's homepage
     * @param file The jar of your mod
     */
    public UploadVersion(String modrinthModId, String[] file_parts, String version_number, String version_title, String version_body,
                         String[] dependencies, String[] game_versions, ReleaseChannel releaseChannel,
                         String[] loaders, boolean featured, File file) {
        this.modrinthModId = modrinthModId;
        this.file_parts = file_parts;
        this.version_number = version_number;
        this.version_title = version_title;
        this.version_body = version_body;
        this.dependencies = dependencies;
        this.game_versions = game_versions;
        this.releaseChannel = releaseChannel;
        this.loaders = loaders;
        this.featured = featured;
        this.file = file;
    }

    public String getModrinthModId() {
        return modrinthModId;
    }

    public String[] getFile_parts() {
        return file_parts;
    }

    public String getVersion_number() {
        return version_number;
    }

    public String getVersion_title() {
        return version_title;
    }

    public String getVersion_body() {
        return version_body;
    }

    public String[] getDependencies() {
        return dependencies;
    }

    public String[] getGame_versions() {
        return game_versions;
    }

    public ReleaseChannel getReleaseChannel() {
        return releaseChannel;
    }

    public String[] getLoaders() {
        return loaders;
    }

    public boolean isFeatured() {
        return featured;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "UploadVersion{" +
                "modrinthModId='" + modrinthModId + '\'' +
                ", file_parts=" + arrayToString(file_parts) +
                ", version_number='" + version_number + '\'' +
                ", version_title='" + version_title + '\'' +
                ", version_body='" + version_body + '\'' +
                ", dependencies=" + arrayToString(dependencies) +
                ", game_versions=" + arrayToString(game_versions) +
                ", releaseChannel=" + releaseChannel.getS() +
                ", loaders=" + arrayToString(loaders) +
                ", featured=" + featured +
                ", file=" + file +
                '}';
    }

    private String arrayToString(String[] s){
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

    private String formatedString(){
        return String.format("{\"mod_id\": \"%s\"," +
                        "\"file_parts\": %s," +
                        "\"version_number\": \"%s\"," +
                        "\"version_title\": \"%s\"," +
                        "\"version_body\": \"%s\"," +
                        "\"dependencies\": %s," +
                        "\"game_versions\": %s," +
                        "\"release_channel\": \"%s\"," +
                        "\"loaders\": %s," +
                        "\"featured\": %b" +
                        "}",
                this.modrinthModId, arrayToString(this.file_parts), this.version_number, this.version_title,
                this.version_body, arrayToString(this.dependencies), arrayToString(this.game_versions), this.releaseChannel.getS(),
                arrayToString(this.loaders), this.featured);
    }

    /**
     * Upload your mod version to Modrinth
     * @param token Your modrinth token
     * @return {@link ModrinthVersion}
     * @throws IOException
     */
    public ModrinthVersion uploadVersionToModrinth(String token) throws IOException {

        String dataForm = formatedString();

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("data", dataForm)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file)).build();
        Request request = new Request.Builder()
                .url(MainClass.baseUrl + "version")
                .method("POST", body)
                .addHeader("Authorization", token)
                .build();
        Response response = MainClass.getClient().newCall(request).execute();
        String responseText = response.body().string();
        return MainClass.getGson().fromJson(responseText, ModrinthVersion.class);
    }
}
