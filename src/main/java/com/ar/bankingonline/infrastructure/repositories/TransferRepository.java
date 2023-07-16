package com.ar.bankingonline.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ar.bankingonline.domain.models.Transfer;
@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

}
