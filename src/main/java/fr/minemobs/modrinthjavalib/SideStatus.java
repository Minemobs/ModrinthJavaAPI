package fr.minemobs.modrinthjavalib;

public enum SideStatus {
    REQUIRED("required"),
    OPTIONAL("optional"),
    UNSUPPORTED("unsupported");

    String s;

    SideStatus(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
