package de.youtuberinsel.schnitzeljagdtipps.commands;

import de.youtuberinsel.schnitzeljagdtipps.data.PlayerData;
import de.youtuberinsel.schnitzeljagdtipps.utility.PlayerUtility;
import de.youtuberinsel.schnitzeljagdtipps.utility.SchnitzeljagdUtility;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SchnitzeljagdProgressCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendRichMessage("You must be a player to run this command!");
            return false;
        }

        if (strings.length == 0) {
            commandSender.sendRichMessage("Bitte nutze /schatzsuche_progress <index>");
            return false;
        }

        int index;
        try {
            index = Integer.parseInt(strings[0]);
        } catch (NumberFormatException nfe) {
            commandSender.sendRichMessage("Bitte nutze /schatzsuche_progress <index>");
            return false;
        }

        Player player = (Player) commandSender;
        if (player.hasPermission("youtuberinsel.schatzsuche.progress")) {
            PlayerData playerData = PlayerUtility.getPlayerData(player);
            int step = playerData.getSchnitzeljagdschritt();
            if (index == step + 1) {
                step = index;
                playerData.setSchnitzeljagdschritt(index);
                PlayerUtility.setPlayerData(player, playerData);
                String tipp = SchnitzeljagdUtility.getSchnitzeljagdData().get(step);
                player.sendRichMessage(tipp);
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "title " + player.getName() + " title {\"text\": \"Hinweis gefunden\", \"color\": \"green\", \"bold\":false}");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 500.0f, 1.0f);
            } else if (index > step + 1) {
                player.sendRichMessage("Du bist noch nicht so weit");
                return false;
            } else {
                player.sendRichMessage("Du warst hier schon");
                return false;
            }
        }



        return true;
    }
}