package de.youtuberinsel.schnitzeljagdtipps.utility;

import de.youtuberinsel.schnitzeljagdtipps.data.SchnitzeljagdData;
import org.bukkit.Bukkit;

import java.util.List;

public class SchnitzeljagdUtility {

    public static String getFolderPath() {
        return Bukkit.getPluginsFolder().getAbsolutePath() + "/Schnitzeljagd";
    }

    public static List<String> getSchnitzeljagdData() {
        SchnitzeljagdData data = SchnitzeljagdData.getInstance();
        return data.getSchnitzeljagdData();
    }

    public static void setSchnitzeljagdData(List<String> schnitzeljagddata) {
        SchnitzeljagdData data = SchnitzeljagdData.getInstance();
        data.setSchnitzeljagdData(schnitzeljagddata);
    }

    public static List<String> getTippData() {
        SchnitzeljagdData data = SchnitzeljagdData.getInstance();
        return data.getTippData();
    }

    public static void setTippData(List<String> schnitzeljagddata) {
        SchnitzeljagdData data = SchnitzeljagdData.getInstance();
        data.setTippData(schnitzeljagddata);
    }
}
