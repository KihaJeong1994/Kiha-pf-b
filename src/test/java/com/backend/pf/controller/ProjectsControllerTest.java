package com.backend.pf.controller;

import com.backend.pf.entity.Projects;
import com.backend.pf.service.ProjectsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(ProjectsController.class)
public class ProjectsControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProjectsService projectsService;

    @Test
    void getProjects(){
        when(projectsService.getProjects()).thenReturn(Flux.just(
                new Projects("1", "prj1", "Web", "/image.png")
                , new Projects("2", "prj2", "Mobile", "/image2.png")));
        webTestClient.get()
                .uri("/api/projects")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Projects[].class);
    }

    @Test
    void getProjectById(){
        when(projectsService.getProjectById("1")).thenReturn(
                Mono.just(new Projects("1", "prj1", "Web", "/image.png")));
        webTestClient.get()
                .uri("/api/projects/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Projects.class);
    }
}
