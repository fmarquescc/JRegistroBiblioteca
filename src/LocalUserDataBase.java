import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocalUserDataBase extends UserDataBase {
    private final Path savePath;
    private List<User> users = new ArrayList<>();
    
    public LocalUserDataBase() {
        this.savePath = Path.of("accounts.txt");
        this.loadUsers();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
        this.saveUsers();
    }

    @Override
    public List<User> getUsers() {
        this.loadUsers();
        return this.users;
    }
    
    private void saveUsers() {
        try (BufferedWriter output =  Files.newBufferedWriter(this.savePath, StandardCharsets.UTF_8)) {
            for (int i = 0; i < this.users.size(); ++i) {
                User user = this.users.get(i);
                output.write(user.name() + ';');
                output.write(user.email() + ';');
                output.write(user.morada() + ';');
                output.write(user.telefone()+ ';');
                output.write(user.nif()+ ';');
                output.write(user.login()+ ';');
                output.write(user.password());
                if (i + 1 < this.users.size()) output.write('\n');
            }
        } catch (IOException e) {
        }
    }
    
    private void loadUsers() {
        try (BufferedReader reader = Files.newBufferedReader(savePath)) {
            this.users.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(";");
                System.out.println(userData.length);
                User user = new User(userData[0], userData[1], userData[2], userData[3], userData[4], userData[5], userData[6]);
                this.users.add(user);
            }
        } catch (IOException e) {
        }
    }
}
