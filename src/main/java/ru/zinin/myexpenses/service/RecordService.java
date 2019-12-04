package ru.zinin.myexpenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import ru.zinin.myexpenses.component.TokenHolder;
import ru.zinin.myexpenses.dto.RecordDto;
import ru.zinin.myexpenses.dto.RecordForChart;
import ru.zinin.myexpenses.exception.InvalidToken;
import ru.zinin.myexpenses.exception.RecordNotFound;
import ru.zinin.myexpenses.model.Category;
import ru.zinin.myexpenses.model.Record;
import ru.zinin.myexpenses.model.User;
import ru.zinin.myexpenses.repo.CategoryRepo;
import ru.zinin.myexpenses.repo.RecordRepo;
import ru.zinin.myexpenses.repo.UserRepo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void deleteRecord(Long id) throws InvalidToken, RecordNotFound {
        if (!tokenHolder.isValidToken()) {
            throw new InvalidToken();
        }
        tokenHolder.updateTimeValidityToken();
//        System.out.println(id);
//        User user = userRepo.getUserById(tokenHolder.getIdUser());
//
//        user.getCategories()
//                .forEach(cat-> {
//                    Iterator<Record> iterator = cat.getRecords().iterator();
//                    while (iterator.hasNext()) {
//                        Record record = iterator.next();
//                        if (record.getId() == id) {
//                            iterator.remove();
//                        }
//                    }
//                });

//        userRepo.save(user);

//        Category category = recordRepo.getRecordById(id).getCategory();
//        System.out.println(category.getRecords().size());
//        Iterator<Record> iterator = category.getRecords().iterator();
//        while (iterator.hasNext()) {
//            Record record = iterator.next();
//            if (record.getId() == id) {
//                iterator.remove();
//            }
//        }
//        System.out.println(category.getRecords().size());
//        categoryRepo.save(category);
        recordRepo.deleteById(id);
    }
}
