import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpEvent, HttpResponse, HttpRequest, HttpHandler, HttpHeaders } from '@angular/common/http';
import { AppService } from '../services/app.service';

@Injectable({
  providedIn: 'root'
})
export class HttpinterceptorService implements HttpInterceptor {

  constructor(private appService: AppService){}
  
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let xhr;
    if(this.appService.authHeaderValue){
      xhr = req.clone({
        headers: req.headers.set('X-Requested-With', 'XMLHttpRequest').set('Authorization', this.appService.authHeaderValue),
        withCredentials: true
      });
    } else {
      xhr = req.clone({
        headers: req.headers.set('X-Requested-With', 'XMLHttpRequest'),
        withCredentials: true
      });
    }
    return next.handle(xhr);
  }

}
