package org.records.models;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name="record_history")
public class RecordHistory {

    public RecordHistory() {
    }

    public RecordHistory(Integer id) {
        this.setId(id);
        this.latestVersion = 1;
        this.setVersionedRecords(new HashSet<>());
    }
    @Id
    @Column(name="record_id")
    private Integer id;

    @Column(name="latest_version")
    private Integer latestVersion;



    @Column(name="versioned_records")
    @ElementCollection
    private Set<Record> versionedRecords;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVersionedRecords(Set<Record> versionedRecords) {
        this.versionedRecords = versionedRecords;
    }
    public void addRecord(Record record) {
        Integer versionNum = record.getVersionNum();
        this.versionedRecords.add(record);
    }

    public Set<Record> getVersionedRecords() {
        return this.versionedRecords;
    }

    public Record getRecordByVersion(Integer versionNum) {
        for(Record r:this.versionedRecords) {
            if(r.getVersionNum().equals(versionNum)) return r;
        }
        return null;
    }

    public boolean checkIfVersionExists(Integer versionNum) {
        for(Record r:this.versionedRecords) {
            if(r.getVersionNum().equals(versionNum)) return true;
        }
        return false;
    }

    public Integer getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(Integer latestVersion) {
        this.latestVersion = latestVersion;
    }
}
