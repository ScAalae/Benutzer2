import java.awt.print.Book;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BenutzerDAO {
	 private String jdbcURL;
     private String jdbcUsername;
     private String jdbcPassword;
     private Connection jdbcConnection;

     public BenutzerDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
         this.jdbcURL = jdbcURL;
         this.jdbcUsername = jdbcUsername;
         this.jdbcPassword = jdbcPassword;
     }

     protected void connect() throws SQLException {
         if (jdbcConnection == null || jdbcConnection.isClosed()) {
             try {
                 //Diver loading
                 Class.forName("org.postgresql.Driver");
             } catch (ClassNotFoundException e) {
                 throw new SQLException(e);
             }
             jdbcConnection = DriverManager.getConnection(
                                         jdbcURL, jdbcUsername, jdbcPassword);
         }
     }
     protected void disconnect() throws SQLException {
         if (jdbcConnection != null && !jdbcConnection.isClosed()) {
             jdbcConnection.close();
         }
     }

     public boolean insertBenutzer(Benutzer benutzer) throws SQLException {
         String sql = "INSERT INTO benutzer (vorname, nachname, email, createdon) VALUES (?, ?, ?, ?)";
         connect();

         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, benutzer.getVorname());
         statement.setString(2, benutzer.getNachname());
         statement.setString(3, benutzer.getEmail());
         statement.setString(4, new Date().toString()); 
         
         //falls gleiche row, keine widerholung
         boolean rowInserted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowInserted; 
     }
     
     public List<Benutzer> listAllBenutzer() throws SQLException {

         List<Benutzer> listBenutzer = new ArrayList<>();

         String sql = "SELECT * FROM benutzer";

         connect();

         Statement statement = jdbcConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);

         while (resultSet.next()) {
             int id = resultSet.getInt("id");
             String vorname = resultSet.getString("vorname");
             String nachname= resultSet.getString("nachname");
             String email = resultSet.getString("email");
             String createdOn = resultSet.getString("createdon");

            Benutzer benutzer = new Benutzer(id, vorname , nachname, email, createdOn);
             listBenutzer.add(benutzer);
         }

         resultSet.close();
         statement.close();

         disconnect();

         return listBenutzer;
     }
     public boolean deleteBenutzer(Benutzer benutzer) throws SQLException {
         String sql = "DELETE FROM benutzer where id = ?";

         connect();

         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, benutzer.getId());

         boolean rowDeleted = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowDeleted;
     }
     
     
     public boolean updateBenutzer(Benutzer benutzer) throws SQLException {
         String sql = "UPDATE benutzer SET vorname = ?, nachname = ?, email = ?, createdon = ?";
         sql += " WHERE id = ?";
         connect();

         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setString(1, benutzer.getVorname());
         statement.setString(2, benutzer.getNachname());
         statement.setString(3, benutzer.getEmail());
         statement.setString(4, new Date().toString() );
         statement.setInt(5, benutzer.getId());

         boolean rowUpdated = statement.executeUpdate() > 0;
         statement.close();
         disconnect();
         return rowUpdated;
     }
     
     
     
     public Benutzer getBenutzer(int id) throws SQLException {
         Benutzer benutzer = null;
         String sql = "SELECT * FROM benutzer WHERE id = ?";

         connect();

         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, id);

         ResultSet resultSet = statement.executeQuery();

         if (resultSet.next()) {
             String vorname = resultSet.getString("vorname");
             String nachname = resultSet.getString("nachname");
             String email = resultSet.getString("email");
             String createdOn = resultSet.getString("createdon");

             benutzer = new Benutzer(id, vorname, nachname, email, createdOn);
         }

         resultSet.close();
         statement.close();

         return benutzer;
     } 
}
