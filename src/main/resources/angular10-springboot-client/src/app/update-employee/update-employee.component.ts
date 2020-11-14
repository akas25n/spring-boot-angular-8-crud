import { Component, OnInit } from '@angular/core';
import {EmplyeeService} from "../emplyee.service";
import {Employee} from "../employee";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  employee : Employee = new Employee();
  id : number;

  constructor(private employeeService: EmplyeeService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.employeeService.getEmployeeById(this.id).subscribe(data=>{
      this.employee = data;
      },
      error => console.log(error));
  }
  redirectToEmployeeList(){
    this.router.navigate(['/employees'])
  }

  onSubmit() {
    this.employeeService.updateEmployee(this.id, this.employee).subscribe(data=>{
    this.redirectToEmployeeList();
    },
      error => console.log(error));
  }
}
