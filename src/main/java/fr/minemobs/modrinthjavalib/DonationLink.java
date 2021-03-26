package fr.minemobs.modrinthjavalib;

public class DonationLink {

    private final String id;
    private final String platform;
    private final String url;

    public DonationLink(String id, String platform, String url) {
        this.id = id;
        this.platform = platform;
        this.url = url;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", platform='" + platform + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
