package net.greatstart.dao;

import net.greatstart.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A Spring Data supported interface to handle DAO operations on {@link net.greatstart.model.Category}.
 */

public interface CategoryDao extends PagingAndSortingRepository<Category, Long> {
    Category getByName(String name);
}
