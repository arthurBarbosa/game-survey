package com.abcode.search.resources;

import com.abcode.search.dto.RecordDTO;
import com.abcode.search.dto.RecordInsertDTO;
import com.abcode.search.services.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
