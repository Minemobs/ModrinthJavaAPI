package fr.minemobs.modrinthjavalib;

public enum Loaders {
    FABRIC("fabric"),
    FORGE("forge");

    String s;

    Loaders(String s){
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
