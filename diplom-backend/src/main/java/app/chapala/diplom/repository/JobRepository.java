package app.chapala.diplom.repository;

import app.chapala.diplom.model.Job;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
    @Query(value = "Select * From diplom_schemas.job o Where o.type = 'New' and o.project_id = :projectId", nativeQuery = true)
    List<Job> getByTypeNew(@Param("projectId")UUID projectId);

    @Query(value = "Select * From diplom_schemas.job o Where o.type = 'ToDo' and o.project_id = :projectId", nativeQuery = true)
    List<Job> getByTypeToDo(@Param("projectId")UUID projectId);

    @Query(value = "Select * From diplom_schemas.job o Where o.type = 'InProgress' and o.project_id = :projectId", nativeQuery = true)
    List<Job> getByTypeInProgress(@Param("projectId")UUID projectId);

    @Query(value = "Select * From diplom_schemas.job o Where o.type = 'Done' and o.project_id = :projectId", nativeQuery = true)
    List<Job> getByTypeDone(@Param("projectId")UUID projectId);

    @Query(value = "Select * From diplom_schemas.job o Where o.type = 'Testing' and o.project_id = :projectId", nativeQuery = true)
    List<Job> getByTypeTesting(@Param("projectId")UUID projectId);

    @Query(value = "Select * from diplom_schemas.job o Where o.project_id = :projectId", nativeQuery = true)
    List<Job> findJobListByIdProject(@Param("projectId") UUID projectId);

    @Query(value = "Select * from diplom_schemas.job o Where o.project_id = ?1 and o.type = ?2", nativeQuery = true)
    List<Job> findJobListByStage(UUID projectId, String stage);


    @Transactional
    @Modifying
    @Query(value = "Update diplom_schemas.job set type = ?1 where id = ?2", nativeQuery = true)
    void setType(String type, UUID id);

    @Transactional
    @Modifying
    @Query(value = "Delete from diplom_schemas.user_jobs where job_id = ?1", nativeQuery = true)
    void delete(UUID id);
}
