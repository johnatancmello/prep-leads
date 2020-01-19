import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SecurityLoginFormComponent } from './form.component';

describe('FormComponent', () => {
  let component: SecurityLoginFormComponent;
  let fixture: ComponentFixture<SecurityLoginFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SecurityLoginFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SecurityLoginFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
