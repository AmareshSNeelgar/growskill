package com.platform.growskill.repo;

import com.platform.growskill.entity.Course;
import com.platform.growskill.enums.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {


    @Query("select c from Course c where c.type = ?1")
     public List<Course> getByType(CourseType type);




}
