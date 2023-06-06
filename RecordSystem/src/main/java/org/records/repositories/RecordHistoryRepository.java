package org.records.repositories;

import org.records.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordHistoryRepository extends JpaRepository<RecordHistory, Integer> {
}
