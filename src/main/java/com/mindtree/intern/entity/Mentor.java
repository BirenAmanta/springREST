package com.mindtree.intern.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity

public class Mentor {
	@Id
	private Integer mentorId;
	private String mentorName;
	@Column(name = "projects_mentored")
	private Integer numberOfProjectMentored;
	

}
