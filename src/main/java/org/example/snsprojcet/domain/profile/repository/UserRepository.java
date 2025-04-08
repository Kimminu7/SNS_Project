package org.example.snsprojcet.domain.profile.repository;

import org.example.snsprojcet.domain.profile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}