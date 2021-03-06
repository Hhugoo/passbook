package com.imooc.merchants.dao;

import com.imooc.merchants.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Merchants Dao 接口
 */
@Repository
public interface MerchantsDao extends JpaRepository<Merchants, Integer>{

    Optional<Merchants> findById(Integer id);

    Merchants findByName(String name);


}
