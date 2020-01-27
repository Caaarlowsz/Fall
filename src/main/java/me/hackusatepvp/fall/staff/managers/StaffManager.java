package me.hackusatepvp.fall.staff.managers;

import lombok.Getter;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.bounty.Bounty;
import me.hackusatepvp.fall.staff.Staff;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class StaffManager {
    @Getter private Map<UUID, Staff> staff = new HashMap<>();

    private HashSet<Player> staffmode = new HashSet<>();
    @Getter private HashSet<Player> vanish = new HashSet<>();
    @Getter private Map<Player, ItemStack[]> inventory = new HashMap<>();
    @Getter private Map<Player, ItemStack[]> armor = new HashMap<>();

    public void setStaffMode(Player player) {
        staffmode.add(player);
    }

    public void setVanish(Player player) {
        vanish.add(player);
    }

    public void removeStaffMode(Player player) {
        staffmode.remove(player);
    }

    public void removeVanish(Player player) {
        vanish.remove(player);
    }

    public boolean isStaffMode(Player player) {
        return staffmode.contains(player);
    }

    public boolean isVanish(Player player) {
        return vanish.contains(player);
    }

    public ItemStack getVanish(Player player) {
        if (Fall.getInstance().getStaffManager().isVanish(player)) {
            ItemStack itemStack = new ItemStack(Material.INK_SACK, 1, (byte) 10);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(StringUtil.format("&7* &9Vanish: &7" + Fall.getInstance().getStaffManager().isVanish(player)));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        } else {
            ItemStack itemStack = new ItemStack(Material.INK_SACK, 1, (byte) 8);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(StringUtil.format("&7* &9Vanish: &7" + Fall.getInstance().getStaffManager().isVanish(player)));
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
    }

    public void giveItems(Player player) {
        player.getInventory().addItem(getVanish(player));
    }

    public void addStaff(UUID uuid, Staff staffs) {
        staff.put(uuid, staffs);
    }

    public Staff getStaff(UUID uuid) {
        return staff.get(uuid);
    }

    public boolean isStaff(UUID uuid) {
        return staff.containsKey(uuid);
    }

    public void removeStaff(UUID uuid) {
        staff.remove(uuid);
    }

}
