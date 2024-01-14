package de.youtuberinsel.schnitzeljagdtipps.commands;

import de.youtuberinsel.schnitzeljagdtipps.data.PlayerData;
import de.youtuberinsel.schnitzeljagdtipps.utility.PlayerUtility;
import de.youtuberinsel.schnitzeljagdtipps.utility.SchnitzeljagdUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SchnitzeljagdCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(!(commandSender instanceof Player)) {
            commandSender.sendRichMessage("You must be a player to run this command!");
            return false;
        }

        Player player = (Player) commandSender;
        if(player.hasPermission("youtuberinsel.schatzsuche.start")) {
            PlayerData playerData = PlayerUtility.getPlayerData(player);
            int step = playerData.getSchnitzeljagdschritt();
            String tipp = SchnitzeljagdUtility.getSchnitzeljagdData().get(step);
            player.sendRichMessage(tipp);
        }

        return true;
    }
}
