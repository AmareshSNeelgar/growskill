package com.platform.growskill.repo;

import com.platform.growskill.entity.CourseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseClassRepo extends JpaRepository<CourseClass, Long> {
}
