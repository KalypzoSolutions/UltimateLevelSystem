package de.kalypzo.levelsystem;

import de.kalypzo.levelsystem.database.RedisManager;
import de.kalypzo.levelsystem.listeners.BlockBreakListener;
import de.kalypzo.levelsystem.listeners.EntityDeathListener;
import de.kalypzo.levelsystem.player.PlayerLevelManager;
import de.kalypzo.levelsystem.xp.MongoXpProvider;
import de.kalypzo.levelsystem.xp.XpProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class LevelSystem extends JavaPlugin {

    private RedisManager redisManager;
    private PlayerLevelManager playerLevelManager;
    private XpProvider xpProvider;

    @Override
    public void onEnable() {
        /*
         * Initialize the RedisManager
         */
        redisManager = new RedisManager("localhost", 6379);
        redisManager.connect();

        this.xpProvider = new MongoXpProvider();

        /*
         * Initialize the Classes
         */
        playerLevelManager = new PlayerLevelManager(this);

        /*
         * Register the Listeners
         */
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityDeathListener(this), this);


    }

    @Override
    public void onDisable() {
        /*
         * Disconnect from the Redis database
         */
        redisManager.disconnect();

    }

    public XpProvider getXpProvider() {
        return xpProvider;
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public PlayerLevelManager getPlayerLevelManager() {
        return playerLevelManager;
    }
}
