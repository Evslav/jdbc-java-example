import java.sql.*;
import java.util.Properties;

public class JDBC_Controller {

    public static void main(String[] args){

        getConnection(DB_URL);

        create_DB("Test1");
        show_DB_list();
        delete_DB("Test1");
        show_DB_list();

        getConnection(DB_URL+"STUDENTS");
        create_table();
        insert_data();
        print_list_();


    }

    static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";
    static Connection conn = null;
    static Statement stmt = null;

    static String USER = "root";
    static String PASS = "Evslav2718";

    public static void getConnection(String DB_URL){
        Properties connectionProps = new Properties();
        connectionProps.put("user", USER);
        connectionProps.put("password", PASS);
        try {
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(DB_URL,
                    connectionProps);
            stmt = conn.createStatement();
            System.out.println("//Connection success");
        } catch (SQLException ex) {
            // handle any errors
            ex.printStackTrace();
        } catch (Exception ex) {
            // handle any errors
            ex.printStackTrace();
        }
    }

    public static void create_DB(String DB_Name) {
        try{
            System.out.println("//Creating database...");

            String sql = "CREATE DATABASE " + DB_Name;
            stmt.executeUpdate(sql);
            System.out.println("//Database created successfully...");
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void delete_DB(String DB_Name) {
        try{
            System.out.println("//Deleting database...");

            String sql = "DROP DATABASE " + DB_Name;
            stmt.executeUpdate(sql);
            System.out.println("//Database delete successfully...");
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void show_DB_list(){

        ResultSet resultset = null;

        try {
            resultset = stmt.executeQuery("SHOW DATABASES;");

            if (stmt.execute("SHOW DATABASES;")) {
                resultset = stmt.getResultSet();
            }
            System.out.println("+--------------------+");
            System.out.println("| Database           |");
            System.out.println("+--------------------+");
            while (resultset.next()) {
                System.out.println("|- " + resultset.getString("Database"));
            }
            System.out.println("+--------------------+");
        }
        catch (SQLException ex){
            // handle any errors
            ex.printStackTrace();
        }
        finally {
            // release resources
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException sqlEx) { }
                resultset = null;
            }
        }
    }

    public static void create_table(){

        ResultSet resultset = null;

        try {
            String sql = "CREATE TABLE REGISTRATION " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("//Created table in given database...");
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }


    public static void insert_data() {
        try {
            String sql = "INSERT INTO REGISTRATION " +
                    "VALUES (106, 'Zara', 'Ali', 18)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO REGISTRATION " +
                    "VALUES (107, 'Mahnaz', 'Fatma', 25)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO REGISTRATION " +
                    "VALUES (108, 'Zaid', 'Khan', 30)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO REGISTRATION " +
                    "VALUES(109, 'Sumit', 'Mittal', 28)";
            stmt.executeUpdate(sql);
            System.out.println("//Inserted records into the table...");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public static void print_list_() {
        try {
            String sql = "SELECT id, first, last, age FROM REGISTRATION";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            rs.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


}
