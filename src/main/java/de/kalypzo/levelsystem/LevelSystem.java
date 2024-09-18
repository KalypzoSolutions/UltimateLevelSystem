package de.kalypzo.levelsystem;

import de.kalypzo.levelsystem.database.RedisManager;
import de.kalypzo.levelsystem.listeners.BlockBreakListener;
import de.kalypzo.levelsystem.listeners.EntityDeathListener;
import de.kalypzo.levelsystem.player.PlayerLevelManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LevelSystem extends JavaPlugin {

    private RedisManager redisManager;
    private PlayerLevelManager playerLevelManager;
    private BlockBreakListener blockBreakListener;
    private EntityDeathListener entityDeathListener;

    @Override
    public void onEnable() {
        /*
         * Initialize the RedisManager
         */
        redisManager = new RedisManager("localhost", 6379);
        redisManager.connect();

        /*
         * Initialize the Classes
         */
        playerLevelManager = new PlayerLevelManager(this);
        blockBreakListener = new BlockBreakListener(this);
        entityDeathListener = new EntityDeathListener(this);

        /*
         * Register the Listeners
         */
        getServer().getPluginManager().registerEvents(blockBreakListener, this);
        getServer().getPluginManager().registerEvents(entityDeathListener, this);


    }

    @Override
    public void onDisable() {
        /*
         * Disconnect from the Redis database
         */
        redisManager.disconnect();

    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public PlayerLevelManager getPlayerLevelManager() {
        return playerLevelManager;
    }
}
