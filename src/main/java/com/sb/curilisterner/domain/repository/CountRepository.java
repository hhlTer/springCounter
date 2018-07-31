package com.sb.curilisterner.domain.repository;

import com.sb.curilisterner.domain.resources.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountRepository extends JpaRepository<Counter, Long> {
//
    @Query(nativeQuery = true,
    value = "select rc from counter where id  =1")
    long getRCounter();
}
