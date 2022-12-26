package edu.school21.cinema.models;

import lombok.*;
import org.apache.commons.fileupload.FileItem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Image {
    private Long ID;
    @NonNull
    private Long UserID;
    @NonNull
    private String Name;
    @NonNull
    private String MIME;

    public Image(User user, FileItem fileItem) {
        UserID = user.getId();
        Name = fileItem.getName();
        MIME = fileItem.getContentType();
    }
}
