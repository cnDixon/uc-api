package com.kdg.cores.pubCores;

import com.kdg.cores.entity.Agent;
import com.kdg.cores.entity.Task;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Resource {

    private static Logger logger = Logger.getLogger(Resource.class.getSimpleName());

    /**
     * 生成源数据
     */
    public ArrayList<Task> getResource(
            String accountKey, Jedis jedis, Map inputParams, List inputAccounts, List inputAgents, String date,
            boolean isNeedAccounts) throws SQLException, ClassNotFoundException {

        ArrayList<Task> tasks;

        Map<String, Agent> agents = MysqlTools.getAgents();

        // 需要传入 账户 + 代理商 信息
        if (isNeedAccounts) {

            // 检查账户key是否存在
            if (!jedis.exists(accountKey)) {
                logger.error("input account's key: " + accountKey + " was not exists, check pls.");
                Finished.finished(1);
            }

            // 指定账户列表
            if (inputAccounts.size() > 0) {
                tasks = byAccount(agents, accountKey, jedis, inputAccounts, date);
            }

            // 指定代理商
            else if (inputAgents.size() > 0) {
                tasks = byAgentWithAccount(agents, accountKey, jedis, inputAgents, date);
            }

            // 全部账户
            else {
                tasks = allAccounts(agents, accountKey, jedis, date);
            }
        }

        // 仅需传入 代理商 信息
        else {

            // 指定代理商
            if (inputAgents.size() > 0) {
                tasks = byAgentWithoutAccount(agents, accountKey, jedis, inputAgents, date);
            }

            // 全部代理商
            else {
                tasks = allAgents(agents, accountKey, jedis, date);
            }
        }

        return tasks;
    }

    private static ArrayList<Task> byAccount(Map<String, Agent> agents, String accountKey, Jedis jedis, List inputAccounts, String date) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        return tasks;
    }

    private static ArrayList<Task> byAgentWithAccount(Map<String, Agent> agents, String accountKey, Jedis jedis, List inputAgents, String date) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        return tasks;
    }

    private static ArrayList<Task> allAccounts(Map<String, Agent> agents, String accountKey, Jedis jedis, String date) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        return tasks;
    }

    private static ArrayList<Task> byAgentWithoutAccount(Map<String, Agent> agents, String accountKey, Jedis jedis, List inputAgents, String date) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        return tasks;
    }

    private static ArrayList<Task> allAgents(Map<String, Agent> agents, String accountKey, Jedis jedis, String date) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        return tasks;
    }
}
