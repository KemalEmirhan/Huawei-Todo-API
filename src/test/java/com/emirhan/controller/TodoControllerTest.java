package com.emirhan.controller;

import com.emirhan.HuaweiTodoAppApplication;
import com.emirhan.controllers.TodoController;
import com.emirhan.repositories.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HuaweiTodoAppApplication.class)
public class TodoControllerTest {


    private MockMvc mockMvc;

    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    private TodoController todoController;

    @Before
    public void setup () {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    @Test
    public void getAllTodos() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("https://huawei-todo-api.herokuapp.com/api/todos");
        mockMvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andDo(MockMvcResultHandlers.print());
    }


}
