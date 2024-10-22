import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAnimalComponent } from './view-animal.component';

describe('ViewAnimalComponent', () => {
  let component: ViewAnimalComponent;
  let fixture: ComponentFixture<ViewAnimalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewAnimalComponent]
    });
    fixture = TestBed.createComponent(ViewAnimalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
