package com.backend.pf.entity;

import com.backend.pf.dto.ProjectDetails;
import com.backend.pf.dto.ProjectImage;
import com.backend.pf.dto.ProjectInfo;
import com.backend.pf.dto.SingleProjectHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projects {

    @Id
    private String id;

    private String title;

    private String category;

    private String img;

    private SingleProjectHeader singleProjectHeader;

    private List<ProjectImage> projectImages;

    private ProjectInfo projectInfo;


    public Projects(String id, String title, String category, String img){
        this.id = id;
        this.title = title;
        this.category =category;
        this.img = img;
    }
}
