import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnSaleComponent } from './onsale.component';

describe('OnSaleComponent', () => {
  let component: OnSaleComponent;
  let fixture: ComponentFixture<OnSaleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OnSaleComponent ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnSaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
