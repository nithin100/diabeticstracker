import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Subject, of } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AppService {

  authenticated = false;
  username: string;
  password: string;
  authHeader: HttpHeaders;
  authHeaderValue: any;

  loginSuccessEventSubject: Subject<any> = new Subject();
  securityQuestionsAnsweredSuccessEventSubject: Subject<any> = new Subject();
  logoutSuccessEventSubject: Subject<any> = new Subject();
  userRegistrationSuccessfull: Subject<any> = new Subject();

  constructor(private http: HttpClient) {
  }

  authenticate(credentials) {
        this.authHeaderValue = 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        this.authHeader = new HttpHeaders(credentials ? {
            authorization : this.authHeaderValue
        } : {});

        return this.http.get('/api/user', {headers: this.authHeader}).pipe(map(res=>{
            if(res['name']){
                this.authenticated = true;
                this.username = res['name'];
                this.loginSuccessEventSubject.next(this.authenticated);
            }
            return this.authenticated;
        }));
    }

    login(credentials) {
       return this.http.post('/login', credentials, { withCredentials: true });
    }

}