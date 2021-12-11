package be.rivzer.easyuniq.Listeners;

import be.rivzer.easyuniq.Config.Config;
import be.rivzer.easyuniq.Logger;
import be.rivzer.easyuniq.Main;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        ItemStack is = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();

        if(is == null) return;
        if(e.getClickedInventory() == null) return;
        if(e.getClickedInventory().getName() == null) return;

        if(e.getInventory().getName().equalsIgnoreCase(Logger.color("&d&lUniq Crates"))){
            e.setCancelled(true);

            List<String> itemlijst2 = Config.getCustomConfig1().getStringList("List");
            String[] items2 = (String[])itemlijst2.toArray(new String[0]);
            String[] var12 = items2;
            int var15 = items2.length;

            for(int i = 0; i < var15; ++i) {
                try{
                    String naam = var12[i];
                    Material mat = Material.valueOf(Config.getCustomConfig1().getString("Info." + naam + ".Block"));
                    String display = Config.getCustomConfig1().getString("Info." + naam + ".BriefNaam");
                    int prijs = Config.getCustomConfig1().getInt("Info." + naam + ".Price");

                    if(is.getType().equals(mat)){
                        ItemStack paper = new ItemStack(Material.PAPER);
                        ItemMeta meta = paper.getItemMeta();
                        meta.setDisplayName(Logger.color(display));
                        ArrayList<String> lore = new ArrayList();

                        List<String> itemlijstlores = Config.getCustomConfig1().getStringList("Info." + naam + ".Lore");
                        String[] itemslore = (String[])itemlijstlores.toArray(new String[0]);
                        String[] var1lore = itemslore;
                        int var10lore = itemslore.length;

                        for(int k = 0; k < var10lore; ++k) {
                            String loretoadd = var1lore[k];

                            lore.add(Logger.color(loretoadd));
                        }

                        paper.setItemMeta(meta);

                        if(Main.getEconomy().getBalance(p) < prijs){
                            p.sendMessage(Logger.color("&cU heeft teweinig geld om deze crate te kopen!"));
                            return;
                        }

                        EconomyResponse r = Main.getEconomy().withdrawPlayer(p, prijs);
                        if(r.transactionSuccess()){
                            p.closeInventory();
                            p.getInventory().addItem(paper);
                            p.sendMessage(Logger.color("&7U heeft de crate &b" + is.getItemMeta().getDisplayName() + " &7gekocht!"));
                            return;
                        }
                        else{
                            p.sendMessage(Logger.color("&cEr ging iets fout!"));
                            return;
                        }
                    }
                }
                catch(Exception ev){
                    ev.printStackTrace();
                }
            }
        }
    }

}
