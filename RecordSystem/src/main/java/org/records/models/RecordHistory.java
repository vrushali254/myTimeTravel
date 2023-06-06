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
        this.setVersionedRecords(new HashMap<>());
    }
    @Id
    @Column(name="record_id")
    private Integer id;

    @Column(name="latest_version")
    private Integer latestVersion;

    @Column(name="versioned_records")
    private Map<Integer, Record> versionedRecords;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Integer, Record> getVersionedRecords() {
        return versionedRecords;
    }

    public void setVersionedRecords(Map<Integer, Record> versionedRecords) {
        this.versionedRecords = versionedRecords;
    }

    public Integer getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(Integer latestVersion) {
        this.latestVersion = latestVersion;
    }
}
