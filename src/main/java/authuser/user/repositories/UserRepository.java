package authuser.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import authuser.user.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
