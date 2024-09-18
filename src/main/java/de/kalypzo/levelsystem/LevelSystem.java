package de.kalypzo.levelsystem;

import de.kalypzo.levelsystem.database.RedisManager;
import de.kalypzo.levelsystem.player.PlayerLevelManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LevelSystem extends JavaPlugin {

    private RedisManager redisManager;
    private PlayerLevelManager playerLevelManager;

    @Override
    public void onEnable() {
        /*
         * Initialize the RedisManager
         */
        redisManager = new RedisManager("localhost", 6379);
        redisManager.connect();

        /*
         * Initialize the PlayerLevelManager
         */
        playerLevelManager = new PlayerLevelManager(this);



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
}
