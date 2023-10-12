import { Component, OnInit } from '@angular/core';
import { AppService } from '../services/app.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials = {username: '', password: ''};
  authError = false;

  constructor(private app: AppService, private router: Router) { }

  ngOnInit() {
    //this.app.securityQuestionsAnsweredSuccessEventSubject.next(false);
    this.app.logoutSuccessEventSubject.next(true);
  }

  login() {
    this.app.authenticate(this.credentials).pipe(catchError(err=>of(false))).subscribe((authenticated=>{
      if(authenticated){
        console.log('user authentication success!!!');
        this.router.navigateByUrl('securityquestions');
      } else {
        console.log('user authentication failed!');
        this.authError = true;
      }
    }));
    return false;
  }

  forgotPassword(){
    this.router.navigateByUrl("forgotpassword")
  }

}
