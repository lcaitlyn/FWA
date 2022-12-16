package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

public interface UsersService {
    void signUp(String firstName, String secondName, String email, String password);
    User signIn(String email, String password);
}
