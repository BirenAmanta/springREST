package com.mindtree.intern.service;

import java.util.List;

import com.mindtree.intern.dto.MentorDTO;
import com.mindtree.intern.dto.ProjectDTO;
import com.mindtree.intern.exception.InternException;

public interface ProjectAllocationService {

	public ProjectDTO getDetails(Integer projectId)throws InternException;
	public Integer allocateProject(ProjectDTO project) throws InternException;
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InternException;
	public void updateProjectMentor (Integer projectId, Integer mentorId) throws InternException ;
	public void deleteProject(Integer projectId) throws InternException;
}
