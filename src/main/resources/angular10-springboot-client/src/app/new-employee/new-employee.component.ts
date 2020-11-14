import { Component, OnInit } from '@angular/core';
import {Employee} from "../employee";
import {EmplyeeService} from "../emplyee.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-employee',
  templateUrl: './new-employee.component.html',
  styleUrls: ['./new-employee.component.css']
})
export class NewEmployeeComponent implements OnInit {

  employee : Employee = new Employee();

  constructor(private employeeService: EmplyeeService,
              private router: Router) { }

  ngOnInit(): void {
  }

  saveEmployee(){
    this.employeeService.createNewEmployee(this.employee).subscribe(data =>{
      console.log(data);
      this.redirectToEmployeeList();
    },
      error => console.log(error));
  }

  redirectToEmployeeList(){
    this.router.navigate(['/employees'])
  }
  onSubmit() {
    this.saveEmployee();
  }
}
