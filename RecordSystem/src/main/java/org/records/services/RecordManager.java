package org.records.services;

import java.util.*;
import org.records.models.Record;
import org.records.models.RecordHistory;
import org.records.repositories.RecordHistoryRepository;
import org.records.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
@Service
public class RecordManager {

    private static RecordManager recordManager;

    // Singleton service
    public static RecordManager getInstance() {
        if(recordManager==null) {
            recordManager = new RecordManager();
        }
        return recordManager;
    }


    @Autowired
    RecordRepository recordRepository;

    @Autowired
    RecordHistoryRepository recordHistoryRepository;

    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    //GET Record by id
    public Optional<Record> getLatestRecordById(Integer id) {
        RecordHistory recordHistory = recordHistoryRepository.findById(id).get();
        return Optional.ofNullable(recordHistory.getRecordByVersion(recordHistory.getLatestVersion()));
    }

    public Optional<Record> getRecordByVersionNum(Integer id, Integer versionNum) {
        // Check if both the version and record exist
        RecordHistory recordHistory = recordHistoryRepository.findById(id).get();
        // Check if that versionNum exists
        if(recordHistory==null || !recordHistory.checkIfVersionExists(versionNum) ) {
            return null;
        }
        // Get the record using version
        return Optional.ofNullable(recordHistory.getRecordByVersion(versionNum));
    }

    //Create new record with recordId, versionNum=1
    public Record createRecordById(Record record) {
        // Save record
        Record savedRecord = recordRepository.save(record);
        // Initialize a new recordHistory for new record
        RecordHistory recordHistory = new RecordHistory(record.getRecordId());
        // Update
        recordHistory.addRecord(savedRecord);
        recordHistoryRepository.save(recordHistory);
        return savedRecord;
    }

    public Record updateRecord(Integer recordId, String jsonData) {
        RecordHistory recordHistory = recordHistoryRepository.findById(recordId).get();
        Integer latestVersion = recordHistory.getLatestVersion();
        Record latestRecord = recordHistory.getRecordByVersion(latestVersion);
        // update the data from new jsonData on old object
        Record recordToUpdate = new Record(recordId, latestRecord.getData().toString());
        recordToUpdate.updateData(jsonData);
        // update the version number
        recordToUpdate.setVersionNum(latestVersion+1);
        // Save updated new record entry
        Record updatedRecord = recordRepository.save(recordToUpdate);
        // update the latest version number in the record's history
        recordHistory.setLatestVersion(recordToUpdate.getVersionNum());
        // Add the updated record to the record history
        recordHistory.addRecord(updatedRecord);
        recordHistoryRepository.save(recordHistory);

        return updatedRecord;
    }

    // Creates or updates a record [will update the record's latest version]
    public Record createOrUpdateRecord(Integer id, String data) {
        if (recordRepository.findFirstByRecordId(id).isPresent()) {
            // update if exists, by creating new record entry and incrementing the versionNum
            return updateRecord(id, data);
        }
        // create new record
        return createRecordById(new Record(id, data));
    }

    // Creates or updates a record's specific version
    public Optional<Record> updateRecordByVersionNum(Integer recordId, Integer versionNum, String data) {
        //RecordId doesn't exist -> return null
        //RecordId exists, versionNum doesn't ->  return null
        //RecordId exists, versionNum exists -> update the record with specified versionNum

        // Get the record with record id and version
        Optional<Record> oldRecord = getRecordByVersionNum(recordId, versionNum);
        if (oldRecord.isEmpty()) {
            return null;
        }
        Record recordToUpdate = oldRecord.get();
        recordToUpdate.updateData(data);
        Record updatedRecord = recordRepository.save(recordToUpdate);

        //assumption: v2 apis with a versionNum, will modify the existing object without creating a new record version
        //else v1 api can be called with the modified payload to create an independent version

        return Optional.of(updatedRecord);
    }


    // Returns all versions for a record
    public Set<Record> getRecordHistory(Integer recordId) {
        RecordHistory recordHistory = recordHistoryRepository.findById(recordId).get();
        return recordHistory.getVersionedRecords();
    }

     public boolean isJsonValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }
}
