package com.wavods.anystore.repositories;


import com.wavods.anystore.gateways.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLoginIgnoreCase(final String login);

    Boolean existsByLogin(final String login);

    Boolean existsByEmail(final String email);

    Optional<UserEntity> findByEmail(final String email);

    @Query(value = """
             SELECT * FROM "user" u WHERE :status IS NULL OR u.active = :status
            """, nativeQuery = true)
    Page<UserEntity> findAllByActive(Boolean status, final Pageable pageable);

}
