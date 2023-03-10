package edu.school21.cinema.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String secondName;
    @NonNull
    private String email;
    @NonNull
    private String password;
}
