package hu.kuerthl.runrace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceRepository extends JpaRepository<RaceEntity, Long> {
    List<RaceEntity> findByRaceName(String raceName);
}