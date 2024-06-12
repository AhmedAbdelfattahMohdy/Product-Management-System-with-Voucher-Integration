package com.ahmed.springrest.voucherapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmed.springrest.voucherapi.model.Voucher;

public interface VoucherRepo extends JpaRepository<Voucher, Long> {

    public Voucher findByCode(String code);

    public boolean existsByCode(String code);

}
