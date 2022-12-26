package edu.school21.cinema.models;

import lombok.*;
import org.apache.commons.fileupload.FileItem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Image {
    private Long id;
    @NonNull
    private Long userID;
    @NonNull
    private String name;
    @NonNull
    private String MIME;
    @NonNull
    private String size;

    public Image(User user, FileItem fileItem) {
        userID = user.getId();
        name = fileItem.getName();
        MIME = fileItem.getContentType();
        size = getReadableSize(fileItem.getSize());
    }

    public String getFileUniquePath() {
        return "/" + getUserID() + "/userid=" + getUserID() + "imageid=" + getId() + "name=" + getName();
    }

    // немного неверно рассчитывает
    @Deprecated
    private String getReadableSize(Long size) {
        String [] measures = {"B", "KB", "MB", "GB"};

        int i = 0;
        long tmp = size;
        while (tmp / 1024 >= 1) {
            i++;
            tmp /= 1024;
        }

        tmp = tmp + tmp % 1024;

        return tmp + measures[i];
    }
}
