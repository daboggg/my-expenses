package ru.zinin.myexpenses.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zinin.myexpenses.model.Record;

public interface RecordRepo extends JpaRepository<Record, Long> {
    Record getRecordById(Long id);

    void removeById(Long id);

    boolean existsRecordById(Long id);

    Record getById(Long id);

    void removeAllById(Long id);

    void deleteAllById(Long id);
}
