package net.javaguides.departmentservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.DepartmentService;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
@Autowired
	private DepartmentRepository departmentRepository;
	
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}


	public DepartmentServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}





	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		Department department = new Department(
				departmentDto.getId(),
				departmentDto.getDepartmentName(),
				departmentDto.getDepartmentDescription(),
				departmentDto.getDepartmentCode()
				);
			Department savedDepartment = departmentRepository.save(department);	
		DepartmentDto savedDepartmentDto = new DepartmentDto(
				savedDepartment.getId(),
				savedDepartment.getDepartmentName(),
				savedDepartment.getDepartmentDescription(),
				savedDepartment.getDepartmentCode()
				);
			return savedDepartmentDto;
	}
	@Override
	public DepartmentDto getDepartmentByCode(String departmentcode) {
		
		Department department = departmentRepository.findByDepartmentCode(departmentcode);
		DepartmentDto departmentDto =new DepartmentDto(
				department.getId(),
				department.getDepartmentName(),
				department.getDepartmentDescription(),
				department.getDepartmentCode()
				);
				
		return departmentDto;
	}
}
