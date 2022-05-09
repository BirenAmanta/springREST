package com.mindtree.intern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public Integer allocateProject(ProjectDTO project) throws InternException {
		Optional<Mentor> data = mentorRepo.findById(project.getMentorDTO().getMentorId());
		if (data.isPresent()) {
			if (data.get().getNumberOfProjectMentored() >= 3) {
				throw new InternException("Service.CANNOT_ALLOCATE_PROJECT");
			} else {
				Project entity = new Project();
				entity.setIdeaOwner(project.getIdeaOwner());
				entity.setMentor(data.get());
				entity.setProjectId(project.getProjectId());
				entity.setProjectName(project.getProjectName());
				entity.setReleaseDate(project.getReleaseDate());

				Project projectSave = projectRepo.save(entity);
				data.get().setNumberOfProjectMentored(data.get().getNumberOfProjectMentored() + 1);
				return projectSave.getProjectId();
			}
		} else {
			throw new InternException("Service.MENTOR_NOT_FOUND");
		}

	}

	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InternException {
		List<Mentor> retrivedData = mentorRepo.findByNumberOfProjectMentored(numberOfProjectsMentored);
		if (retrivedData.isEmpty()) {
			throw new InternException("Service.MENTOR_NOT_FOUND");
		}
		List<MentorDTO> details = new ArrayList<>();
		retrivedData.stream().forEach((data) -> {
			MentorDTO mentorDTO = new MentorDTO();
			mentorDTO.setMentorId(data.getMentorId());
			mentorDTO.setMentorName(data.getMentorName());
			mentorDTO.setNumberOfProjectMentored(data.getNumberOfProjectMentored());
			details.add(mentorDTO);
		});
		return details;
	}

	@Override
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws InternException {
		Optional<Mentor> mentorData = mentorRepo.findById(mentorId);
		Mentor mentorDetails = mentorData.orElseThrow(() -> new InternException("Service.MENTOR_NOT_FOUND"));
		if (mentorDetails.getNumberOfProjectMentored() >= 3) {
			throw new InternException("Service.CANNOT_ALLOCATE_PROJECT");
		}
		Optional<Project> projectData = projectRepo.findById(projectId);
		Project projectDetails = projectData.orElseThrow(() -> new InternException("Service.PROJECT_NOT_FOUND"));
		projectDetails.setMentor(mentorDetails);
		mentorDetails.setNumberOfProjectMentored(mentorDetails.getNumberOfProjectMentored() + 1);
	}

	@Override
	public void deleteProject(Integer projectId) throws InternException {
		Optional<Project> projectData = projectRepo.findById(projectId);
		Project projectDetails = projectData.orElseThrow(() -> new InternException("Service.PROJECT_NOT_FOUND"));
		Optional<Mentor> mentorData = mentorRepo.findById(projectDetails.getMentor().getMentorId());
		Mentor mentorDetails = mentorData.orElseThrow(() -> new InternException("Service.MENTOR_NOT_FOUND"));
		mentorDetails.setNumberOfProjectMentored(mentorDetails.getNumberOfProjectMentored() - 1);
		projectDetails.setMentor(null);
		projectRepo.delete(projectDetails);
	}

	@Override
	public ProjectDTO getDetails(Integer projectId) throws InternException {
		Optional<Project> data = projectRepo.findById(projectId);
		Project details = data.orElseThrow(() -> new InternException("Service.PROJECT_NOT_FOUND"));
		Mentor mentor = mentorRepo.findById(details.getMentor().getMentorId()).get();
		MentorDTO mentorDTO = new MentorDTO();
		mentorDTO.setMentorId(mentor.getMentorId());
		mentorDTO.setMentorName(mentor.getMentorName());
		mentorDTO.setNumberOfProjectMentored(mentor.getNumberOfProjectMentored());
		ProjectDTO retrivedData = new ProjectDTO();
		retrivedData.setIdeaOwner(details.getIdeaOwner());
		retrivedData.setMentorDTO(mentorDTO);
		retrivedData.setProjectId(details.getProjectId());
		retrivedData.setProjectName(details.getProjectName());
		retrivedData.setReleaseDate(details.getReleaseDate());
		return retrivedData;
	}

}
