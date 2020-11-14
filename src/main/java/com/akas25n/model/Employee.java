package com.akas25n.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EMPLOYEE")
@Data
public class Employee {

	@Id
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;

}
