package ru.zinin.myexpenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.zinin.myexpenses.component.TokenHolder;
import ru.zinin.myexpenses.dto.RecordDto;
import ru.zinin.myexpenses.dto.RecordForChart;
import ru.zinin.myexpenses.exception.InvalidToken;
import ru.zinin.myexpenses.model.Category;
import ru.zinin.myexpenses.model.Record;
import ru.zinin.myexpenses.model.User;
import ru.zinin.myexpenses.repo.CategoryRepo;
import ru.zinin.myexpenses.repo.RecordRepo;
import ru.zinin.myexpenses.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    TokenHolder tokenHolder;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RecordRepo recordRepo;

    public void createRecord(RecordDto recordDto) throws InvalidToken {
        if (!tokenHolder.isValidToken()) {
            throw new InvalidToken();
        }
        tokenHolder.updateTimeValidityToken();
        Category byIdCategory = categoryRepo.getById(recordDto.getCategoryId());
        Record record = RecordDto.getRecord(recordDto);
        record.setCategory(byIdCategory);
        byIdCategory.getRecords().add(record);
        categoryRepo.save(byIdCategory);
    }

    public ResponseEntity<List<RecordForChart>> getRecords() throws InvalidToken {
        if (!tokenHolder.isValidToken()) {
            throw new InvalidToken();
        }
        tokenHolder.updateTimeValidityToken();

        User userById = userRepo.getUserById(tokenHolder.getIdUser());
        List<Record> records = new ArrayList<>();
        userById.getCategories()
                .forEach(cat-> cat.getRecords()
                        .forEach(rec-> records.add(rec)));

        return ResponseEntity.ok(RecordForChart.getListRecordForChart(records));
    }

    public ResponseEntity<RecordForChart> getRecord(Long id) throws InvalidToken {
        if (!tokenHolder.isValidToken()) {
            throw new InvalidToken();
        }
        tokenHolder.updateTimeValidityToken();

        return ResponseEntity.ok(RecordForChart.getRecordForChart(recordRepo.getRecordById(id)));
    }
}
