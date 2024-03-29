package net.javaguides.organizationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.javaguides.organizationservice.dto.OrganizationDto;
import net.javaguides.organizationservice.service.OrganizationService;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
public class OrganizationController {

	@Autowired
	 private OrganizationService organizationService;

	    // Build Save Organization REST API
	    @PostMapping
	    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
	        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
	        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
	    }

		public OrganizationController(OrganizationService organizationService) {
			super();
			this.organizationService = organizationService;
		}

		public OrganizationController() {
			super();
			// TODO Auto-generated constructor stub
		}
		

	    //Build Get Organization by Code REST API
	  @GetMapping("{code}")
	   public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("code") String organizationCode){
	   OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
	    return ResponseEntity.ok(organizationDto);
	    }
	    
	    
}
