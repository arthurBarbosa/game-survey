package com.abcode.search.services;

import com.abcode.search.dto.RecordDTO;
import com.abcode.search.dto.RecordInsertDTO;
import com.abcode.search.entities.Game;
import com.abcode.search.entities.Record;
import com.abcode.search.repositories.GameRepository;
import com.abcode.search.repositories.RecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class RecordService {

    private final RecordRepository recordRepository;
    private final GameRepository gameRepository;

    public RecordService(RecordRepository recordRepository, GameRepository gameRepository) {
        this.recordRepository = recordRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional
    public RecordDTO insert(RecordInsertDTO dto){
        Record entity = new Record();

        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setMoment(Instant.now());

        Game game = gameRepository.getOne(dto.getGameId());
        entity.setGame(game);

        entity = recordRepository.save(entity);
        return new RecordDTO(entity);

    }

    @Transactional(readOnly = true)
    public Page<RecordDTO> findByMoments(Instant minDate, Instant maxDate, PageRequest pageRequest) {
        return recordRepository.findByMoments(minDate, maxDate, pageRequest).map(x -> new RecordDTO(x));
    }
}
