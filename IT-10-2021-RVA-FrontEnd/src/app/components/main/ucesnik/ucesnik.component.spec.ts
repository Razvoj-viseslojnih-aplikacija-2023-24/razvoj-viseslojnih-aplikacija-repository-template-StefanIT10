import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UcesnikComponent } from './ucesnik.component';

describe('UcesnikComponent', () => {
  let component: UcesnikComponent;
  let fixture: ComponentFixture<UcesnikComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UcesnikComponent]
    });
    fixture = TestBed.createComponent(UcesnikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
