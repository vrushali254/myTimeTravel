package org.records.models;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name="recordHistory")
public class RecordHistory {

    public RecordHistory() {
    }

    public RecordHistory(Integer id) {
        this.setId(id);
        this.setOlderRecords(new ArrayList<>());
    }
    @Id
    @Column(name="record_id")
    private Integer id;

    @Column(name="older_records")
    private List<Record> olderRecords;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Record> getOlderRecords() {
        return olderRecords;
    }

    public void setOlderRecords(List<Record> olderRecords) {
        this.olderRecords = olderRecords;
    }
}
