import { TestBed } from '@angular/core/testing';

import { SpecieServiceService } from './specie-service.service';

describe('SpecieServiceService', () => {
  let service: SpecieServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpecieServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
