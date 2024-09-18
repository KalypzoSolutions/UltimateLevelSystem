package de.kalypzo.levelsystem.listeners;

import de.kalypzo.levelsystem.LevelSystem;
import de.kalypzo.levelsystem.categorys.GatheringLevel;
import de.kalypzo.levelsystem.categorys.LevelCategory;
import de.kalypzo.levelsystem.categorys.MinningLevel;
import de.kalypzo.levelsystem.player.PlayerLevelManager;
import de.kalypzo.levelsystem.xp.XpManager;
import org.bukkit.Bukkit;
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
        LevelCategory levelCategory = new MinningLevel();

        /*
         * Add XP to the player
         */
        playerLevelManager.addXp(player.getUniqueId(), levelCategory, XpManager.getXpAmountByType(event.getBlock().getType().toString()));
    }
}
