package ModelTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.impl.DepartmentServiceImpl;

public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDepartment() {
        // Arrange
        DepartmentDto departmentDto = new DepartmentDto(null, "IT", "Information Technology", "IT001");
        Department savedDepartment = new Department(1L, "IT", "Information Technology", "IT001");

        // Act
        when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(savedDepartment);
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);

        // Assert
        assertEquals(savedDepartment.getId(), savedDepartmentDto.getId());
        assertEquals(savedDepartment.getDepartmentName(), savedDepartmentDto.getDepartmentName());
        assertEquals(savedDepartment.getDepartmentDescription(), savedDepartmentDto.getDepartmentDescription());
        assertEquals(savedDepartment.getDepartmentCode(), savedDepartmentDto.getDepartmentCode());
    }

    @Test
    public void testGetDepartmentByCode() {
        // Arrange
        String departmentCode = "IT001";
        Department department = new Department(1L, "IT", "Information Technology", departmentCode);

        // Act
        when(departmentRepository.findByDepartmentCode(departmentCode)).thenReturn(department);
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);

        // Assert
        assertEquals(department.getId(), departmentDto.getId());
        assertEquals(department.getDepartmentName(), departmentDto.getDepartmentName());
        assertEquals(department.getDepartmentDescription(), departmentDto.getDepartmentDescription());
        assertEquals(department.getDepartmentCode(), departmentDto.getDepartmentCode());
    }

//    @Test
//    public void testGetDepartmentByCodeNotFound() {
//        // Arrange
//        String departmentCode = "NonExistentCode";
//
//        // Act
//        when(departmentRepository.findByDepartmentCode(departmentCode)).thenReturn(null);
//        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
//
//        // Assert
//        assertEquals(null, departmentDto);
//    }
}
