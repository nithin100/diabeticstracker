import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import {DTUser} from '../models/dt-user';
import {SecurityValues} from '../models/security-values.model';
import { HttpServiceService } from '../services/http-service.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { Router } from '@angular/router';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  showPassword = false;
  showConfirmPassword=false;
  passwordStrength: number = 0;
  isSubmitting = false;
  
  selectedImageIndex: number = -1;
  images: any[] = [
  ];

  registrationForm = new FormGroup({
    fullname: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    dateofbirth: new FormControl('', Validators.required),
    phonenumber: new FormControl('', Validators.required),
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    confirmPassword: new FormControl('', Validators.required),
    securityQuestion1: new FormControl('', Validators.required),
    securityAnswer1: new FormControl('', Validators.required),
    securityQuestion2: new FormControl('', Validators.required),
    securityAnswer2: new FormControl('', Validators.required)
  });
  
  iconLibrary = {
    faEye,
    faEyeSlash
  };
  

  constructor(private httpService: HttpServiceService, private router: Router) { }

  ngOnInit() {
    
    this.httpService.fetchSecurityImages().subscribe((data: any[])=>{
      this.images = data;
    });
    // let x=0;
    // while(x<9){
    //   this.images.push({src:'https://via.placeholder.com/100x100', alt:`image+${x}`});
    //   x++;
    // }
  }

  
  registerUser() {
    if (!this.registrationForm.valid || this.selectedImageIndex < 0) {
      window.alert("Fill all the details before submitting");
    } else {
      this.isSubmitting = true;
      const formValues = this.registrationForm.value;
      const user = this.mapFormValuesToDTUser(formValues);
      this.httpService.registerTheUser(user).pipe(catchError(err => {
        return of(false);
      })).subscribe(res => {
        this.isSubmitting = false;
        if (res) {
          console.log("User registered successfully!");
          window.alert("User registered successfully. Please login to continue");
          this.router.navigateByUrl('login');
          
        } else {
          console.log("Something went wrong!");
          window.alert("Something went wrong. Try again later!");
        }
      })
    }
  }

  onImageClick(index: number) {
    this.selectedImageIndex = index;
  }
  
  toggleShowPassword() {
    this.showPassword = !this.showPassword;
  }

  toggleShowConfirmPassword() {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

  mapFormValuesToDTUser(formValues: any): DTUser {
    const user: DTUser = new DTUser();
  
    // Map the form values to the user object
    user.fullName = formValues.fullname;
    user.email = formValues.email;
    user.phoneNumber = formValues.phonenumber;
    user.dateofbirth = formValues.dateofbirth;
    user.userName = formValues.username;
    user.password = formValues.password;
    user.securityValues = [];
    const securityValue1: SecurityValues = new SecurityValues();
    securityValue1.question = formValues.securityQuestion1;
    securityValue1.answer = formValues.securityAnswer1;
    securityValue1.securityType = "QUESTION";

    const securityValue2: SecurityValues = new SecurityValues();
    securityValue2.question = formValues.securityQuestion2;
    securityValue2.answer = formValues.securityAnswer2;
    securityValue2.securityType = "QUESTION";

    const securityValue3: SecurityValues = new SecurityValues();
    securityValue3.question = null;
    securityValue3.answer = this.images[this.selectedImageIndex].id;
    securityValue3.securityType = "IMAGE";
    
    user.securityValues.push(securityValue1);
    user.securityValues.push(securityValue2);
    user.securityValues.push(securityValue3);

    return user;
  }
  calculatePasswordStrength() {
    const password = this.registrationForm.get('password').value;
    let score = 0;
    if (password.length > 8) {
      score += 25;
    }
    if (password.match(/[a-z]+/)) {
      score += 25;
    }
    if (password.match(/[A-Z]+/)) {
      score += 25;
    }
    if (password.match(/[0-9]+/)) {
      score += 25;
    }
    this.passwordStrength = score;
  }
}
