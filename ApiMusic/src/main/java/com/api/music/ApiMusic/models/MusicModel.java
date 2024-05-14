package com.api.music.ApiMusic.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TABLE_MUSIC")
public class MusicModel extends RepresentationModel<MusicModel> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMusic;
    private String nameMusic;
    private String artistMusic;
    private String styleMusic;
    @Max(value = 2025)
    private int yearMusic;

    public UUID getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(UUID idMusic) {
        this.idMusic = idMusic;
    }

    public String getNameMusic() {
        return nameMusic;
    }

    public void setNameMusic(String nameMusic) {
        this.nameMusic = nameMusic;
    }

    public String getArtistMusic() {
        return artistMusic;
    }

    public void setArtistMusic(String artistMusic) {
        this.artistMusic = artistMusic;
    }

    public String getStyleMusic() {
        return styleMusic;
    }

    public void setStyleMusic(String styleMusic) {
        this.styleMusic = styleMusic;
    }

    public int getYearMusic() {
        return yearMusic;
    }

    public void setYearMusic(int yearMusic) {
        this.yearMusic = yearMusic;
    }
}
