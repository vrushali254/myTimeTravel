package org.records.services;

import java.util.*;
import org.records.models.Record;
import org.records.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RecordManager {
    public Optional<Record> getRecordByVersionNum(Integer id, Integer versionNum) {
        // Check if both the version and record exist
        return null;
    }
    @Autowired
    RecordRepository recordRepository;

    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    //GET Record by id
    public Optional<Record> getRecordById(Integer id) {
        return recordRepository.findById(id=id);
    }


    //Create/Update Record
    public Record createRecordById(Record record) {
        return recordRepository.save(record);
    }

    public Record updateRecord(Record record) {
        Record record_to_update = recordRepository.findById(record.getId()).get();
        record_to_update.update(record.getData());
        return recordRepository.save(record_to_update);
    }

    // Creates or updates a record [will update the record's latest version]
    public Record createOrUpdateRecord(Record record) {
        if (getRecordById(record.getId()).isPresent()) {
            // update if exists
            return updateRecord(record);
        }
        // create new record
        return createRecordById(record);
    }

    // Creates or updates a record's specific version
    public Optional<Record> createOrUpdateRecordByVersionNum(Integer id, Integer versionNum) {
        return null;
    }


    // Returns all versions for a record
    public List<Record> getRecordHistory(Integer id) {
        return null;
    }
}
