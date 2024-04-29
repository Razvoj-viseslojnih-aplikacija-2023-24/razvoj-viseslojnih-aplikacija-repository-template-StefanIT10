import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SudDijalogComponent } from './sud-dijalog.component';

describe('SudDijalogComponent', () => {
  let component: SudDijalogComponent;
  let fixture: ComponentFixture<SudDijalogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SudDijalogComponent]
    });
    fixture = TestBed.createComponent(SudDijalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
