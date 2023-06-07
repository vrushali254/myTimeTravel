package org.records.controllers;

import org.records.models.Record;
import org.records.services.RecordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("api/")
public class RecordControllerV1 {
    @Autowired
    RecordManager recordManager = RecordManager.getInstance();

    @RequestMapping(value =  "v1/record", method = RequestMethod.GET)
    public List<Record> getAllRecords() {
        return recordManager.getAllRecords();
    }

    @RequestMapping(value = "v1/record/{recordId}", method = RequestMethod.GET)
    public Optional<Record> getRecordById(@PathVariable(value = "recordId", required = true)Integer id) {
        return recordManager.getLatestRecordById(id);
    }
    @RequestMapping(value = "v1/record/{recordId}", method = RequestMethod.POST)
    public Record createRecordById(@PathVariable(value="recordId", required = true) Integer id, @RequestBody String data) {
        Record record = new Record(id, data);
        return recordManager.createOrUpdateRecord(record);
    }

    @RequestMapping(value =  {"v2/record/{recordId}", "v2/record/{recordId}/{versionNum}"}, method = RequestMethod.GET)
    public Optional<Record> getRecordByVersionNum(@PathVariable(value = "recordId") Integer id, @PathVariable(value="versionNum", required = false) Optional<Integer> versionNum) {
        return recordManager.getRecordByVersionNum(id, versionNum.get());
    }

    @RequestMapping(value =  "v2/record/{recordId}/{versionNum}", method = RequestMethod.POST)
    public Optional<Record> updateRecordByVersionNum(@PathVariable(value = "recordId", required = true) Integer id, @PathVariable(value="versionNum", required = true) Integer versionNum, @RequestBody String data) {
        return recordManager.updateRecordByVersionNum(id, versionNum, data);
    }

    @RequestMapping(value="v2/records/{recordId}/history", method = RequestMethod.GET)
    public Set<Record> getRecordHistory(@PathVariable(value = "recordId", required = true) Integer id) {
        return recordManager.getRecordHistory(id);
   }

}

