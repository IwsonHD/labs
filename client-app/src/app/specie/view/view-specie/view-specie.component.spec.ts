import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSpecieComponent } from './view-specie.component';

describe('ViewSpecieComponent', () => {
  let component: ViewSpecieComponent;
  let fixture: ComponentFixture<ViewSpecieComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewSpecieComponent]
    });
    fixture = TestBed.createComponent(ViewSpecieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
