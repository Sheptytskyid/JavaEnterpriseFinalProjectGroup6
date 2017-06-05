package net.greatstart.services;

import net.greatstart.dao.CategoryDao;
import net.greatstart.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic layer for {@link net.greatstart.model.Category}.
 */

@Service
public class CategoryService {
    private CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryDao.save(category);
    }

    public Category findCategoryByName(String name) {
        return categoryDao.getByName(name);
    }

    public List<Category> getAllCategories() {
        Pageable pageable = new PageRequest(0, Integer.MAX_VALUE);
        return categoryDao.findAll(pageable).getContent();
    }
}
