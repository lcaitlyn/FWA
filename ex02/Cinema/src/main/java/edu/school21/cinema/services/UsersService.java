package edu.school21.cinema.services;

public interface UsersService {
    void signUp(String firstName, String secondName, String email, String password);
    boolean signIn(String email, String password);
}
