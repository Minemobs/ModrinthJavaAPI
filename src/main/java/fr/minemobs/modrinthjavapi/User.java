package fr.minemobs.modrinthjavapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class User {
    private final String id;
    private final long github_id;
    private final String username;
    private final String name;
    private final String email;
    private final String avatar_url;
    private final String bio;
    private final String role;

    /**
     * @param id the id of the user
     * @param github_id the github id of the user
     * @param username the username of the user
     * @param name the name of the user
     * @param email the email of the user
     * @param avatar_url the url of the avatar
     * @param bio the bio of the user
     * @param role the role of the user
     */
    private User(String id, long github_id, String username, String name, String email, String avatar_url, String bio, String role) {
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

    /**
     *
     * @param username the username of the user
     * @return {@link User}
     */
    public static User getUserFromName(String username) {
        try {
            if(username.contains("@")){
                username = username.replace("@","");
            }
            URL url = new URL(MainClass.getBaseUrl() + "user/@" + username);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            return MainClass.getGson().fromJson(inputStreamReader, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id the id of the user
     * @return {@link User}
     */
    public static User getUserFromId(String id) {
        try {
            URL url = new URL(MainClass.getBaseUrl() + "user/" + id);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            return MainClass.getGson().fromJson(inputStreamReader, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get your own account
     * @param token your Modrinth token
     * @return {@link User}
     */
    public static User getMySelf(String token){
        try {
            URL url = new URL(MainClass.getBaseUrl() + "user");
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

    /**
     * Delete your own account.
     * @param token Your Modrinth token
     */
    public void userDelete(String token){
        try{
            String userId = getMySelf(token).getId();
            URL url = new URL(MainClass.getBaseUrl() + "user/" + userId);
            Request request = new Request.Builder()
                    .url(url)
                    .method("DELETE", null)
                    .addHeader("Authorization", token)
                    .build();
            MainClass.getClient().newCall(request).execute();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param userInfo the type of information you want to edit
     * @param info the information you want to change
     * @param token Your Modrinth token
     * @param userId the Id of the user
     */
    public void editUserInfo(@NotNull UserInfo userInfo, @NotNull String info, @NotNull String token, User userId) {
        String json = null;
        switch (userInfo) {
            case BIO:
                json = "{\"bio\":\"" + info + "\"}";
                break;
            case EMAIL:
                json = "{\"email\":\"" + info + "\"}";
                break;
            case USERNAME:
                json = "{\"username\":\"" + info + "\"}";
                break;
        }

        try {
            URL url = new URL(MainClass.getBaseUrl() + "user/" + userId.getId());
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(json, mediaType);
            Request request = new Request.Builder()
                    .url(url)
                    .method("PATCH", body)
                    .addHeader("Authorization", token)
                    .build();
            MainClass.getClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum UserInfo{
        USERNAME,
        EMAIL,
        BIO
    }

}
