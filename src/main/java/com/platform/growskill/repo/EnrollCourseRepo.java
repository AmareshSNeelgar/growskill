package com.platform.growskill.repo;

import com.platform.growskill.entity.EnRollCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollCourseRepo extends JpaRepository<EnRollCourse,Long> {

    @Query("select ec from EnRollCourse ec where ec.studentId = ?1 and ec.courseId =?2")
    public Optional<EnRollCourse> getByStudentId(Long studentId, Long courseId);
}
