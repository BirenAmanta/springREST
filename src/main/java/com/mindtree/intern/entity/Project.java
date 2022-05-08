package com.mindtree.intern.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.mindtree.intern.dto.ProjectDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;
	private String projectName;
	private Integer ideaOwner;
	private LocalDate releaseDate;
	@ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name="mentor_id")
	private Mentor  mentor;
	public ProjectDTO getDTO()
	{
		ProjectDTO data=new ProjectDTO();
		data.setProjectId(this.projectId);
		data.setProjectName(this.projectName);
		data.setIdeaOwner(this.ideaOwner);
		data.setReleaseDate(this.releaseDate);
		data.setMentorDTO(this.mentor.getDTO());
		return data;
	}
}
