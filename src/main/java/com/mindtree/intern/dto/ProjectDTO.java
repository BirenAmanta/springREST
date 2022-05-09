package com.mindtree.intern.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ProjectDTO {
	private Integer projectId;
	@NotNull(message="roject.ideaowner.absent")
	private Integer ideaOwner;
	@NotNull(message="project.projectname.absent")
	private String projectName;
	@NotNull(message="project.releasedate.absent")
	private LocalDate releaseDate;
	@NotNull(message="project.mentor.absent")
	private MentorDTO  mentorDTO;
}
