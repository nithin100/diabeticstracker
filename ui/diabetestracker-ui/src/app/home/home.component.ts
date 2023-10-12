import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpServiceService } from '../services/http-service.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  diabetesData = [
    // {
    //   date: '2023-04-30',
    //   bloodSugarAM: 85,
    //   breakfast: 'Oatmeal',
    //   lunch: 'Chicken salad',
    //   dinner: 'Salmon and vegetables',
    //   bloodSugarPM: 93
    // },
    // {
    //   date: '2023-05-01',
    //   bloodSugarAM: 90,
    //   breakfast: 'Scrambled eggs',
    //   lunch: 'Turkey sandwich',
    //   dinner: 'Beef stir-fry',
    //   bloodSugarPM: 98
    // }
  ];

  constructor(private router: Router, private httpService: HttpServiceService) { }

  ngOnInit() {
    this.httpService.getUserDailyData().pipe((catchError(err=>of({})))).subscribe((data: any[])=>{
      this.diabetesData = [...data];
    })
  }

  addData(){
    this.router.navigateByUrl('adddata');
  }

}
