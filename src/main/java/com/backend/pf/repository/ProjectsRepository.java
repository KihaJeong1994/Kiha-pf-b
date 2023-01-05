package com.backend.pf.repository;

import com.backend.pf.entity.Projects;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends ReactiveMongoRepository<Projects, String> {
}
