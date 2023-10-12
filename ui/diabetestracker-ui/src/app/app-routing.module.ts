import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { SecurityQuestionsComponent } from './security-questions/security-questions.component';
import { SignupComponent } from './signup/signup.component';
import { UserDiabetesDataComponent } from './user-diabetes-data/user-diabetes-data.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'securityquestions', component: SecurityQuestionsComponent },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: SignupComponent },
  {path: 'adddata', component: UserDiabetesDataComponent},
  {path: 'forgotpassword', component: ForgetPasswordComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
