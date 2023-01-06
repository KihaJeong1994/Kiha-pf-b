package com.backend.pf.controller;

import com.backend.pf.entity.Projects;
import com.backend.pf.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectsController {

    private final ProjectsService projectsService;

    @GetMapping("")
    public Flux<Projects> getProjects(){
        return projectsService.getProjects();
    }

    @GetMapping("/{id}")
    public Mono<Projects> getProjectById(@PathVariable String id){
        return projectsService.getProjectById(id);
    }
}
