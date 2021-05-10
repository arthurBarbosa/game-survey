package com.abcode.search.resources;

import com.abcode.search.dto.GameDTO;
import com.abcode.search.dto.RecordDTO;
import com.abcode.search.dto.RecordInsertDTO;
import com.abcode.search.services.RecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordResources {

    private final RecordService recordService;

    public RecordResources(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public ResponseEntity<RecordDTO> insert(@RequestBody RecordInsertDTO dto) {
        return ResponseEntity.ok().body(recordService.insert(dto));
    }

    @GetMapping
    public ResponseEntity<Page<RecordDTO>> findAll(@RequestParam(value = "min", defaultValue = "") String min,
                                                   @RequestParam(value = "max", defaultValue = "") String max,
                                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                                   @RequestParam(value = "orderBy", defaultValue = "moment") String orderBy,
                                                   @RequestParam(value = "direction", defaultValue = "DESC") String direction) {

        Instant minDate = ("".equals(min)) ? null : Instant.parse(min);
        Instant maxDate = ("".equals(max)) ? null : Instant.parse(max);

        if(linesPerPage == 0){
            linesPerPage = Integer.MAX_VALUE;
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return ResponseEntity.ok().body(recordService.findByMoments(minDate, maxDate, pageRequest));
    }
}
