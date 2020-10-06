package com.sorry.buskingbusking.repository;

import com.sorry.buskingbusking.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
