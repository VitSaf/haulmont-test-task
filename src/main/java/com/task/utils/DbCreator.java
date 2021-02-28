package com.task.utils;

import org.flywaydb.core.Flyway;

import java.sql.*;
@Deprecated
public class DbCreator {
    private final static String  DB_URL = "jdbc:hsqldb:BankDB";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";
    private final static String DB_DRIVER = "org.hsqldb.jdbcDriver";
    private final  Connection con;


    DbCreator() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }


    @Deprecated
    public void tryConnWithCreating(){
        Statement stat;
        try {
            stat = con.createStatement();
            stat.executeUpdate("CREATE TABLE test(id integer, value VARCHAR(250) )");

            stat.executeUpdate("INSERT INTO test VALUES(1,'value')");
            printResult(con.prepareStatement("SELECT * FROM test").executeQuery());

            stat.executeQuery("DROP TABLE test");
            try {
                stat.executeQuery("SELECT * FROM test");
            } catch (SQLSyntaxErrorException sqlE) {
                System.out.println("Test connection succeeded");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Deprecated
    public void tryFlyway(){
        try {
            printResult(con.prepareStatement("SELECT cl.*, cr.* FROM client cl FULL JOIN credit cr ON cr.bank_id = cl.bank_id").executeQuery());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Deprecated
    public void printResult(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        System.out.println("Query result is:");
        int cols = metaData.getColumnCount();
            while (rs.next()) {
                String s = "";
                for (int i = 1;i<=cols;i++){
                    s += metaData.getColumnName(i) + ':' + rs.getString(i) + " | ";
                }
                System.out.println(s);
            }
    }
    @Deprecated
    public static void migrate(){
        Flyway flyway = Flyway.configure().dataSource(DB_URL, DB_USER, DB_PASSWORD).load();
        flyway.migrate();
    }

//    public static void main(String[] args) {
//        DbCreator tester = null;
//        try {
//            tester = new DbCreator();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//        DbCreator.migrate();
//        tester.tryFlyway();
//        //tester.tryConnWithCreating();
//    }


}
