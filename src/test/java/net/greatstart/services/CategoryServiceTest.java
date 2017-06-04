package net.greatstart.services;

import net.greatstart.dao.CategoryDao;
import net.greatstart.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    private static final String TEST_CAT = "Test category";

    @Mock
    private CategoryDao categoryDao;
    @Mock
    private Page<Category> page;
    @InjectMocks
    private CategoryService categoryService;
    @Test
    public void addCategory() throws Exception {
        categoryService.addCategory(TEST_CAT);
        verify(categoryDao).save(any(Category.class));
    }

    @Test
    public void findCategoryByName() throws Exception {
        categoryService.findCategoryByName(TEST_CAT);
        verify(categoryDao).getByName(TEST_CAT);
    }

    @Test
    public void getAllCategories() throws Exception {
        when(categoryDao.findAll(any(Pageable.class))).thenReturn(page);
        categoryService.getAllCategories();
    }

}