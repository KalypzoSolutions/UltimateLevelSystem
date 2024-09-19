package de.kalypzo.levelsystem.listeners;

import de.kalypzo.levelsystem.LevelSystem;
import de.kalypzo.levelsystem.categorys.CombatLevel;
import de.kalypzo.levelsystem.categorys.LevelCategory;
import de.kalypzo.levelsystem.player.PlayerLevelManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    private final LevelSystem plugin;

    public EntityDeathListener(LevelSystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        if (event.getEntity().getKiller() != null) {

            PlayerLevelManager playerLevelManager = plugin.getPlayerLevelManager();
            Player player = event.getEntity().getKiller();
            LevelCategory levelCategory = new CombatLevel();

            /*
             * Add XP to the player
             */
            Integer xp = plugin.getXpProvider().getXpAmountByType(event.getEntity().getType().toString());
            if (xp != null) {
                playerLevelManager.addXp(player.getUniqueId(), levelCategory, xp);
            }
        }

    }
}
