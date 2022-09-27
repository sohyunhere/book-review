package com.example.bookreview.batch;

import com.example.bookreview.batch.model.PostCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCountRepo extends JpaRepository<PostCount, Long> {
}
