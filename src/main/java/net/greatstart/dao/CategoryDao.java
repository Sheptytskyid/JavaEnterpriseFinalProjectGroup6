package net.greatstart.dao;

import net.greatstart.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryDao extends PagingAndSortingRepository<Category, Long> {
    Category getByName(String name);
}
