import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UcesnikDijalogComponent } from './ucesnik-dijalog.component';

describe('UcesnikDijalogComponent', () => {
  let component: UcesnikDijalogComponent;
  let fixture: ComponentFixture<UcesnikDijalogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UcesnikDijalogComponent]
    });
    fixture = TestBed.createComponent(UcesnikDijalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
