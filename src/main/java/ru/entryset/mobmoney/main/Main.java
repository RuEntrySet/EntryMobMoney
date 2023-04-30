package ru.entryset.mobmoney.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.entryset.api.bukkit.configuration.Config;
import ru.entryset.api.bukkit.manager.Messager;
import ru.entryset.mobmoney.events.Events;

public class Main extends JavaPlugin {

    public static Config config;

    public static Messager messager;

    @Override
    public void onEnable() {
        config = new Config(this, "config.yml");
        messager = new Messager(config);
        registerEvents();
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new Events(), this);
    }

}
