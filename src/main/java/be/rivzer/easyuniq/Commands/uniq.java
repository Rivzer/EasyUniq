package be.rivzer.easyuniq.Commands;

import be.rivzer.easyuniq.Guis.Uniq;
import be.rivzer.easyuniq.Logger;
import be.rivzer.easyuniq.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class uniq implements CommandExecutor {

    private Main plugin;

    public uniq(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("uniq").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(Logger.color("&f---------------------"));
            sender.sendMessage(Logger.color("&cDeze commands zijn niet bedoeld voor de console!"));
            sender.sendMessage(Logger.color("&f---------------------"));
            return true;
        }

        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("uniq")) {
            if (args.length == 0) {
                p.sendMessage(Logger.color("&f---------------------"));
                p.sendMessage("");
                p.sendMessage(Logger.color("&b/uniq help &f- Bekijk het help menu"));
                p.sendMessage("");
                p.sendMessage(Logger.color("&b/uniq buy &f- Open de gui om crates te kopen"));
                p.sendMessage("");
                p.sendMessage(Logger.color("&f---------------------"));
            }
            else if(args[0].equalsIgnoreCase("help")){
                p.sendMessage(Logger.color("&f---------------------"));
                p.sendMessage("");
                p.sendMessage(Logger.color("&b/uniq help &f- Bekijk het help menu"));
                p.sendMessage("");
                p.sendMessage(Logger.color("&b/uniq buy &f- Open de gui om crates te kopen"));
                p.sendMessage("");
                p.sendMessage(Logger.color("&f---------------------"));
            }
            else if(args[0].equalsIgnoreCase("buy")){
                p.closeInventory();
                Uniq.openGUI(p);
            }
        }

        return false;
    }

}
