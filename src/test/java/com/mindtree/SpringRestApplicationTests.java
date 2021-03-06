package com.mindtree;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.mindtree.intern.dto.MentorDTO;
import com.mindtree.intern.dto.ProjectDTO;
import com.mindtree.intern.entity.Mentor;
import com.mindtree.intern.exception.InternException;
import com.mindtree.intern.repository.MentorRepository;
import com.mindtree.intern.service.ProjectAllocationService;
import com.mindtree.intern.service.ProjectAllocationServiceImpl;

@SpringBootTest
class SpringRestApplicationTests {

	@Mock
	private MentorRepository mentorRepository;

	@InjectMocks
	private ProjectAllocationService projectAllocationService = new ProjectAllocationServiceImpl();

	@Test
	public void allocateProjectCannotAllocateTest() throws Exception {
		ProjectDTO project = new ProjectDTO();
		project.setIdeaOwner(1009);
		MentorDTO mentor = new MentorDTO();
		mentor.setMentorId(Integer.toString(1002));
		Mentor data=new Mentor();
		data.setMentorId(1002);
		data.setMentorId(1002);
		data.setMentorName("Warner");
		data.setNumberOfProjectMentored(3);
		project.setMentorDTO(mentor);
		project.setProjectId(1);
		project.setProjectName("Android Shopping App");
		project.setReleaseDate(LocalDate.of(2019, 9, 27));
		Mockito.when(mentorRepository.findById(Integer.parseInt(mentor.getMentorId()))).thenReturn(Optional.of(data));
		InternException e = Assertions.assertThrows(InternException.class,
				() -> projectAllocationService.allocateProject(project));
		Assertions.assertEquals("Service.CANNOT_ALLOCATE_PROJECT", e.getMessage());

	}

	@Test
	public void allocateProjectMentorNotFoundTest() throws Exception {
		ProjectDTO project = new ProjectDTO();
		project.setMentorDTO(new MentorDTO());
		project.getMentorDTO().setMentorId(Integer.toString(9000));
		Mockito.when(mentorRepository.findById(Integer.parseInt(project.getMentorDTO().getMentorId())))
				.thenReturn(Optional.ofNullable(null));
		InternException e = Assertions.assertThrows(InternException.class,
				() -> projectAllocationService.allocateProject(project));
		Assertions.assertEquals("Service.MENTOR_NOT_FOUND", e.getMessage());
	}

}
