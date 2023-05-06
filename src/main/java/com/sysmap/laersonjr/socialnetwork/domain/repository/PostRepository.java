package com.sysmap.laersonjr.socialnetwork.domain.repository;

import com.sysmap.laersonjr.socialnetwork.domain.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PostRepository extends MongoRepository<Post, UUID> {
}
