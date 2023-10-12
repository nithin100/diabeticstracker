import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from '../services/http-service.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {

  email: string;
  temppasswordsent: boolean = false;
  error: boolean = false;
  constructor(private http: HttpServiceService) { }

  ngOnInit() {
  }

  sendTempPassword(){
    this.http.resetPassword(this.email).pipe(catchError(err=>of(false))).subscribe((result:boolean)=>{
      if(result){
        this.temppasswordsent = true;
      } else {
        this.error = true;
      }
    })
  }

}
