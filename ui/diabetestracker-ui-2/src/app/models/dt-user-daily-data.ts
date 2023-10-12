export class DTUserDailyData {
    date: Date;
    bloodSugarAM: number;
    breakfast: string;
    breakfastTime: string;
    lunch: string;
    lunchTime: string;
    dinner: string;
    dinnerTime: string;
    bloodSugarPM: number;

    constructor(
        date: Date,
        bloodSugarAM: number,
        breakfast: string,
        breakfastTime: string,
        lunch: string,
        lunchTime: string,
        dinner: string,
        dinnerTime: string,
        bloodSugarPM: number
      ) {
        this.date = date;
        this.bloodSugarAM = bloodSugarAM;
        this.breakfast = breakfast;
        this.breakfastTime = breakfastTime;
        this.lunch = lunch;
        this.lunchTime = lunchTime;
        this.dinner = dinner;
        this.dinnerTime = dinnerTime;
        this.bloodSugarPM = bloodSugarPM;
      }
  }
  