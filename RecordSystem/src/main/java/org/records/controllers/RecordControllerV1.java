package org.records.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("api/v1")
public class RecordControllerV1 {
//    @Autowired
//    RecordsManager recordsManager;

    @RequestMapping(value =  "/records", method = RequestMethod.GET)
    public List<Record> getAllRecords() {
        return null;
        // return recordsManager.getAllRecords();
    }

    @RequestMapping(value = "/books/{recordId}", method = RequestMethod.GET)
    public Optional<Record> getRecordById(@PathVariable(value = "recordId")UUID id) {
        //return recordsManager.getRecordById(id);
        return null;
    }
    @RequestMapping(value = "/records/{recordId}", method = RequestMethod.POST)
    public Record createBooks(@RequestBody Record record) {
        //return recordsManager.createRecordById(record);
        return null;
    }


}

