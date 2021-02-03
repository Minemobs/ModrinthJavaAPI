package fr.minemobs.modrinthjavapi;

import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class User {
    private String id;
    private long github_id;
    private String username;
    private String name;
    private String email;
    private String avatar_url;
    private String bio;
    private String role;

    public User(String id, long github_id, String username, String name, String email, String avatar_url, String bio, String role) {
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

    public User getUserFromName(String username) {
        try {
            if(username.contains("@")){
                username = username.replace("@","");
            }
            URL url = new URL(MainClass.baseUrl + "user/@" + username);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            return MainClass.getGson().fromJson(inputStreamReader, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User getMySelf(String token){
        try {
            URL url = new URL(MainClass.baseUrl + "user");
            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .addHeader("Authorization", token)
                    .build();
            Response response = MainClass.getClient().newCall(request).execute();
            String responseBody = response.body().string();
            User user = MainClass.getGson().fromJson(responseBody, User.class);
            response.close();
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void userDelete(String token){
        try{
            String userId = getMySelf(token).getId();
            URL url = new URL(MainClass.baseUrl + "user/" + userId);
            Request request = new Request.Builder()
                    .url(url)
                    .method("DELETE", null)
                    .addHeader("Authorization", token)
                    .build();
            Response response = MainClass.getClient().newCall(request).execute();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
