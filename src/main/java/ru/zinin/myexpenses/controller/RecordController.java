package ru.zinin.myexpenses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zinin.myexpenses.dto.RecordDto;
import ru.zinin.myexpenses.dto.RecordForChart;
import ru.zinin.myexpenses.exception.InvalidToken;
import ru.zinin.myexpenses.exception.RecordNotFound;
import ru.zinin.myexpenses.model.Record;
import ru.zinin.myexpenses.service.RecordService;

import java.util.List;

@RestController
public class RecordController {

    @Autowired
    RecordService recordService;

    @CrossOrigin(methods = RequestMethod.POST)
    @PostMapping("/api/record")
    public void createRecord(@RequestBody RecordDto recordDto) throws InvalidToken {
        recordService.createRecord(recordDto);
    }

    @CrossOrigin(methods = RequestMethod.GET)
    @GetMapping("/api/record")
    public ResponseEntity<List<RecordForChart>> getRecords() throws InvalidToken {
        return recordService.getRecords();
    }

    @CrossOrigin(methods = RequestMethod.GET)
    @GetMapping("/api/rec")
    public ResponseEntity<RecordForChart> getRecord(@RequestParam Long id) throws InvalidToken {
        return recordService.getRecord(id);
    }

    @CrossOrigin(methods = RequestMethod.DELETE)
    @DeleteMapping("/api/record/{id}")
    public void deleteRecord(@PathVariable Long id) throws RecordNotFound, InvalidToken {
        recordService.deleteRecord(id);
    }
}
