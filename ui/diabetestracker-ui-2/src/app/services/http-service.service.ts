import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DTUser } from '../models/dt-user';
import { Observable } from 'rxjs';
import { DTUserDailyData } from '../models/dt-user-daily-data';
import { SecurityValues } from '../models/security-values.model';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private http: HttpClient) { }

  registerTheUser(user: DTUser){
    return this.http.post("/api/user", user);
  }

  getUserSecurityQuestions(){
    return this.http.get("/api/security/phase2");
  }

  validateUserSecuirity(validatableAnswers: SecurityValues[]){
    return this.http.post("/api/security/validate", validatableAnswers);
  }

  getUserDailyData(){
    return this.http.get("/api/data");
  }

  addUserDailyData(userData: DTUserDailyData){
    return this.http.post("/api/data", userData);
  }

  checkForUsernameAvailability(userData: DTUser){
    return this.http.post("/api/user/availablility", userData);
  }

  fetchSecurityImages(){
    return this.http.get("/api/data/securityimages");
  }

  resetPassword(email: string){
    return this.http.post("/api/user/forgetpassword", email);
  }

}
