package pro.sachin.ms1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pro.sachin.ms1.model.Coupon;
import pro.sachin.ms1.repository.CouponRepository;

@RestController
@RequiredArgsConstructor
public class CouponController {

    private final CouponRepository couponRepository;

    @GetMapping("/coupons/{code}")
//    this is method level securiry so after configuring the WebSecurityConfig there are two
//    security mechanisms, pre and post
    @PreAuthorize("hasRole('ADMIN')")
    public Coupon getCoupon(@PathVariable("code") String code) {
        return couponRepository.findByCode(code);
    }

    @PostMapping("/coupons/")
    public String addCoupon(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon).getCode();
    }
}
