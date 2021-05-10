package com.abcode.search.services;

import com.abcode.search.dto.GameDTO;
import com.abcode.search.entities.Game;
import com.abcode.search.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<GameDTO> findAll() {
        return gameRepository.findAll().stream().map(x -> new GameDTO(x)).collect(Collectors.toList());
    }
}
