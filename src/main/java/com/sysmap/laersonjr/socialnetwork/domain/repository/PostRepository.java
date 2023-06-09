package com.sysmap.laersonjr.socialnetwork.domain.repository;

import com.sysmap.laersonjr.socialnetwork.domain.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends MongoRepository<Post, UUID> {

    List<Post> findByUserId(UUID userId);
    List<Post> findByUserNickName(String nickName);



}
