package net.kunmc.lab.duplicatesarrows;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage("引数が不足しています。");
            return false;
        }

        try {
            switch (args[0]) {
                case Config.COMMAND_ADD:
                case Config.COMMAND_REMOVE:
                    if (args.length < 2) {
                        sender.sendMessage("引数が不足しています。");
                        return false;
                    } else {
                        switch (args[0]) {
                            case Config.COMMAND_ADD:
                                if (args[1].equals("@a")) {
                                    ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                                    players.removeIf(e -> Config.playerList.contains(e));
                                    Config.playerList.addAll(players);
                                    sender.sendMessage("全てのプレイヤーを拡散射撃対象に設定しました。");
                                    break;
                                } else if (args[1].equals("@r")) {
                                    ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                                    players.removeIf(e -> Config.playerList.contains(e));
                                    if (players.size() < 1) {
                                        sender.sendMessage("全てのプレイヤーがすでに拡散射撃対象です。");
                                    } else {
                                        Random random = new Random();
                                        Player p = players.get(random.nextInt(players.size()));
                                        Config.playerList.add(p);
                                        sender.sendMessage("拡散射撃対象に " + p.getName() + " を追加しました。");
                                    }
                                    break;
                                } else {
                                    Player p = Bukkit.getPlayer(args[1]);
                                    if (p != null) {
                                        if (Config.playerList.contains(p)) {
                                            sender.sendMessage("拡散射撃対象にすでに追加されています。");
                                            return true;
                                        }
                                        Config.playerList.add(p);
                                        sender.sendMessage("拡散射撃対象に " + p.getName() + " を追加しました。");
                                    } else {
                                        sender.sendMessage("存在しないプレイヤーです。");
                                        return false;
                                    }
                                }
                                break;
                            case Config.COMMAND_REMOVE:
                                if (args[1].equals("@a")) {
                                    Config.playerList.clear();
                                    sender.sendMessage("全てのプレイヤーを拡散射撃対象から除外しました。");
                                    break;
                                } else if (args[1].equals("@r")) {
                                    if (Config.playerList.size() < 1) {
                                        sender.sendMessage("拡散射撃対象がすでに存在しません。");
                                    } else {
                                        Random random = new Random();
                                        Player p = Config.playerList.get(random.nextInt(Config.playerList.size()));
                                        Config.playerList.remove(p);
                                        sender.sendMessage("拡散射撃対象から " + p.getName() + " を除外しました。");
                                    }
                                    break;
                                } else {
                                    Player p = Bukkit.getPlayer(args[1]);
                                    if (p != null) {
                                        Config.playerList.remove(p);
                                        sender.sendMessage("拡散射撃対象から " + p.getName() + " を除外しました。");
                                    } else {
                                        sender.sendMessage("拡散射撃対象に存在しないプレイヤーです。");
                                        return false;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case Config.COMMAND_CONFIG:
                    if (args.length < 2) {
                        sender.sendMessage("引数が不足しています。");
                        return false;
                    } else {
                        switch (args[1]) {
                            case Config.COMMAND_CHECK:
                                sender.sendMessage("***DuplicatesArrows 設定値一覧***");
                                StringJoiner sj = new StringJoiner(",");
                                for (Player p : Config.playerList) {
                                    sj.add(p.getName());
                                }
                                sender.sendMessage("拡散射撃対象プレイヤー： " + sj);
                                sender.sendMessage("拡散射撃数： " + Config.MULTIPLE);
                                sender.sendMessage("射撃スピード倍率： " + Config.SPEED);
                                sender.sendMessage("拡散範囲： " + Config.SPREAD);
                                break;
                            case Config.COMMAND_MULTIPLE:
                                if (Integer.parseInt(args[2]) < 1) {
                                    sender.sendMessage("拡散射撃数は1以上の整数である必要があります。");
                                    return false;
                                }
                                Config.MULTIPLE = Integer.parseInt(args[2]);
                                sender.sendMessage("拡散射撃数： " + Config.MULTIPLE);
                                break;
                            case Config.COMMAND_SPEED:
                                if (Float.parseFloat(args[2]) <= 0) {
                                    sender.sendMessage("射撃スピード倍率は0より大きい数である必要があります。");
                                    return false;
                                }
                                Config.SPEED = Float.parseFloat(args[2]);
                                sender.sendMessage("射撃スピード倍率： " + Config.SPEED);
                                break;
                            case Config.COMMAND_SPREAD:
                                if (Float.parseFloat(args[2]) <= 0) {
                                    sender.sendMessage("拡散範囲は0より大きい数である必要があります。");
                                    return false;
                                }
                                Config.SPREAD = Float.parseFloat(args[2]);
                                sender.sendMessage("拡散範囲： " + Config.SPREAD);
                                break;
                            case Config.COMMAND_RESET:
                                Config.playerList = Config.DEF_playerList;
                                Config.MULTIPLE = Config.DEF_MULTIPLE;
                                Config.SPEED = Config.DEF_SPEED;
                                Config.SPREAD = Config.DEF_SPREAD;

                                sender.sendMessage("全ての設定を初期化しました。");
                                sender.sendMessage("***DuplicatesArrows 設定値一覧***");
                                sender.sendMessage("拡散射撃対象プレイヤー： ");
                                sender.sendMessage("拡散射撃数： " + Config.MULTIPLE);
                                sender.sendMessage("射撃スピード倍率： " + Config.SPEED);
                                sender.sendMessage("拡散範囲： " + Config.SPREAD);
                                break;
                            default:
                                sender.sendMessage("存在しない引数です。");
                                return false;
                        }
                        break;
                    }
                default:
                    sender.sendMessage("存在しない引数です。");
                    return false;
            }
        } catch (Exception e) {
            sender.sendMessage("引数が不正です。");
            return false;
        }

        return true;
    }
}
