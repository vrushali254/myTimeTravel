package org.records.models;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.records.models.converters.JSONObjectConverter;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

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


    @Column(name="data", columnDefinition = "TEXT")
    @Convert(converter= JSONObjectConverter.class)
    private JSONObject data;

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
        return data.toString();
    }

    public void setData(String data) {
        try {
            this.data = new JSONObject(data);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
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
                ", data='" + data.toString() + '\'' +
                ", versionNum=" + versionNum +
                ", createdOn=" + createdOn +
                ", lastUpdatedOn=" + lastUpdatedOn +
                '}';
    }
}
