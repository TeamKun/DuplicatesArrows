package net.kunmc.lab.duplicatesarrows;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TabComplete implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        switch (args.length) {
            case 1:
                completions.add(Config.COMMAND_ADD);
                completions.add(Config.COMMAND_REMOVE);
                completions.add(Config.COMMAND_CONFIG);
                completions.removeIf(e -> !e.startsWith(args[0].toLowerCase(Locale.ROOT)));
                break;
            case 2:
                switch (args[0]) {
                    case Config.COMMAND_ADD:
                        completions.addAll(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList()));
                        completions.removeIf(e -> Config.playerList.contains(Bukkit.getPlayer(e)));
                        completions.add(0, "@a");
                        completions.add(0, "@r");
                        completions.removeIf(e -> !e.toLowerCase(Locale.ROOT).startsWith(args[1].toLowerCase(Locale.ROOT)));
                        break;
                    case Config.COMMAND_REMOVE:
                        for (Player p : Config.playerList) {
                            completions.add(0, "@a");
                            completions.add(p.getName());
                        }
                        completions.removeIf(e -> !e.toLowerCase(Locale.ROOT).startsWith(args[1].toLowerCase(Locale.ROOT)));
                        break;
                    case Config.COMMAND_CONFIG:
                        completions.add(Config.COMMAND_CHECK);
                        completions.add(Config.COMMAND_RESET);
                        completions.add(Config.COMMAND_MULTIPLE);
                        completions.add(Config.COMMAND_SPEED);
                        completions.add(Config.COMMAND_SPREAD);
                        completions.removeIf(e -> !e.startsWith(args[1].toLowerCase(Locale.ROOT)));
                        break;
                }
        }
        return completions;
    }
}