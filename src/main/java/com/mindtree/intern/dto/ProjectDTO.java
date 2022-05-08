package com.mindtree.intern.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.mindtree.intern.entity.Project;

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
	public Project getEntity()
	{
		Project data=new Project();
		data.setProjectId(this.projectId);
		data.setProjectName(this.projectName);
		data.setIdeaOwner(this.ideaOwner);
		data.setReleaseDate(this.releaseDate);
		data.setMentor(this.mentorDTO.getEntity());
		return data;
	}
}
