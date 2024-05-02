package hu.kuerthl.runrace;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<ResultEntity,Long > {
    List<ResultEntity> findByRace(RaceEntity race);
}
