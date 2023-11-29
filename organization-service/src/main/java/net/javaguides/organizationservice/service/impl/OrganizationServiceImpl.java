package net.javaguides.organizationservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.organizationservice.dto.OrganizationDto;
import net.javaguides.organizationservice.entity.Organization;
import net.javaguides.organizationservice.mapper.OrganizationMapper;
import net.javaguides.organizationservice.repository.OrganizationRepository;
import net.javaguides.organizationservice.service.OrganizationService;

@Service
@AllArgsConstructor

public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;
	 @Override
	    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
	 
		 Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

	        Organization savedOrganization = organizationRepository.save(organization);

	        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
	 
	 }
		
	  @Override
	  public OrganizationDto getOrganizationByCode(String organizationCode) {
	   Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
	  return OrganizationMapper.mapToOrganizationDto(organization);
	 }
	 
}
