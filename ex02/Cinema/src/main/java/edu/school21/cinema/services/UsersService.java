package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

import java.util.Optional;

public interface UsersService {
    void signUp(String firstName, String secondName, String email, String password);
    boolean signIn(String email, String password);
}
