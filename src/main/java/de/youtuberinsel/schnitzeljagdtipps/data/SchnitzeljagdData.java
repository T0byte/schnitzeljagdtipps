package de.youtuberinsel.schnitzeljagdtipps.data;

import java.util.List;

public class SchnitzeljagdData {

    private static final SchnitzeljagdData OBJ = new SchnitzeljagdData();

    public List<String> getSchnitzeljagdData() {
        return schnitzeljagdData;
    }

    public void setSchnitzeljagdData(List<String> schnitzeljagdData) {
        this.schnitzeljagdData = schnitzeljagdData;
    }

    private List<String> schnitzeljagdData;

    public List<String> getTippData() {
        return tippData;
    }

    public void setTippData(List<String> tippData) {
        this.tippData = tippData;
    }

    private List<String> tippData;

    public static SchnitzeljagdData getInstance() {
        return OBJ;
    }

}
