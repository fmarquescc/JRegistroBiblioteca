
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SQLUserDataBase extends UserDataBase {
    public static Connection createConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/utilizador?serverTimezone=UTC";
        return DriverManager.getConnection(url, "root", "");
    }
    
    @Override
    public void addUser(User user) {
        String query = "INSERT INTO user (nome,email,morada,telefone,nif,login,password) VALUES(?,?,?,?,?,?,?)";
        
        try (Connection connection = createConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.name());
            ps.setString(2, user.email());
            ps.setString(3, user.morada());
            ps.setString(4, user.telefone());
            ps.setString(5, user.nif());
            ps.setString(6, user.login());
            ps.setString(7, user.password());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
    
        try (Connection connection = createConnection()) {
            String query = "SELECT * FROM user";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                                   rs.getString(7), rs.getString(8)));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
}
