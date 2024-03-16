package org.pyrex.entainuker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {
    private final Player player;
    private Location kickLocation;

    public void run() {
        if (Entainuker.brockbreakcounter.get(this.player) >= 10) {
            this.kickLocation = this.player.getLocation();
            this.player.kickPlayer("Nuker alapos gyanújával le van tartóztatva!");
            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (!pl.hasPermission("entainuker.figyelmeztetes")) continue;
                pl.sendMessage(ChatColor.RED + "Nukerezett a " + this.player.getName() + " nevű játékos.");
                pl.sendMessage("A játékos legutolsó ismert helye:");
                pl.sendMessage("X\uff1a" + this.kickLocation.getX());
                pl.sendMessage("Y\uff1a" + this.kickLocation.getY());
                pl.sendMessage("Z\uff1a" + this.kickLocation.getZ());
            }

        }
        Entainuker.brockbreakcounter.put(this.player, 0);
        Entainuker.playertimer.put(this.player, false);
    }

    Timer(Player p) {
        this.player = p;
    }
}
