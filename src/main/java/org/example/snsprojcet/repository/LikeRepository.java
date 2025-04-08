package org.example.snsprojcet.repository;

import org.example.snsprojcet.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Integer> {


}
