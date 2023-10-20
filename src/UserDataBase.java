
import java.util.List;

public abstract class UserDataBase {
    public static UserDataBase db;
    
    public static UserDataBase getLocal() {
        if (db == null || !(db instanceof LocalUserDataBase)) {
            db = new LocalUserDataBase();
        }
        return db;
    }
    
    public static UserDataBase getSQL() {
        if (db == null || !(db instanceof SQLUserDataBase)) {
            db = new SQLUserDataBase();
        }
        return db;
    }
    
    public abstract void addUser(User user);
    public abstract List<User> getUsers();
}