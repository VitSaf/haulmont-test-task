package utils;

import java.sql.*;

public class DbCreator {
    private Connection con;

    public DbCreator(){
        try{
            Class.forName("org.hsqldb.jdbcDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:.", "sa", "");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void tryConnWithCreating(){
        Statement stat = null;
        try {
            stat = this.con.createStatement();
            stat.executeUpdate("CREATE TABLE test(id integer, value VARCHAR(250) )");

            stat.executeUpdate("INSERT INTO test VALUES(1,'value')");
            selectAllPrinter(this.con.prepareStatement("SELECT * FROM test").executeQuery());

            //stat.executeQuery("DROP TABLE test");
            try {
                stat.executeQuery("SELECT * FROM test");
            } catch (SQLSyntaxErrorException sqlE) {
                System.out.println("Test connection succeeded");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void check(){
        try {
            selectAllPrinter(this.con.prepareStatement("SELECT * FROM test").executeQuery());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void tryFlyway(){
        try {
            selectAllPrinter(this.con.prepareStatement("SELECT * FROM client").executeQuery());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void selectAllPrinter(ResultSet rs) throws SQLException {
            while (rs.next()) {
                String s = null;
                s = rs.getString(1) + " : " + rs.getString(2);
                System.out.println("Query result is:" + s);
            }

    }

    public static void main(String[] args) {
        DbCreator tester =new DbCreator();
        //tester.tryFlyway();
        //tester.tryConnWithCreating();
        tester.check();
    }
}
