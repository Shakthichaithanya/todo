package com.todo.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.app.dto.TaskDTO;
import com.todo.app.entity.Task;
import com.todo.app.service.TaskService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TaskController.class)
class TaskControllerTest {

    @MockBean
    private TaskService taskService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private Task task;
    private TaskDTO taskDTO;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<TaskDTO> taskDTOList;
    private
    @BeforeEach
    void setUp() {
        taskDTOList = new ArrayList<>();
        startDate = LocalDateTime.now();
        endDate = LocalDateTime.now().plus(24,ChronoUnit.HOURS);
        task = new Task(1l,"sample","sample task",startDate,endDate,"pending","shakthi");
        taskDTO = new TaskDTO("sample","sample task",startDate,endDate,"pending","shakthi");
        taskDTOList.add(taskDTO);
    }


    /**
     * test case to add new task to database
     * @throws Exception
     */
    @Test
    @DisplayName("JUnit test for post mapping adding new task")
    void addTask() throws Exception {
        BDDMockito.given(taskService.addTask(taskDTO)).willReturn("new task added");

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDTO)));

        response.andExpect(status().isCreated()).andExpect(jsonPath("$.message", CoreMatchers.is("new task added")));
    }


    /**
     * test case to get all task details
     * @throws Exception
     */
    @Test
    @DisplayName("JUnit test case for get mapping to get all tasks")
    void getAllTasks() throws Exception{
        BDDMockito.given(taskService.getAllTasks()).willReturn(taskDTOList);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/tasks"));

        response.andExpect(status().isOk()).andExpect(jsonPath("$.size()",CoreMatchers.is(taskDTOList.size())));
    }

    /**
     * test case to delete task from database
     * @throws Exception
     */
    @Test
    @DisplayName("JUnit test case for delete mapping to delete task using id")
    void deleteTask() throws Exception{
        BDDMockito.given(taskService.deleteTask(1l)).willReturn("Task deleted successfully");

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/1"));

        response.andExpect(status().isOk()).andExpect(jsonPath("$.message",CoreMatchers.is("Task deleted successfully")));
    }
}