package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Photo {

    private int id;
    private String user_id;
    private String path;
    private String alt;

    public Photo(String user_id, String path, String alt) {
        this.user_id = user_id;
        this.path = path;
        this.alt = alt;
    }
}

