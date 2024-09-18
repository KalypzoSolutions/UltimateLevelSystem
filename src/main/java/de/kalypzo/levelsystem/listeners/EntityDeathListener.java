package de.kalypzo.levelsystem.listeners;

import de.kalypzo.levelsystem.LevelSystem;
import de.kalypzo.levelsystem.categorys.CombatLevel;
import de.kalypzo.levelsystem.categorys.GatheringLevel;
import de.kalypzo.levelsystem.categorys.LevelCategory;
import de.kalypzo.levelsystem.player.PlayerLevelManager;
import de.kalypzo.levelsystem.xp.XpManager;
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
            playerLevelManager.addXp(player.getUniqueId(), levelCategory, XpManager.getXpAmountByType(event.getEntity().getType().toString()));

        }

    }
}
