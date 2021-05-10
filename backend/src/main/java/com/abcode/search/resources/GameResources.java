package com.abcode.search.resources;

import com.abcode.search.dto.GameDTO;
import com.abcode.search.entities.Game;
import com.abcode.search.repositories.GameRepository;
import com.abcode.search.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gamers")
public class GameResources {

    private final GameService service;

    public GameResources(GameService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<GameDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
}
