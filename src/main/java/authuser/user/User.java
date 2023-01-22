package authuser.user;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity // creando tabla
@Table(name = "users") // llamando tabla en la bd
public class User {
    private @Id @GeneratedValue long id;
    private @NotBlank String username;
    private @NotBlank String password;
    private @NotBlank boolean loggedIn;

    public User() {
    }

    // constructor, recibe los valores del nuevo registro
    public User(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public boolean equals(Object o) {// comparando los datos a registrar con los datos de la bd
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {// generando hash, un valor encriptado, del objeto a registrar
        return Objects.hash(id, username, password, loggedIn);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn + '}';// presentando los datos a registrar
    }
}
