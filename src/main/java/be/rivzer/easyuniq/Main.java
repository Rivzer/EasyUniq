package be.rivzer.easyuniq;

import be.rivzer.easyuniq.Commands.uniq;
import be.rivzer.easyuniq.Config.Config;
import be.rivzer.easyuniq.Listeners.InventoryClick;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Economy econ = null;

    @Override
    public void onEnable() {
        //Console
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        //Configs
        Config.createCustomConfig1();

        //Vault
        if(!setupEconomy()){
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        //Commands
        new uniq(this);

        //Listeners
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(), this);

        //Console Logs
        console.sendMessage(Logger.color("&f----------------------------------------"));
        console.sendMessage(Logger.color(""));
        console.sendMessage(Logger.color("&5&lPlugin wordt aangezet..."));
        console.sendMessage(Logger.color(""));
        console.sendMessage(Logger.color("&5&lCoded by&f&l: Rivzer"));
        console.sendMessage(Logger.color(""));
        console.sendMessage(Logger.color("&f----------------------------------------"));
    }

    @Override
    public void onDisable() {
        //Console
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        //Console Logs
        console.sendMessage(Logger.color("&f----------------------------------------"));
        console.sendMessage(Logger.color(""));
        console.sendMessage(Logger.color("&c&lPlugin wordt uitgezet..."));
        console.sendMessage(Logger.color(""));
        console.sendMessage(Logger.color("&c&lCoded by&f&l: Rivzer"));
        console.sendMessage(Logger.color(""));
        console.sendMessage(Logger.color("&f----------------------------------------"));
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
