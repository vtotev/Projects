package test.task.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.task.employees.entity.Project;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByProjectId(Long projectId);

    @Query("select p from Project p where size(p.employees) >= 2")
    List<Project> getAllProjectsWithMoreThanOneEmployee();
}
