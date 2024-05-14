package com.api.music.ApiMusic.repositories;

import com.api.music.ApiMusic.models.MusicModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// ATIVAR BUSCA POR NOME
public interface MusicRepository extends JpaRepository<MusicModel, UUID> {
}
