package com.thatguysapp.quickpoll.repository;

import com.thatguysapp.quickpoll.domain.POption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<POption, Long> {
}
