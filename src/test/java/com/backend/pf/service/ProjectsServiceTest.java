package com.backend.pf.service;

import com.backend.pf.entity.Projects;
import com.backend.pf.repository.ProjectsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class ProjectsServiceTest {

    @InjectMocks
    private ProjectsServiceImpl projectsService; // InjectMocks cannot be interface

    @Mock
    private ProjectsRepository projectsRepository;

    @Test
    void getProjectsTest(){
        when(projectsRepository.findAll()).thenReturn(Flux.just(
                new Projects("1", "prj1", "Web", "/image.png")
                , new Projects("2", "prj2", "Mobile", "/image2.png")));
        Flux<Projects> projects = projectsService.getProjects();
        StepVerifier.create(projects)
                    .expectNextCount(2)
                    .verifyComplete();
    }

    @Test
    void getProjectByIdTest(){
        when(projectsRepository.findById("1")).thenReturn(Mono.just(new Projects("1", "prj1", "Web", "/image.png")));
        Mono<Projects> project = projectsService.getProjectById("1");
        StepVerifier.create(project)
                .consumeNextWith(p->{
                    assertNotNull(p.getId());
                    assertEquals(p.getTitle(),"prj1");
                    assertEquals(p.getCategory(),"Web");
                    assertEquals(p.getImg(),"/image.png");
                })
                .verifyComplete();
    }
}
