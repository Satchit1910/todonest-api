package com.todonest.api.repository;

import com.todonest.api.entity.Project;
import com.todonest.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUser(User user);
}
