package com.kdg.cores.connections;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConn {

    private JedisPool jedisPool;
    private int maxTotal;

    public JedisConn(int maxTotal) {
        this.maxTotal = maxTotal;
        setConnectPool();
    }

    private void setConnectPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(this.maxTotal);

        jedisPool = new JedisPool(
                jedisPoolConfig, "172.21.32.43", 6379, 0, "dmp2018@prd", 5);
    }

    public Jedis getConn() {
        return jedisPool.getResource();
    }

    public void returnResource(Jedis jedis) {
        jedis.close();
    }

    public void poolClose() {
        jedisPool.close();
    }

    public static void main(String[] args) {
        JedisConn jedisConnect = new JedisConn(5);
        Jedis jedis = jedisConnect.getConn();
        String pong = jedis.ping();
        System.out.println(jedis.keys("*"));
        System.out.println(pong);
        jedisConnect.returnResource(jedis);
        jedisConnect.poolClose();
    }
}
