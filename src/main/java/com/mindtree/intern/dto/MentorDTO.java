package com.mindtree.intern.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.mindtree.intern.entity.Mentor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MentorDTO {
	@NotNull(message = "mentor.mentorid.absent")
	@Pattern(regexp ="([0-9]{4})",message = "mentor.mentorid.invalid")
	private Integer mentorId;
	private String mentorName;
	private Integer numberOfProjectMentored;
	public Mentor getEntity()
	{
		Mentor data=new Mentor();
		data.setMentorId(this.mentorId);
		data.setMentorName(this.mentorName);
		data.setNumberOfProjectMentored(this.numberOfProjectMentored);
		return data;
	}
}
