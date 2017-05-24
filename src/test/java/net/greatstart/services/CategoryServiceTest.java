package net.greatstart.services;

import net.greatstart.dao.CategoryDao;
import net.greatstart.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    private static final String TEST_CAT = "Test category";
    @Mock
    private CategoryDao categoryDao;
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

}