package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.LogsRepository;
import edu.school21.cinema.repositories.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService{
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(String firstName, String secondName, String email, String password) {
        usersRepository.save(
                new User(firstName, secondName, email, passwordEncoder.encode(password))
        );
    }

    @Override
    public boolean signIn(String email, String password) {
        Optional<User> user = usersRepository.findByEmail(email);

        return (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword()));
    }
}
