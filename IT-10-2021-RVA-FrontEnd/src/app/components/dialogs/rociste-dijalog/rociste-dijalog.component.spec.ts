import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RocisteDijalogComponent } from './rociste-dijalog.component';

describe('RocisteDijalogComponent', () => {
  let component: RocisteDijalogComponent;
  let fixture: ComponentFixture<RocisteDijalogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RocisteDijalogComponent]
    });
    fixture = TestBed.createComponent(RocisteDijalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
