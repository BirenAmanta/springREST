package com.mindtree.intern.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MentorDTO {
	@NotNull(message = "{mentor.mentorid.absent}")
	@Pattern(regexp ="([0-9]{4})",message = "{mentor.mentorid.invalid}")
	private String mentorId;
	private String mentorName;
	private Integer numberOfProjectMentored;
}
