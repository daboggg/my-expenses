package ru.zinin.myexpenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.zinin.myexpenses.component.TokenHolder;
import ru.zinin.myexpenses.dto.RecordDto;
import ru.zinin.myexpenses.exception.InvalidToken;
import ru.zinin.myexpenses.model.Category;
import ru.zinin.myexpenses.model.Record;
import ru.zinin.myexpenses.repo.CategoryRepo;

@Service
public class RecordService {

    @Autowired
    TokenHolder tokenHolder;

    @Autowired
    CategoryRepo categoryRepo;

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
}
