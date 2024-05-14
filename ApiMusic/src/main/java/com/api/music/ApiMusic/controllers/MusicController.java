package com.api.music.ApiMusic.controllers;

import com.api.music.ApiMusic.dtos.MusicRecordDtos;
import com.api.music.ApiMusic.models.MusicModel;
import com.api.music.ApiMusic.repositories.MusicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MusicController {
    @Autowired
    MusicRepository musicRepository;

    @PostMapping("/add-new-music")
    public ResponseEntity<MusicModel> saveMusic(@RequestBody @Valid MusicRecordDtos musicRecordDtos){
        var musicModel = new MusicModel();
        BeanUtils.copyProperties(musicRecordDtos, musicModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(musicRepository.save(musicModel));
    }

    @GetMapping("/search-one-music/{id}")
    public ResponseEntity<Object> getOneMusic(@PathVariable(value = "id") UUID id){
        Optional<MusicModel> musicM = musicRepository.findById(id);
        if (musicM.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Music not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(musicM.get());
    }

    @GetMapping("/search-all-musics")
    public ResponseEntity<List<MusicModel>> getAllMusic(){
        List<MusicModel> musicList = musicRepository.findAll();
        if (!musicList.isEmpty()){
            for (MusicModel musicModel: musicList){
                UUID id = musicModel.getIdMusic();
                musicModel.add(linkTo(methodOn(MusicController.class).getOneMusic(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(musicList);
    }
}
