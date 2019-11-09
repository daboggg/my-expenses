package ru.zinin.myexpenses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zinin.myexpenses.dto.RecordDto;
import ru.zinin.myexpenses.exception.InvalidToken;
import ru.zinin.myexpenses.service.RecordService;

@RestController
public class RecordController {

    @Autowired
    RecordService recordService;

    @CrossOrigin(methods = RequestMethod.POST)
    @PostMapping("/api/record")
    public void createRecord(@RequestBody RecordDto recordDto) throws InvalidToken {
        recordService.createRecord(recordDto);
    }
}
