import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DTUserDailyData } from '../models/dt-user-daily-data'
import { HttpServiceService } from '../services/http-service.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-diabetes-data',
  templateUrl: './user-diabetes-data.component.html',
  styleUrls: ['./user-diabetes-data.component.css']
})
export class UserDiabetesDataComponent implements OnInit {

  bloodSugarForm: FormGroup;
  errorSeen = false;

  constructor(private fb: FormBuilder, private http: HttpServiceService, private router: Router) {
    this.bloodSugarForm = this.fb.group({
      date: ['', Validators.required],
      fastingBloodSugarLevel: ['', Validators.required],
      fastingBloodSugarTime: ['', Validators.required],
      breakfast: this.fb.group({
        time: [''],
        food: ['']
      }),
      lunch: this.fb.group({
        time: [''],
        food: ['']
      }),
      dinner: this.fb.group({
        time: [''],
        food: ['']
      }),
      postDinnerBloodSugarLevel: ['', Validators.required],
      postDinnerBloodSugarTime: ['', Validators.required]
    });
  }

  saveData() {
    console.log(this.bloodSugarForm.value);
    const dailyDataRecord: DTUserDailyData = this.transformBloodSugarFormData(this.bloodSugarForm.value);
    this.http.addUserDailyData(dailyDataRecord).pipe(catchError(err=>of({failed: true}))).subscribe(data=>{
      if(data['failed']){
        this.errorSeen = true;
        window.scrollTo({ top: 0, behavior: 'smooth' });
      } else {
        this.router.navigateByUrl('home');
      }
    });
  }

  transformBloodSugarFormData(formData: any): DTUserDailyData {
    return new DTUserDailyData(
      new Date(formData.date),
      formData.fastingBloodSugarLevel,
      formData.breakfast.food,
      formData.breakfast.time,
      formData.lunch.food,
      formData.lunch.time,
      formData.dinner.food,
      formData.dinner.time,
      formData.postDinnerBloodSugarLevel
    );
  }

  ngOnInit() {

  }

}
