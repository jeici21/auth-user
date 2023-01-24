package authuser.user.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import authuser.user.entities.Status;
import authuser.user.entities.User;
import authuser.user.repositories.UserRepository;
import jakarta.validation.Valid;

@RestController // controla la funcionalidad de la api
public class UserController {
    @Autowired // para no tener que configurar un contructor
    UserRepository userRepository;

    @PostMapping("/users/register")
    public Status registerUser(@Valid @RequestBody User newUser) {
        List<User> users = userRepository.findAll();// creando una lista de todos los usuarios
        System.out.println("New user: " + newUser.toString());// presentando los datos registrados
        for (User user : users) {// iterando por todos los usuarios
            System.out.println("Registered user: " + newUser.toString());
            if (user.equals(newUser)) {
                System.out.println("User Already exists!");// si existe, no crea al nuevo usuario
                return Status.USER_ALREADY_EXISTS;
            }
        }
        userRepository.save(newUser);// si no existe, lo crea
        return Status.SUCCESS;
    }

    @PostMapping("/users/login")
    public Status loginUser(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);// si el usuario existe, permite el login
                // userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    @PostMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(false);// si el usuario existe, permite cerrar sesión
                //userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();// borrando todos los registros
        return Status.SUCCESS;
    }
}
