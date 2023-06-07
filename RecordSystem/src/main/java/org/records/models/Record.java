package org.records.models;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="record")
public class Record {

    public Record() {
    }

    public Record(Integer recordId, String data) {
        this.setRecordId(recordId);
        this.setData(data);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="record_id", nullable = false)
    private Integer recordId;

    @Column(name="data")
    private String data;

    @Column(name="versionNum", nullable = false)
    private Integer versionNum = 1;

    @CreationTimestamp
    private Timestamp createdOn;
    @UpdateTimestamp
    private Timestamp lastUpdatedOn;

    public UUID getId() { return id; }


    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public Timestamp getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    @Override
    public String toString() {
        return "Record{" +
                "uuid=" + id +
                "record_id=" + recordId +
                ", data='" + data + '\'' +
                ", versionNum=" + versionNum +
                ", createdOn=" + createdOn +
                ", lastUpdatedOn=" + lastUpdatedOn +
                '}';
    }

    public void update(String data) {
        this.versionNum = this.versionNum + 1;
        this.data = data;
    }
}
