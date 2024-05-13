import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredmetDijalogComponent } from './predmet-dijalog.component';

describe('PredmetDijalogComponent', () => {
  let component: PredmetDijalogComponent;
  let fixture: ComponentFixture<PredmetDijalogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PredmetDijalogComponent]
    });
    fixture = TestBed.createComponent(PredmetDijalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
