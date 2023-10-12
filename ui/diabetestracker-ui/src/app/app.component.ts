import { Component } from '@angular/core';
import { AppService } from './services/app.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'diabetestracker-ui';
  postUsernamePasswordSignIn = false;
  postAuthenticationCompletion = false;
  constructor(private appService: AppService, private router: Router) {
    this.appService.loginSuccessEventSubject.subscribe(val => {
      if (val) {
        this.postUsernamePasswordSignIn = true;
      }
    });
    
    this.appService.logoutSuccessEventSubject.subscribe(val => {
      if (val) {
        this.postUsernamePasswordSignIn = false;
        this.postAuthenticationCompletion = false;
      }
    });

    this.appService.securityQuestionsAnsweredSuccessEventSubject.subscribe(val => {
      if (val) {
        this.postAuthenticationCompletion = true;
      }
    })
  }

  logout(){
    this.appService.authenticated = false;
    this.appService.authHeaderValue = null;
    this.appService.logoutSuccessEventSubject.next(true);
    this.router.navigateByUrl("/").finally(()=>{
      window.alert("Logout successfull!");
    });
  }

}
