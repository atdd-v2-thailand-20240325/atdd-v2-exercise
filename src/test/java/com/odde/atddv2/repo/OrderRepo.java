package com.odde.atddv2.repo;

import com.odde.atddv2.entity.Order;
import org.springframework.data.repository.Repository;

public interface OrderRepo extends Repository<Order, Long> {

    void deleteAll();

}
