package org.pyrex.entainuker;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Entainuker
        extends JavaPlugin
        implements Listener {
    public static HashMap<Player, Integer> brockbreakcounter = new HashMap();
    public static HashMap<Player, Boolean> playertimer = new HashMap();

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
        this.getLogger().info("A plugin sikeresen elindult. A Plugint \u00edrta: Pyrex");
    }

    public void onDisable() {
    }

    @EventHandler
    public void getBlockbreakEvent(BlockBreakEvent e) {
        Player p = e.getPlayer();
        brockbreakcounter.put(p, brockbreakcounter.get(p) + 1);
        if (!playertimer.get(p).booleanValue()) {
            new Timer(p).runTaskLater((Plugin)this, 2L);
            playertimer.put(p, true);
        }
    }


    @EventHandler
    public void getPlayerjoinEvent(PlayerJoinEvent e) {
        brockbreakcounter.put(e.getPlayer(), 0);
        playertimer.put(e.getPlayer(), false);
    }
}
