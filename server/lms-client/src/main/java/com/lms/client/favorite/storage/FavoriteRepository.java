package com.lms.client.favorite.storage;

import com.lms.client.favorite.storage.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
