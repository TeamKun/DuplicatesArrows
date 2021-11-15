package net.kunmc.lab.duplicatesarrows;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public final static String MAIN_COMMAND = "duparrow";

    public final static String COMMAND_ADD = "addPlayer";
    public final static String COMMAND_REMOVE = "removePlayer";
    public final static String COMMAND_CONFIG = "config";
    public final static String COMMAND_CHECK = "check";
    public final static String COMMAND_RESET = "reset";
    public final static String COMMAND_MULTIPLE = "multiple";
    public final static String COMMAND_SPEED = "speed";
    public final static String COMMAND_SPREAD = "spread";

    public static List<Player> playerList = new ArrayList<>();
    public static int MULTIPLE = 100;
    public static float SPEED = 1f;
    public static float SPREAD = 10f;

    public final static List<Player> DEF_playerList = playerList;
    public final static int DEF_MULTIPLE = MULTIPLE;
    public final static float DEF_SPEED = SPEED;
    public final static float DEF_SPREAD = SPREAD;
}
