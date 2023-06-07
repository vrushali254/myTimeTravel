package org.records.repositories;

import org.records.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    public Optional<Record> findFirstByRecordId(Integer recordId);
    public Optional<Record> findByVersionNum(Integer versionNum);
}
