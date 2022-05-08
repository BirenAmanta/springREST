package com.mindtree.intern.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.mindtree.intern.dto.MentorDTO;

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
	
	public MentorDTO getDTO()
	{
		MentorDTO data=new MentorDTO();
		data.setMentorId(this.mentorId);
		data.setMentorName(this.mentorName);
		data.setNumberOfProjectMentored(this.numberOfProjectMentored);
		return data;
	}

}
