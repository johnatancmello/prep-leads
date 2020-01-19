import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SecurityLoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: SecurityLoginComponent;
  let fixture: ComponentFixture<SecurityLoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SecurityLoginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SecurityLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
