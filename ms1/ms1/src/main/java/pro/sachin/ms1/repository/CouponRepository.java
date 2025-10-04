package pro.sachin.ms1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sachin.ms1.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {

    Coupon findByCode(String code);

}
