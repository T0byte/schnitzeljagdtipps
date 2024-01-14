package de.youtuberinsel.schnitzeljagdtipps.commands;

import de.youtuberinsel.schnitzeljagdtipps.Schnitzeljagdtipps;
import de.youtuberinsel.schnitzeljagdtipps.utility.SchnitzeljagdUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SchnitzeljagdSetupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendRichMessage("You must be a player to run this command!");
            return false;
        }

        if (strings.length == 0 || strings.length == 1 && !(strings[0].equals("all"))) {
            commandSender.sendRichMessage("Bitte nutze /schatzsuche_setup <index> <hinweis>");
            return false;
        }

        if(strings[0].equals("all")) {
            List<String> schnitzeljagdData = SchnitzeljagdUtility.getSchnitzeljagdData();
            String steps = " ";
            for(String step: schnitzeljagdData) {
                steps = steps + "\n" + step;
            }
            commandSender.sendRichMessage(steps);
            return true;
        }

        int index;
        try {
            index = Integer.parseInt(strings[0]);
        } catch (NumberFormatException nfe) {
            commandSender.sendRichMessage("Bitte nutze /schatzsuche_setup <index> <hinweis>");
            return false;
        }

        if(strings[1].equals("tipp")) {
            strings[1] = strings[0] + ":";
            strings[0] = "Tipp";
            List<String> tippData = SchnitzeljagdUtility.getTippData();
            if(index < tippData.size()) {
                String hint = String.join(" ", strings);
                tippData.set(index, hint);
                commandSender.sendRichMessage("Schatzsuche eintrag erstellt: \"" + hint + "\"");
            } else {
                commandSender.sendRichMessage("Kein eintrag auf diesem Index vorhanden");
                return false;
            }
            return true;
        }

        strings[0] = "Schritt " + strings[0] + ":";
        String hint = String.join(" ", strings);

        Player player = (Player) commandSender;
        if(player.hasPermission("youtuberinsel.schatzsuche.setup")) {
            List<String> schnitzeljagdData = SchnitzeljagdUtility.getSchnitzeljagdData();
            if(index >= schnitzeljagdData.size()) {
                List<String> tippData = SchnitzeljagdUtility.getTippData();
                for(int i = schnitzeljagdData.size(); i <= index; i++) {
                    schnitzeljagdData.add("Kein Eintrag vorhanden");
                    tippData.add("Kein Hinweis vorhanden");
                }
                SchnitzeljagdUtility.setTippData(tippData);
            }
            schnitzeljagdData.set(index, hint);
            SchnitzeljagdUtility.setSchnitzeljagdData(schnitzeljagdData);
            player.sendRichMessage("Schatzsuche eintrag erstellt: \"" + hint + "\"");
        }

        return true;
    }
}
