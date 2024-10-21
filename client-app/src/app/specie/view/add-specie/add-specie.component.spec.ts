import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSpecieComponent } from './add-specie.component';

describe('AddSpecieComponent', () => {
  let component: AddSpecieComponent;
  let fixture: ComponentFixture<AddSpecieComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddSpecieComponent]
    });
    fixture = TestBed.createComponent(AddSpecieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
