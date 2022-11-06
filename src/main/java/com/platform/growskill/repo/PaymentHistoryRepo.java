package com.platform.growskill.repo;

import com.platform.growskill.entity.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory,Long> {

//    @Query("")
//    public Optional<PaymentHistory> getLatestPayment(Long studentId, Long courseId);
}
