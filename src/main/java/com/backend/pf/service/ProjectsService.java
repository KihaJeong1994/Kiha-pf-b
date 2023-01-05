package com.backend.pf.service;

import com.backend.pf.entity.Projects;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectsService {
    Flux<Projects> getProjects();
    Mono<Projects> getProjectById(String id);
}
