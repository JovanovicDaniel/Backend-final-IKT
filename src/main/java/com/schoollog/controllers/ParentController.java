package com.schoollog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.schoollog.controllers.util.RESTError;
import com.schoollog.entities.ParentEntity;
import com.schoollog.entities.RoleEntity;
import com.schoollog.entities.UserEntity;
import com.schoollog.entities.dto.ParentDTO;
import com.schoollog.entities.dto.UserDTO;
import com.schoollog.services.ParentService;
import com.schoollog.services.UserService;	

@RestController
@RequestMapping(path="/api/schoollog/parents")
public class ParentController {
	@Autowired
	private ParentService service;
	@Autowired
	private UserService userService;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
	public ResponseEntity<?> getParentById(@PathVariable Integer id)
	{
		try
		{
			
			ParentEntity parent = service.findParentById(id);
			ParentDTO parentDto = new ParentDTO();
			parentDto.setEmail(parent.getEmail());
			parentDto.setFirstName(parent.getFirstName());
			parentDto.setLastName(parent.getLastName());
			
			UserEntity user =parent.getUser();
			UserDTO userDto = new UserDTO();
			userDto.setEmail(user.getEmail());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			RoleEntity role = user.getRole();
			userDto.setRoleId(role.getId());
			userDto.setUsername(user.getUsername());
			parentDto.setUserDto(userDto);
			
			if(parent.getId().equals(id))
			{
				return new ResponseEntity<>(parentDto, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(new RESTError(1, "Parent not found"), HttpStatus.NOT_FOUND);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value="/add")
	public ResponseEntity<?> addNewParent(@RequestBody ParentDTO parentDto)
	{
		try
		{
			service.addNewParent(parentDto);
		
			logger.info("Parent successfully added");  
		
		return new ResponseEntity<>(parentDto, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
			
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/change/{id}")
	public ResponseEntity<?> changeParent(@RequestBody ParentDTO parentDto, @PathVariable Integer id)
	{
		try
		{
			service.changeParent(parentDto, id);
		
			logger.info("Parent successfully changed"); 
			
			return new ResponseEntity<>(parentDto, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
			
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
	public void deleteParent(@PathVariable Integer id)
	{
		try
		{
			ParentEntity parent = service.findParentById(id);
			UserEntity user = parent.getUser();
			
			service.deleteParent(id);
			userService.deleteUser(user.getId());
			
			logger.info("Parent successfully deleted");
		}
		catch(Exception ex)
		{
			logger.error("Error: " + ex.getLocalizedMessage());  
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, value = "/get/all")
	public ResponseEntity<?> getAllParents()
	{
		try
		{
			Iterable<ParentEntity> parents = service.getAllParents();
			List<ParentDTO> dtos = new ArrayList<ParentDTO>();
			
			for(ParentEntity parent : parents)
			{

				ParentDTO parentDto = new ParentDTO();
				parentDto.setEmail(parent.getEmail());
				parentDto.setFirstName(parent.getFirstName());
				parentDto.setLastName(parent.getLastName());
				
				UserEntity user =parent.getUser();
				UserDTO userDto = new UserDTO();
				userDto.setEmail(user.getEmail());
				userDto.setFirstName(user.getFirstName());
				userDto.setLastName(user.getLastName());
				RoleEntity role = user.getRole();
				userDto.setRoleId(role.getId());
				userDto.setUsername(user.getUsername());
				parentDto.setUserDto(userDto);
				
				dtos.add(parentDto);
			}
			
			return new ResponseEntity<>(dtos, HttpStatus.OK);
			
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(new RESTError(2, "Exception:" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
