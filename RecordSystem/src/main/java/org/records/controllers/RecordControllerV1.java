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
    RecordManager recordManager;

    @RequestMapping(value =  "v1/record", method = RequestMethod.GET)
    public List<Record> getAllRecords() {
        return recordManager.getAllRecords();
    }

    @RequestMapping(value = "v1/record/{recordId}", method = RequestMethod.GET)
    public Optional<Record> getRecordById(@PathVariable(value = "recordId", required = true)Integer id) {
        return recordManager.getRecordById(id);
    }
    @RequestMapping(value = "v1/record/{recordId}", method = RequestMethod.POST)
    public Record createRecordById(@PathVariable(value="recordId", required = true) Integer id, @RequestBody String data) {
        Record record = new Record(id, data);
        return recordManager.createOrUpdateRecord(record);
    }


    @RequestMapping(value =  {"v2/record/{recordId}", "v2/record/{recordId}/{versionNum}"}, method = RequestMethod.GET)
    public Optional<Record> getRecordByVersionNum(@PathVariable(value = "recordId") Integer id, @PathVariable(value="versionNum", required = false) Optional<Integer> versionNum) {
//        Integer id = pathVariables.get("recordId");
//        Integer versionNum = pathVariables.get("versionNum");
        //Get latest if no versionNum is specified
        if(versionNum.isEmpty()) {
            return recordManager.getRecordById(id);
        }
        return recordManager.getRecordByVersionNum(id, versionNum.get());
    }

    @RequestMapping(value =  "v2/record/{recordId}/{versionNum}", method = RequestMethod.POST)
    public Optional<Record> createOrUpdateRecordByVersionNum(@PathVariable(value = "recordId") Integer id, @PathVariable(value="versionNum", required = true) Integer versionNum) {
        return recordManager.createOrUpdateRecordByVersionNum(id, versionNum);
    }

   // v2/records/{recordId}/history?startTime && endTime → GET
    @RequestMapping(value="v2/records/{recordId}/history", method = RequestMethod.GET)
    public List<Record> getRecordHistory(@PathVariable(value = "recordId", required = true) Integer id) {
        return recordManager.getRecordHistory(id);
   }

}

