package be.rivzer.easyuniq.Guis;

import be.rivzer.easyuniq.Config.Config;
import be.rivzer.easyuniq.Logger;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Uniq {

    public static void openGUI(Player p){
        Inventory inv = Bukkit.createInventory(p, 9, Logger.color("&d&lUniq Crates"));

        List<String> itemlijst2 = Config.getCustomConfig1().getStringList("List");
        String[] items2 = (String[])itemlijst2.toArray(new String[0]);
        String[] var12 = items2;
        int var15 = items2.length;

        for(int i = 0; i < var15; ++i) {
            try{
                String naam = var12[i];
                Material mat = Material.valueOf(Config.getCustomConfig1().getString("Info." + naam + ".Block"));
                String nameg = Config.getCustomConfig1().getString("Info." + naam + ".Name");
                String nbte = Config.getCustomConfig1().getString("Info." + naam + ".NBTE-TAGG");
                ArrayList<String> lore = new ArrayList();

                List<String> itemlijstlores = Config.getCustomConfig1().getStringList("Info." + naam + ".Lore");
                String[] itemslore = (String[])itemlijstlores.toArray(new String[0]);
                String[] var1lore = itemslore;
                int var10lore = itemslore.length;

                for(int k = 0; k < var10lore; ++k) {
                    String loretoadd = var1lore[k];

                    lore.add(Logger.color(loretoadd));

                }

                setItem(inv, mat, i, lore, nameg, nbte);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        p.closeInventory();
        p.openInventory(inv);
    }

    public static void setItem(Inventory inv, Material mat, Integer slotnum, ArrayList<String> lore, String name, String nbteNaam){
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Logger.color(name));
        meta.setLore(lore);
        item.setItemMeta(meta);

        NBTItem nbti = new NBTItem(item);
        nbti.setString("mtcustom", nbteNaam);

        ItemStack item1 = nbti.getItem();

        inv.setItem(slotnum, item1);
    }

}
