package com.revature.TeamCP2;

import com.revature.TeamCP2.beans.controllers.CategoriesController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.revature.TeamCP2.beans.services.CategoriesService;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoriesController.class)
public class CategoriesServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CategoriesService categoriesService;

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/categories/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(categoriesService, times(1)).getAllUsers();
    }

    @Test
    public void getLocation() throws Exception {
        mockMvc.perform(get("/delete/1"))
                .andExpect(status().isOk());

        verify(categoriesService, times(1)).deleteCategories(1);
    }
    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/create/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(categoriesService, times(1)).create();
    }
    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/update/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(categoriesService, times(1)).updateById();
    }
}