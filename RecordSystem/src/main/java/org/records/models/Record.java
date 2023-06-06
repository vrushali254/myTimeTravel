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

    @Column(name="versionNum")
    private long versionNum;

    @CreationTimestamp
    private Timestamp createdOn;
    @UpdateTimestamp
    private Timestamp lastUpdatedOn;

}
