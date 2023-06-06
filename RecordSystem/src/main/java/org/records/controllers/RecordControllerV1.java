package org.records.controllers;

import org.records.models.Record;
import org.records.services.RecordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1")
public class RecordControllerV1 {
    @Autowired
    RecordManager recordManager;

    @RequestMapping(value =  "/record", method = RequestMethod.GET)
    public List<Record> getAllRecords() {
        return recordManager.getAllRecords();
    }

    @RequestMapping(value = "/record/{recordId}", method = RequestMethod.GET)
    public Optional<Record> getRecordById(@PathVariable(value = "recordId", required = true)Integer id) {
        return recordManager.getRecordById(id);
    }
    @RequestMapping(value = "/record/{recordId}", method = RequestMethod.POST)
    public Record createRecordById(@PathVariable(value="recordId", required = true) Integer id, @RequestBody String data) {
        Record record = new Record(id, data);
        return recordManager.createOrUpdateRecord(record);
    }

}

