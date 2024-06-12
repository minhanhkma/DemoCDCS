package example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import example.demo.entities.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByUrl(String url);

}

