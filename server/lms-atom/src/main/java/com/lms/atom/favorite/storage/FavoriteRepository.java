package com.lms.atom.favorite.storage;

import com.lms.atom.favorite.storage.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
