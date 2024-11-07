package com.ldodev.crud.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String imgUrl;

    @NotBlank
    private String imgId;

    public Image(String name, String imgUrl, String imgId) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.imgId = imgId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(@NotBlank String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public @NotBlank String getImgId() {
        return imgId;
    }

    public void setImgId(@NotBlank String imgId) {
        this.imgId = imgId;
    }


}
