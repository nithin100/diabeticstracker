import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDiabetesDataComponent } from './user-diabetes-data.component';

describe('UserDiabetesDataComponent', () => {
  let component: UserDiabetesDataComponent;
  let fixture: ComponentFixture<UserDiabetesDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserDiabetesDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDiabetesDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
