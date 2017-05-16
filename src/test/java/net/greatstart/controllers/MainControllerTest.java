package net.greatstart.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {
    @InjectMocks
    private MainController controller;
    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = standaloneSetup(controller).build();
    }

    @Test(timeout = 2000)
    public void frontPageShouldReturnView() throws Exception {
        mvc.perform(get("/indexjsp")).andExpect(view().name("index"));
    }
}