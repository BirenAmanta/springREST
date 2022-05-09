package com.mindtree.intern.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.intern.dto.MentorDTO;
import com.mindtree.intern.dto.ProjectDTO;
import com.mindtree.intern.exception.InternException;
import com.mindtree.intern.service.ProjectAllocationService;

@RestController
@CrossOrigin
@RequestMapping(value = "/intern")
public class ProjectAllocationApi {
	
	static final Log LOGGER=LogFactory.getLog(ProjectAllocationService.class);
	
	@Autowired
	private Environment enviroment;

	@Autowired
	private ProjectAllocationService projectAllocationService;

	@PostMapping(value = "/project")
	public ResponseEntity<String> allocateProject(@RequestBody @Valid ProjectDTO project) throws InternException {
		Integer Id = projectAllocationService.allocateProject(project);
		String message = enviroment.getProperty("API.ALLOCATION_SUCCESS") + Id;
		LOGGER.info(message);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@GetMapping(value = "/mentors/{numberOfProjectsMentored}")
	public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable Integer numberOfProjectsMentored)
			throws InternException {
		List<MentorDTO> mentorDetails = projectAllocationService.getMentors(numberOfProjectsMentored);
		LOGGER.info("retrived data: "+mentorDetails);
		return new ResponseEntity<>(mentorDetails, HttpStatus.OK);
	}

	@GetMapping(value = "/project/{projectId}")
	public ResponseEntity<ProjectDTO> getProjectDetails(
			@PathVariable @Pattern(regexp = "([0-9]+)", message = "project.projectid.invalid") Integer projectId)
			throws InternException {
		ProjectDTO details = projectAllocationService.getDetails(projectId);
		LOGGER.info("retrived data: "+details);
		return new ResponseEntity<>(details, HttpStatus.OK);
	}

	@PutMapping(value = "/projectUpdate/{projectId}/{mentorId}")
	public ResponseEntity<String> updateProjectMentor(
			@PathVariable @Pattern(regexp = "([0-9]+)", message = "project.projectid.invalid") Integer projectId,
			@PathVariable @Pattern(regexp = "([0-9]{4})", message = "mentor.mentorid.invalid") Integer mentorId)
			throws InternException {
		projectAllocationService.updateProjectMentor(projectId, mentorId);
		String message = enviroment.getProperty("API.PROJECT_UPDATE_SUCCESS");
		LOGGER.info(message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteProject/{projectId}")
	public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) throws InternException {
		projectAllocationService.deleteProject(projectId);
		String message = enviroment.getProperty("API.PROJECT_DELETE _SUCCESS");
		LOGGER.info(message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
