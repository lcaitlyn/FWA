package edu.school21.cinema.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Log {
    private Long id;
    @NonNull
    private String email;
    @NonNull
    private LocalDateTime date;
    @NonNull
    private String ip;

}
