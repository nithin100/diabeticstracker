import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from '../services/http-service.service';
import { AppService } from '../services/app.service';
import { catchError, map } from 'rxjs/operators';
import { FormGroup, FormControl } from '@angular/forms';
import { of } from 'rxjs';
import { Router } from '@angular/router';
import { SecurityValues } from '../models/security-values.model';

@Component({
  selector: 'app-security-questions',
  templateUrl: './security-questions.component.html',
  styleUrls: ['./security-questions.component.css']
})
export class SecurityQuestionsComponent implements OnInit {

  userquestions: SecurityValues[] = [];
  username: string;
  securityQuestionsForm = new FormGroup({
    securityAnswer1: new FormControl(),
    securityAnswer2: new FormControl()
  });
  selectedImageIndex: number = -1;
  images: any[] = [
  ];
  incorrectsecurityanswers=false;
  constructor(private http: HttpServiceService, private appService: AppService, private router: Router) { }

  ngOnInit() {
    this.http.fetchSecurityImages().subscribe((data: any[])=>{
      this.images = data;
    });
    // let x=0;
    // while(x<9){
    //   this.images.push({src:'https://via.placeholder.com/100x100', alt:`image+${x}`});
    //   x++;
    // }
    this.http.getUserSecurityQuestions().pipe(catchError(err => {
      console.log("Error while fetching user questions");
      return of([]);
    })).subscribe((questions: any[]) => {
      this.username = this.appService.username;
      questions.forEach(q=>{
        if(q.securityType === "QUESTION"){
          this.userquestions.push(q);
        }
      })
    });

    this.securityQuestionsForm.valueChanges.subscribe(()=>{
      this.incorrectsecurityanswers = false;
    });
  }

  setAnswer(event, question){
    console.log(question+" "+event.value);
  }

  answersSubmitted() {
    console.log(this.securityQuestionsForm);
    const validateSecuirtyQuestions: SecurityValues[] = [...this.userquestions];
    validateSecuirtyQuestions[0].answer = this.securityQuestionsForm.value.securityAnswer1;
    validateSecuirtyQuestions[1].answer = this.securityQuestionsForm.value.securityAnswer2;
    
    const imageAnser = {} as SecurityValues;
    imageAnser.answer = this.images[this.selectedImageIndex].id;
    imageAnser.securityType="IMAGE";

    validateSecuirtyQuestions.push(imageAnser)
    this.http.validateUserSecuirity(validateSecuirtyQuestions).pipe((catchError(err=>{
      return of(false);
    }))).subscribe(validAnswers=>{
      if(validAnswers){
        console.log("validation passed");
        this.appService.securityQuestionsAnsweredSuccessEventSubject.next(true);
        this.router.navigateByUrl('home');
      } else {
        console.log("validation failed");
        this.incorrectsecurityanswers = true;
      }
    })
  }

  onImageClick(index: number) {
    this.selectedImageIndex = index;
  }
  
}
