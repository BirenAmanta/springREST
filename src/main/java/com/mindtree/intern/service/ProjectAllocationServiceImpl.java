package com.mindtree.intern.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.intern.dto.MentorDTO;
import com.mindtree.intern.dto.ProjectDTO;
import com.mindtree.intern.entity.Mentor;
import com.mindtree.intern.entity.Project;
import com.mindtree.intern.exception.InternException;
import com.mindtree.intern.repository.MentorRepository;
import com.mindtree.intern.repository.ProjectRepository;

@Service
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService {

	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private MentorRepository mentorRepo;
	
	@Override
	public Integer allocateProject(ProjectDTO project)throws InternException {
		Optional<Mentor> data=mentorRepo.findById(project.getMentorDTO().getMentorId());
		if(data.isPresent())
		{
			if(data.get().getNumberOfProjectMentored()>=3)
			{
				throw new InternException("Service.CANNOT_ALLOCATE_PROJECT");
			}
			else
			{
				Project projectSave=projectRepo.save(project.getEntity());
				projectSave.getMentor().setNumberOfProjectMentored(projectSave.getMentor().getNumberOfProjectMentored()+1);
				return projectSave.getProjectId();
			}
		}
		else
		{
			throw new InternException("Service.MENTOR_NOT_FOUND");
		}
		
	}

	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InternException {
		List<Mentor> retrivedData=mentorRepo.findByNumberOfProjectMentored(numberOfProjectsMentored);
		if(retrivedData.isEmpty())
		{
			throw new InternException("Service.MENTOR_NOT_FOUND");
		}
		return retrivedData.stream().map((data)-> data.getDTO()).collect(Collectors.toList());
	}

	@Override
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws InternException{
		Optional<Mentor>mentorData=mentorRepo.findById(mentorId);
		Mentor mentorDetails=mentorData.orElseThrow(()->new InternException("Service.MENTOR_NOT_FOUND"));
		Optional<Project>projectData=projectRepo.findById(projectId);
		Project projectDetails=projectData.orElseThrow(()->new InternException("Service.PROJECT_NOT_FOUND"));
		projectDetails.setMentor(mentorDetails);

	}

	@Override
	public void deleteProject(Integer projectId) throws InternException{
		Optional<Project>projectData=projectRepo.findById(projectId);
		Project projectDetails=projectData.orElseThrow(()->new InternException("Service.PROJECT_NOT_FOUND"));
		projectDetails.setMentor(null);
		projectRepo.delete(projectDetails);
	}

	@Override
	public ProjectDTO getDetails(Integer projectId) throws InternException {
		Optional<Project>data=projectRepo.findById(projectId);
		Project details=data.orElseThrow(()->new InternException("Service.PROJECT_NOT_FOUND"));
		ProjectDTO retivedData=details.getDTO();
		return retivedData;
	}

}
