import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Employee} from "./employee";

@Injectable({
  providedIn: 'root'
})
export class EmplyeeService {

  private baseURl = "http://localhost:8080/api/employees";
  constructor(private httpClient: HttpClient) { }

  getEmployeeList(): Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseURl}`);
  }

  createNewEmployee(employee:Employee) : Observable<any>{
    return this.httpClient.post(`${this.baseURl}`, employee);
  }

  getEmployeeById(id: number): Observable<Employee>{
    return this.httpClient.get<Employee>(`${this.baseURl}/${id}`);
  }

  updateEmployee(id: number, employee: Employee): Observable<any>{
    return this.httpClient.put(`${this.baseURl}/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<any>{
    return this.httpClient.delete(`${this.baseURl}/${id}`);
  }
}
