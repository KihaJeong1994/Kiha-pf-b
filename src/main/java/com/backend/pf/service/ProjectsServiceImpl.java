package com.backend.pf.service;

import com.backend.pf.entity.Projects;
import com.backend.pf.repository.ProjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProjectsServiceImpl implements ProjectsService{

    private final ProjectsRepository projectsRepository;
    @Override
    public Flux<Projects> getProjects() {
        return projectsRepository.findAll();
    }

    @Override
    public Mono<Projects> getProjectById(String id) {
        return projectsRepository.findById(id);
    }
}
