package com.backend.pf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfo {
    private String clientHeading,objectivesHeading,objectivesDetails;
    private List<CompanyInfo> companyInfos;
    private List<Technology> technologies;
    private List<ProjectDetails> projectDetailsList;
}
