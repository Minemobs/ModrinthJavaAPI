package fr.minemobs.modrinthjavapi.post;

import fr.minemobs.modrinthjavapi.MainClass;
import fr.minemobs.modrinthjavapi.ModrinthVersion;
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

    /**
     * ModRinth mod_id
     */
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

    public String formatedString(){
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

    public ModrinthVersion uploadVersionToModrinth(String token) throws IOException {

        MediaType mediaType = MediaType.parse("text/plain");

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
