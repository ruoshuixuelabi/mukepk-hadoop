package com.pk.project.utils;

import java.sql.*;

public class HiveJDBCApp {

    public static void main(String[] args) {
        Connection connection = null;
        try{

            Class.forName("org.apache.hive.jdbc.HiveDriver");
            connection = DriverManager.getConnection("jdbc:hive2://hadoop000:10000/default", "hadoop", "");

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from domain_cnt_traffics where d='20231028'");
            while(rs.next()) {
                System.out.println(rs.getString("domain") + "\t" + rs.getLong("cnt")+ "\t" + rs.getLong("traffics"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
