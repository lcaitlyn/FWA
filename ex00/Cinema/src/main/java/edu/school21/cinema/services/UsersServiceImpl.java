package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
    public User signIn(String email, String password) {
        User user = usersRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword()))
            return user;
//
        return null;
    }
}
