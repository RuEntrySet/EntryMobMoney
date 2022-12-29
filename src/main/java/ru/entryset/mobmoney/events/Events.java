package ru.entryset.mobmoney.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import ru.entryset.mobmoney.hook.Money;
import ru.entryset.mobmoney.Main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Events implements Listener {

    @EventHandler
    public void onDamage(EntityDeathEvent e){
        if(e.getEntity() instanceof Player){
            return;
        }
        Entity entity = e.getEntity();
        if(e.getEntity().getKiller() == null){
            return;
        }
        Player damager = e.getEntity().getKiller();

        List<String> list = new ArrayList<>();

        for(String str : Main.config.getStringList("settings.mobs")){
            String s = str.split(":")[0];
            list.add(s.toUpperCase());
        }

        if(!list.contains(entity.getType().name().toUpperCase())){
            return;
        }

        if(Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        double money = 0.0;

        for(String str : Main.config.getStringList("settings.mobs")){
            if(str.split(":")[0].toUpperCase().equalsIgnoreCase(entity.getType().name())){
                double inner1 = Double.parseDouble(str.split(":")[1]);
                double inner2 = Double.parseDouble(str.split(":")[2]);
                money = inner1;
                if(damager.hasPermission(Main.config.getPermission("x2money"))){
                    money = inner2;
                }
                break;
            }
        }
        if(money == 0.0){
            return;
        }
        Money money1 = new Money(damager);
        money1.give(money);
        Main.messager.sendMessage(damager, Main.config.getMessage("getmoney").replace("<size>", money + ""), true);
    }

    @EventHandler
    public void onPlayer(PlayerDeathEvent e){

        if(e.getEntity().getKiller() == null){
            return;
        }

        Player damager = e.getEntity().getKiller();
        Player target = e.getEntity();

        if(target.equals(damager)){
            return;
        }

        if(Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return;
        }

        Money money_damager = new Money(damager);
        Money money_target = new Money(target);

        if(money_target.get() == 0D){
            return;
        }

        double money = money_target.get() * (Main.config.getInt("settings.player")/100);

        money_target.take(money);
        money_damager.give(money);

        double i = round(money, 2);
        Main.messager.sendMessage(damager, Main.config.getMessage("getmoneyplayer").replace("<size>", i + ""), true);
        Main.messager.sendMessage(target, Main.config.getMessage("target_getmoneyplayer").replace("<size>", i + ""), true);

    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
