package com.ahmed.springrest.voucherapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmed.springrest.voucherapi.model.Voucher;
import com.ahmed.springrest.voucherapi.repository.VoucherRepo;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/voucherapi")
public class VoucherRestController {

    @Autowired
    VoucherRepo repo;

    @PostMapping("/vouchers")
    @Transactional
    public Voucher createVoucher(@RequestBody Voucher voucher) {
        if (repo.existsByCode(voucher.getCode())) {
            throw new IllegalArgumentException("Voucher code already exists");
        }
        return repo.save(voucher);
    }

    // @PostMapping("/vouchers")
    // @Transactional
    // public Voucher create(@RequestBody Voucher voucher) {
    // return repo.save(voucher);
    // }

    @GetMapping("/vouchers/{code}")
    public Voucher getVoucher(@PathVariable("code") String code) {
        return repo.findByCode(code);
    }

}
