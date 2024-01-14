package de.youtuberinsel.schnitzeljagdtipps.events;

import de.youtuberinsel.schnitzeljagdtipps.data.PlayerData;
import de.youtuberinsel.schnitzeljagdtipps.utility.PlayerUtility;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;

public class GeneralEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PlayerData data = new PlayerData();
        File f = new File(PlayerUtility.getFolderPath(event.getPlayer()) + "/data.yml");

        if(f.exists()) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
            data.setSchnitzeljagdschritt(cfg.getInt("schnitzeljagd.step"));
        } else {
            data.setSchnitzeljagdschritt(0);
        }
        PlayerUtility.setPlayerData(event.getPlayer(), data);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        PlayerData playerData = PlayerUtility.getPlayerData(event.getPlayer());
        File f = new File(PlayerUtility.getFolderPath(event.getPlayer()) + "/data.yml");

        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
        cfg.set("schnitzeljagd.step", playerData.getSchnitzeljagdschritt());

        try {cfg.save(f); } catch (IOException e) { e.printStackTrace(); }
        PlayerUtility.setPlayerData(event.getPlayer(), null);
    }
}
