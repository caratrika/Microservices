package ModelTesting;


import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.javaguides.departmentservice.controller.DepartmentController;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.service.DepartmentService;

public class DepartmentControllerTest {

    private MockMvc mockMvc;

    private DepartmentService departmentService;

    private DepartmentController departmentController;

    @BeforeEach
    public void setUp() {
        departmentService = mock(DepartmentService.class);
        departmentController = new DepartmentController(departmentService);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    public void testSaveDepartment() throws Exception {
        // Arrange
        DepartmentDto departmentDto = new DepartmentDto(null, "IT", "Information Technology", "IT001");
        when(departmentService.saveDepartment(departmentDto)).thenReturn(departmentDto);

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(departmentDto)))
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        DepartmentDto savedDepartmentDto = new ObjectMapper().readValue(content, DepartmentDto.class);

        assert savedDepartmentDto != null;
        assert savedDepartmentDto.getDepartmentName().equals("IT");
        // Add more assertions based on your DTO properties
    }

    @Test
    public void testGetDepartment() throws Exception {
        // Arrange
        String departmentCode = "IT001";
        DepartmentDto departmentDto = new DepartmentDto(1L, "IT", "Information Technology", departmentCode);
        when(departmentService.getDepartmentByCode(departmentCode)).thenReturn(departmentDto);

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/departments/{department-code}", departmentCode))
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        DepartmentDto retrievedDepartmentDto = new ObjectMapper().readValue(content, DepartmentDto.class);

        assert retrievedDepartmentDto != null;
        assert retrievedDepartmentDto.getDepartmentCode().equals("IT001");
        // Add more assertions based on your DTO properties
    }

    // Helper method to convert object to JSON string
    private static String asJsonString(Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
