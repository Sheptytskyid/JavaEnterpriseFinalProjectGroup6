package net.greatstart.controllers;

import net.greatstart.services.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController controller;

    private MockMvc mockMvc;

    @Test
    public void getCategories() throws Exception {
        mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/api/category"))
                .andExpect(status().isOk());
        verify(categoryService).getAllCategories();
    }

}