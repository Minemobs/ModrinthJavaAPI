package fr.minemobs.modrinthjavapi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ModrinthVersion {

    private String id;
    private String mod_id;
    private String name;
    private String version_number;
    private String changelog_url;
    private int downloads;
    private String version_type;
    private String[] dependencies;
    private String[] game_versions;
    private String[] loaders;
    private File[] files;
    private String date_published;

    private LocalDateTime date_publishedAsADate;

    public static class File {
        private HashMap<String, String> hashes;
        private String url;
        private String filename;

        public File(HashMap<String, String> hashes, String url, String filename) {
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

    public ModrinthVersion(String id, String mod_id, String name, String version_number, String changelog_url,
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

    public static ModrinthVersion getVersionFromNameOfTheVersion(String versionName) throws IOException {
        URL url = new URL(MainClass.baseUrl + "version/" + versionName);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        ModrinthVersion modrinthVersion = MainClass.getGson().fromJson(reader, ModrinthVersion.class);
        reader.close();
        return modrinthVersion;
    }
}
