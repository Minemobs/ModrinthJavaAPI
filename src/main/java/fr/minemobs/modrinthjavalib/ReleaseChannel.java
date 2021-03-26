package fr.minemobs.modrinthjavalib;

public enum ReleaseChannel {
    ALPHA("alpha"),
    BETA("beta"),
    RELEASE("release");

    String s;

    ReleaseChannel(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
