package de.youtuberinsel.schnitzeljagdtipps;

import de.youtuberinsel.schnitzeljagdtipps.commands.SchnitzeljagdSetupCommand;
import de.youtuberinsel.schnitzeljagdtipps.commands.SchnitzeljagdCommand;
import de.youtuberinsel.schnitzeljagdtipps.commands.SchnitzeljagdProgressCommand;
import de.youtuberinsel.schnitzeljagdtipps.commands.SchnitzeljagdTippCommand;
import de.youtuberinsel.schnitzeljagdtipps.events.GeneralEvents;
import de.youtuberinsel.schnitzeljagdtipps.utility.SchnitzeljagdUtility;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Schnitzeljagdtipps extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new GeneralEvents(), this);


        getCommand("schatzsuche").setExecutor(new SchnitzeljagdCommand());
        getCommand("schatzsuche_setup").setExecutor(new SchnitzeljagdSetupCommand());
        getCommand("schatzsuche_progress").setExecutor(new SchnitzeljagdProgressCommand());
        getCommand("tipp").setExecutor(new SchnitzeljagdTippCommand());


        File f = new File(SchnitzeljagdUtility.getFolderPath() + "/schnitzeljagd.yml");
        List<String> schnitzeljagd = new ArrayList<>();
        List<String> tipps = new ArrayList<>();
        if(f.exists()) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
            schnitzeljagd = cfg.getStringList("schnitzeljagd");
            tipps = cfg.getStringList("tipps");
        } else {
            schnitzeljagd.add("Schritt 0: Start der Schatzsuche");
            tipps.add("Viel Spass ^^");
        }
        SchnitzeljagdUtility.setSchnitzeljagdData(schnitzeljagd);
        SchnitzeljagdUtility.setTippData(tipps);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        List<String> schnitzeljagd = SchnitzeljagdUtility.getSchnitzeljagdData();
        List<String> tipps = SchnitzeljagdUtility.getTippData();
        File f = new File(SchnitzeljagdUtility.getFolderPath() + "/schnitzeljagd.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
        cfg.set("schnitzeljagd", schnitzeljagd);
        cfg.set("tipps", tipps);

        try {cfg.save(f); } catch (IOException e) { e.printStackTrace(); }
    }
}
