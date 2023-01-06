package com.backend.pf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetails {
    @Field("id")
    private String id;
    private String projectDetailsHeading;
    private List<ProjectDetail> projectDetails;
}
