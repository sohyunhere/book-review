package com.example.bookreview.location.repo;

import com.example.bookreview.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Long> {
}
