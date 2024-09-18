package de.kalypzo.levelsystem;

import de.kalypzo.levelsystem.database.RedisManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LevelSystem extends JavaPlugin {

    private RedisManager redisManager;

    @Override
    public void onEnable() {
        /**
         * Initialize the RedisManager
         */
        redisManager = new RedisManager("localhost", 6379);
        redisManager.connect();

    }

    @Override
    public void onDisable() {
        /**
         * Disconnect from the Redis database
         */
        redisManager.disconnect();

    }


    /**
     * Get the RedisManager
     * @return the RedisManager
     */
    public RedisManager getRedisManager() {
        return redisManager;
    }
}
