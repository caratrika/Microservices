package net.javaguides.employeeservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.APIClient;
import net.javaguides.employeeservice.service.EmployeeService;
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
	super();
	this.employeeRepository = employeeRepository;
}
	
	public EmployeeServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	//private WebClient webClient;
	@Autowired
	private APIClient apiClient;
	
	public EmployeeServiceImpl(APIClient apiClient) {
		super();
		this.apiClient = apiClient;
	}

	@Override
public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		
		Employee employee = new Employee(
			employeeDto.getId(),
			employeeDto.getFirstName(),
			employeeDto.getLastName(),
			employeeDto.getEmail(),
			employeeDto.getDepartmentCode()
				);
		Employee saveDEmployee = employeeRepository.save(employee);
		 EmployeeDto savedEmployeeDto = new EmployeeDto(
				 saveDEmployee.getId(),
				 saveDEmployee.getFirstName(),
				 saveDEmployee.getLastName(),
				 saveDEmployee.getEmail(),
				 saveDEmployee.getDepartmentCode()
				 );
	return savedEmployeeDto;
}
	@Override
	public APIResponseDto getEmployeeById(Long employeeId) {
		
		Employee employee = employeeRepository.findById(employeeId).get();
		// ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
		//DepartmentDto.class);
		 //
		//DepartmentDto departmentDto = responseEntity.getBody(); 
		
//		DepartmentDto departmentDto = webClient.get()
//		     .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//		.retrieve()
//		.bodyToMono(DepartmentDto.class)
//		.block();
		DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
		EmployeeDto employeeDto = new EmployeeDto(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getDepartmentCode()
				);
		
		
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setDepartment(departmentDto);
		
		
		return apiResponseDto ;
	}
}
