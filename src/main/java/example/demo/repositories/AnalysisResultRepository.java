package example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import example.demo.entities.AnalysisResult;

public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long> {
}

