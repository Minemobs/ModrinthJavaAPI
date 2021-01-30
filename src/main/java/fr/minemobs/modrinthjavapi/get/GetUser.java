package fr.minemobs.modrinthjavapi.get;

import fr.minemobs.modrinthjavapi.MainClass;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetUser {
    private String id;
    private long github_id;
    private String username;
    private String name;
    private String email;
    private String avatar_url;
    private String bio;
    private String role;

    public GetUser(String id, long github_id, String username, String name, String email, String avatar_url, String bio, String role) {
        this.id = id;
        this.github_id = github_id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.avatar_url = avatar_url;
        this.bio = bio;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public long getGithub_id() {
        return github_id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getBio() {
        return bio;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "GetUser{" +
                "id='" + id + '\'' +
                ", github_id=" + github_id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", bio='" + bio + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    private GetUser getUserFromName(String username) {
        try {
            if(username.contains("@")){
                username = username.replace("@","");
            }
            URL url = new URL(MainClass.baseUrl + "user/@" + username);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            return MainClass.getGson().fromJson(inputStreamReader, GetUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
