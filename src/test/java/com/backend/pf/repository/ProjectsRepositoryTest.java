package com.backend.pf.repository;

import com.backend.pf.entity.Projects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
//@ExtendWith(SpringExtension.class)
public class ProjectsRepositoryTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ProjectsRepository projectsRepository;

    @AfterEach
    void cleanUp(){
        this.projectsRepository.deleteAll();
    }

    @Test
    void findAllProjectsTest(){
        Flux<Projects> projects = Flux.just(
                                    new Projects(null,"prj1","Web","/image.png")
                                    ,new Projects(null,"prj2","Mobile","/image2.png"))
                                .flatMap(p->projectsRepository.save(p))
                                .thenMany(projectsRepository.findAll());
        StepVerifier.create(projects)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void findByIdProjectsTest(){
        Mono<Projects> project1 = Flux.just(
                                    new Projects("1", "prj1", "Web", "/image.png")
                                    , new Projects("2", "prj2", "Mobile", "/image2.png"))
                                .flatMap(p -> projectsRepository.save(p))
                                .then(projectsRepository.findById("1"));
        StepVerifier.create(project1)
                .consumeNextWith(p->{
                    assertNotNull(p.getId());
                    assertEquals(p.getTitle(),"prj1");
                    assertEquals(p.getCategory(),"Web");
                    assertEquals(p.getImg(),"/image.png");
                })
                .verifyComplete();
    }
}
