package com.kdg.cores.pubCores;

import com.kdg.cores.connections.MysqlConn;
import com.kdg.cores.entity.Agent;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MysqlTools {


    public static Map<String, Agent> getAgents() throws SQLException, ClassNotFoundException {

        HashMap<String, Agent> agents = new HashMap<String, Agent>();

        Connection conn = MysqlConn.getPaizhiConn();
        Statement statement = conn.createStatement();

        String sql = "SELECT\n" +
                "      `id`,\n" +
                "      `user`,\n" +
                "      `password`,\n" +
                "      JSON_UNQUOTE( JSON_EXTRACT( `oauth`, \"$.token\" ) ) as token \n" +
                "    FROM\n" +
                "      `agents_decrypt` \n" +
                "    WHERE\n" +
                "      `platform_id` = 5 \n" +
                "      AND `oauth` IS NOT NULL";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String agentId = resultSet.getString("id");
            String user = resultSet.getString("user");
            String password = resultSet.getString("password");
            String token = resultSet.getString("token");

            agents.put(agentId, new Agent(agentId, user, password, token));
        }

        statement.close();
        conn.close();

        return agents;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Map<String, Agent> agents = MysqlTools.getAgents();

        System.out.println(agents.toString());
    }
}
