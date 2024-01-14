package de.youtuberinsel.schnitzeljagdtipps.utility;

import de.youtuberinsel.schnitzeljagdtipps.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerUtility {
    private static Map<String, PlayerData> playerData = new HashMap<>();

    public static PlayerData getPlayerData(Player p) {
        return playerData.get(p.getUniqueId().toString());
    }

    public static void setPlayerData(Player p, PlayerData data) {
        if(data == null) {
            playerData.remove(p.getUniqueId().toString());
        } else {
            playerData.put(p.getUniqueId().toString(), data);
        }
    }

    public static String getFolderPath(Player p) {
        return Bukkit.getPluginsFolder().getAbsolutePath() + "/Schnitzeljagd/player/" + p.getUniqueId();
    }
}
