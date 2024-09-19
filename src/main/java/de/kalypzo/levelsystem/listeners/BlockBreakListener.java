package de.kalypzo.levelsystem.listeners;

import de.kalypzo.levelsystem.LevelSystem;
import de.kalypzo.levelsystem.categorys.LevelCategory;
import de.kalypzo.levelsystem.player.PlayerLevelManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private final LevelSystem plugin;

    public BlockBreakListener(LevelSystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        PlayerLevelManager playerLevelManager = plugin.getPlayerLevelManager();
        Player player = event.getPlayer();

        /*
         * Add XP to the player
         */
        Integer xp = plugin.getXpProvider().getXpAmountByType(event.getBlock().getType().toString());
        if (xp != null) {
            playerLevelManager.addXp(player.getUniqueId(), LevelCategory.MINING_LEVEL, xp);
        }
    }
}
