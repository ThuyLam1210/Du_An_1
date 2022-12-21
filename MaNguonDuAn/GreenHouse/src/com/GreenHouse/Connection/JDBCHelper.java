package com.GreenHouse.Connection;

import java.sql.*;

public class JDBCHelper {

    public static Connection conn = null;
    protected Connection con;
    //  static String urlDB = "jdbc:sqlserver://localhost:1433;databaseName = QuanLy_TourDuLichGreenHouse";
    static String url = "jdbc:sqlserver://localhost:1433;databaseName = QuanLy_TourDuLichGreenHouse";
    static String usersName = "sa";
    static String password = "123";

    public static Connection dbConnection(){
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url,usersName,password);
           //  System.out.println("KET NOI CSDL THANH CONG");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("LOI CSDL ");
        }
        return conn;
    }
    public JDBCHelper() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String s = System.getProperty("os.name");
            if (s.contains("Windows")) {
                con = DriverManager.getConnection(url, usersName, password);
                // System.out.println("Connect");
            } else {
                con = DriverManager.getConnection(url, usersName, password);
                // System.out.println("Connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet ExcuteQueryGetTable(String cauTruyVanSQL) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(cauTruyVanSQL);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return null;
    }

    public static PreparedStatement getStm(String sql, Object... args) throws SQLException {
        Connection conn = DriverManager.getConnection(url, usersName, password);
        PreparedStatement stm;
        if (sql.trim().startsWith("{")) {
            stm = conn.prepareCall(sql);
        } else {
            stm = conn.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            stm.setObject(i + 1, args[i]);
        }
        return stm;
    }

    public static ResultSet query(String sql, Object... args) throws SQLException {
        PreparedStatement stm = JDBCHelper.getStm(sql, args);
        return stm.executeQuery();
    }

    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int update(String sql, Object... args) {
        try {
            PreparedStatement stm = JDBCHelper.getStm(sql, args);
            try {
                return stm.executeUpdate();
            } finally {
                stm.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement prepareStatement(String select__from_) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
