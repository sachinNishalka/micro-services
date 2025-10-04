package pro.sachin.ms1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.sachin.ms1.model.Coupon;
import pro.sachin.ms1.repository.CouponRepository;

@RestController
@RequiredArgsConstructor
public class CouponController {

    private final CouponRepository couponRepository;

    @GetMapping("/coupon/{code}")
    public Coupon getCoupon(@PathVariable("code") String code) {
        return couponRepository.findByCode(code);
    }

    @PostMapping("/coupons")
    public String addCoupon(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon).getCode();
    }
}
