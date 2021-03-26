package fr.minemobs.modrinthjavapi;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;

public class ModrinthVersion {

    private final String id;
    private final String mod_id;
    private final String name;
    private final String version_number;
    private final String changelog_url;
    private final int downloads;
    private final String version_type;
    private final String[] dependencies;
    private final String[] game_versions;
    private final String[] loaders;
    private final File[] files;
    private final String date_published;

    private final LocalDateTime date_publishedAsADate;

    public static class File {
        private final HashMap<String, String> hashes;
        private final String url;
        private final String filename;

        /**
         * If you use {@link #getVersionFromNameOfTheVersion(String)} it will return you this class
         * @param hashes The hash of the file
         * @param url The url of the file
         * @param filename The name of the file
         */
        private File(HashMap<String, String> hashes, String url, String filename) {
            this.hashes = hashes;
            this.url = url;
            this.filename = filename;
        }

        public String getUrl() {
            return url;
        }

        public HashMap<String, String> getHashes() {
            return hashes;
        }

        public String getFilename() {
            return filename;
        }

        @Override
        public String toString() {
            return "File{" +
                    "hashes=" + hashes +
                    ", url='" + url + '\'' +
                    ", filename='" + filename + '\'' +
                    '}';
        }
    }

    private ModrinthVersion(String id, String mod_id, String name, String version_number, String changelog_url,
                           int downloads, String version_type, String[] dependencies, String[] game_versions,
                           String[] loaders, File[] files, String date_published) {
        this.id = id;
        this.mod_id = mod_id;
        this.name = name;
        this.version_number = version_number;
        this.changelog_url = changelog_url;
        this.downloads = downloads;
        this.version_type = version_type;
        this.dependencies = dependencies;
        this.game_versions = game_versions;
        this.loaders = loaders;
        this.files = files;
        this.date_published = date_published;

        date_publishedAsADate = LocalDateTime.parse(date_published, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    }

    public String getId() {
        return id;
    }

    public String getMod_id() {
        return mod_id;
    }

    public String getName() {
        return name;
    }

    public String getVersion_number() {
        return version_number;
    }

    public String getChangelog_url() {
        return changelog_url;
    }

    public int getDownloads() {
        return downloads;
    }

    public String getVersion_type() {
        return version_type;
    }

    public String[] getDependencies() {
        return dependencies;
    }

    public String[] getGame_versions() {
        return game_versions;
    }

    public String[] getLoaders() {
        return loaders;
    }

    public File[] getFiles() {
        return files;
    }

    public LocalDateTime getDate_publishedAsADate() {
        return date_publishedAsADate;
    }

    public String getDate_published() {
        return date_published;
    }

    @Override
    public String toString() {
        return "ModrinthVersion{" +
                "id='" + id + '\'' +
                ", mod_id='" + mod_id + '\'' +
                ", name='" + name + '\'' +
                ", version_number='" + version_number + '\'' +
                ", changelog_url='" + changelog_url + '\'' +
                ", downloads=" + downloads +
                ", version_type='" + version_type + '\'' +
                ", dependencies=" + Arrays.toString(dependencies) +
                ", game_versions=" + Arrays.toString(game_versions) +
                ", loaders=" + Arrays.toString(loaders) +
                ", files=" + Arrays.toString(files) +
                ", date_published='" + date_published + '\'' +
                ", date_publishedAsADate=" + date_publishedAsADate +
                '}';
    }

    /**
     * @param versionName the id of the version
     * @return {@link ModrinthVersion}
     */
    public static ModrinthVersion getVersionFromNameOfTheVersion(String versionName) {
        try {
            URL url = new URL(MainClass.getBaseUrl() + "version/" + versionName);
            InputStreamReader reader;
            reader = new InputStreamReader(url.openStream());
            ModrinthVersion modrinthVersion = MainClass.getGson().fromJson(reader, ModrinthVersion.class);
            reader.close();
            return modrinthVersion;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Delete a version of a mod
     * @param token your Modrinth token
     */
    public void deleteVersion(String token){
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create("", mediaType);
        Request request = new Request.Builder()
                .url(MainClass.getBaseUrl() + "version/" + this.id)
                .method("DELETE", body)
                .addHeader("Authorization", token)
                .build();
        try {
            MainClass.getClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
