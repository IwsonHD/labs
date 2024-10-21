import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditSpecieComponent } from './edit-specie.component';

describe('EditSpecieComponent', () => {
  let component: EditSpecieComponent;
  let fixture: ComponentFixture<EditSpecieComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditSpecieComponent]
    });
    fixture = TestBed.createComponent(EditSpecieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
