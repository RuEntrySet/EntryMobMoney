package ru.entryset.mobmoney;

import ru.entryset.core.EntryCore;
import ru.entryset.core.bukkit.bukkit.configuration.Config;
import ru.entryset.core.bukkit.bukkit.manager.Messager;
import ru.entryset.core.expansion.Expansion;

public class EntryMobMoney extends Expansion {

    public static Config config;

    public static Messager messager;

    @Override
    public void init() {
        config = new Config(this, "EntryMobMoney.yml");
        messager = new Messager(config);
        registerEvents();
    }

    private void registerEvents() {
        EntryCore.getInstance().getRegistryManager().registerHandlers(this, new Events());
    }

}
