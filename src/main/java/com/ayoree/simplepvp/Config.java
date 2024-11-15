package com.ayoree.simplepvp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Config {

    private final Plugin plugin;
    protected final File configFile;

    public static boolean EXIT_KILL = true;
    public static boolean PVP_TIMER = true;
    public static int PVP_TIME = 15;
    public static Set<String> blockedCmds = new HashSet<>();

    public static String MSG_NO_PERMISSION = "§f[§4§lPVP§r§f] §cУ вас нет прав для PVP в данном режиме";
    public static String MSG_ON_PVP_TIME = "§cДо выхода из §lPVP§r§c осталось §e{time}§c секунд";
    public static String MSG_ON_PVP_COMMAND = "§cВы не можете выполнять данную команду во время PVP";
    public static String MSG_EXIT_ON_PVP = "§e{player} вышел во время §cPVP§e и был наказан!";
    public static String MSG_END_PVP = "§aВы вышли из §lPVP§r§a режима";

    public Config(SimplePVP plugin) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), "config.yml");
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        loadAll();
    }

    private void loadAll() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        PVP_TIMER = config.getBoolean("pvp-timer", PVP_TIMER);
        PVP_TIME = config.getInt("pvp-time", PVP_TIME);
        MSG_NO_PERMISSION = config.getString("no-permission-msg", MSG_NO_PERMISSION);

        EXIT_KILL = config.getBoolean("exit-pvp-kill", EXIT_KILL);
        if (EXIT_KILL) {
            MSG_EXIT_ON_PVP = config.getString("exit-on-pvp-msg", MSG_EXIT_ON_PVP);
        }

        if (PVP_TIMER) {
            MSG_ON_PVP_TIME = config.getString("on-pvp-msg", MSG_ON_PVP_TIME);
            MSG_ON_PVP_COMMAND = config.getString("command-on-pvp-msg", MSG_ON_PVP_COMMAND);
            MSG_END_PVP = config.getString("end-pvp-msg", MSG_END_PVP);

            List<String> commandsSection = config.getStringList("blocked-cmds");
            blockedCmds.addAll(commandsSection);
        }
    }
}