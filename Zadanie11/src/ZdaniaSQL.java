import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ZdaniaSQL implements GeneratorZdan {
    String dbPath;

    @Override
    public void plikBazyDanych(String filename) {
        // basically a constructor
        this.dbPath = filename;
    }

    @Override
    public String zbudujZdanie(int zdanieID) {
        // connects to a DB and then executes some queries to get a result
        Connection conn = connectToDatabase();
        ArrayList<Integer> listedStatement = getZdanieMap(zdanieID, conn);
        ArrayList<String> statement = findStatement(conn, listedStatement);
        return createStatementFromList(statement);
    }

    public Connection connectToDatabase() {
        // returns a database connection
        Connection conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to database!");
        return conn;
    }

    public ArrayList<String> findStatement(Connection conn, ArrayList<Integer> ids) {
        // queries the database based on given indexes
        Statement statement = null;
        ArrayList<String> result = new ArrayList<>();
        try {
            statement = conn.createStatement();
            for (int i = 0; i < ids.size(); i++) {
                if (i == 0) {
                    ResultSet rs = statement.executeQuery("SELECT Imie, Plec FROM Imie WHERE ImieID = " + ids.get(i));
                    result.add(rs.getString("Imie"));
                    result.add(rs.getInt("Plec") + "");
                } else if (i == 1) {
                    ResultSet rs = statement.executeQuery("SELECT Nazwa FROM Czynnosc WHERE CzynnoscID = " + ids.get(i));
                    result.add(rs.getString("Nazwa"));
                } else if (i == 2) {
                    ResultSet rs = statement.executeQuery("SELECT Nazwa FROM Przedmiot WHERE PRzedmiotID = " + ids.get(i));
                    result.add(rs.getString("Nazwa"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }



    public ArrayList<Integer> getZdanieMap(Integer id, Connection conn){
        // returns indexes of name, thing to do and object
        Statement statement = null;
        ArrayList<Integer> ids = new ArrayList<>(); // IMIE, CZYNNOSC, PRZEDMIOT
        Map<String, Integer> resultMap = new HashMap<>();
        try{
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Zdanie WHERE ZdanieID = " + id);
            while (rs.next()){
                ids.add(rs.getInt("ImieID"));
                ids.add(rs.getInt("CzynnoscID"));
                ids.add(rs.getInt("PrzedmiotID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    private String createStatementFromList(ArrayList<String> splitStatement){
        // builds a string based on listed statement
        if (splitStatement.get(1).equals("0")){
            splitStatement.set(2, splitStatement.get(2) + "a");
        }
        splitStatement.remove(1);
        return String.join(" ", splitStatement) + ".";
    }
}

