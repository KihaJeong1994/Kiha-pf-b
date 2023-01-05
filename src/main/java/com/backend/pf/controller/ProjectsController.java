package com.backend.pf.controller;

import com.backend.pf.entity.Projects;
import com.backend.pf.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProjectsController {

    private final ProjectsService projectsService;

    @GetMapping("/api/projects")
    public Flux<Projects> getProjects(){
        return projectsService.getProjects();
    }

    @GetMapping("/api/projects/{id}")
    public Mono<Projects> getProjectById(@PathVariable String id){
        return projectsService.getProjectById(id);
    }
}
