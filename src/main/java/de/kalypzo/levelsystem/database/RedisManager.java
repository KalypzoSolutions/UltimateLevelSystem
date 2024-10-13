package de.kalypzo.levelsystem.database;

import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisManager {

    private final String host;
    private final int port;
    private Jedis jedis;
    private final ExecutorService executorService;

    public RedisManager(String host, int port) {
        this.host = host;
        this.port = port;
        this.executorService = Executors.newCachedThreadPool();
    }


    /*
     * Connect to the Redis database
     */
    public void connect() {
        jedis = new Jedis(host, port);
    }


    /*
     * Disconnect from the Redis database
     */
    public void disconnect() {
        if (jedis != null) {
            jedis.close();
        }
        executorService.shutdown();
    }





    /**
     * Read data from the Redis database asynchronously
     *
     * @param key is the key to get the data from
     * @return the data from the key
     */
    public CompletableFuture<String> getDataAsync(String key) {
        return CompletableFuture.supplyAsync(() -> {
            if (jedis.exists(key)) {
                return jedis.get(key);
            } else {
                System.out.println("No data found for key: " + key);
                return null;
            }
        }, executorService);
    }


    /**
     * Set data in the Redis database asynchronously
     * @param key is the key to set the data to
     * @param value is the data to set
     * @return a CompletableFuture<Void> to indicate the completion of the operation
     */
    public CompletableFuture<Void> setDataAsync(String key, String value) {
        return CompletableFuture.runAsync(() -> jedis.set(key, value), executorService);
    }


    /**
     * Delete data from the Redis database asynchronously
     * @param key is the key to delete the data from
     * @return a CompletableFuture<Void> to indicate the completion of the operation
     */
    public CompletableFuture<Void> deleteDataAsync(String key) {
        return CompletableFuture.runAsync(() -> {
            if (jedis.exists(key)) {
                jedis.del(key);
            } else {
                System.out.println("No data found for key: " + key);
            }
        }, executorService);
    }


    /**
     * Set multiple data in the Redis database asynchronously
     * @param dataMap is the map of keys and values to set
     * @return a CompletableFuture<Void> to indicate the completion of the operation
     */
    public CompletableFuture<Void> setBulkDataAsync(Map<String, String> dataMap) {
        return CompletableFuture.runAsync(() -> {
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                jedis.set(entry.getKey(), entry.getValue());
            }
        }, executorService);
    }


    /**
     * Get the Jedis object
     * @return the Jedis object
     */
    public Jedis getJedis() {
        return jedis;
    }
}