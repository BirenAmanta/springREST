package com.mindtree.intern.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.intern.entity.Mentor;

public interface MentorRepository extends CrudRepository<Mentor,Integer> {
	List<Mentor> findByNumberOfProjectMentored(Integer numberOfProjectMentored);
}
