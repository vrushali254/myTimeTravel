package org.records.models;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name="record")
public class Record {
    @Id
    @Column(name="record_id", nullable = false, unique = true)
    private Integer id;

    @Column(name="data")
    private String data;

    @Column(name="versionNum", nullable = false)
    private long versionNum = 1;

    @CreationTimestamp
    private Timestamp createdOn;
    @UpdateTimestamp
    private Timestamp lastUpdatedOn;

    public Record() {
    }

    public Record(Integer id, String data) {
        this.setId(id);
        this.setData(data);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(long versionNum) {
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
                "id=" + id +
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
