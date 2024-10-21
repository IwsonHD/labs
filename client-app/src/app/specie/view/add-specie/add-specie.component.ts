import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SpecieService } from '../../service/specie-service.service';
import { Continents, DietType, Specie } from '../../model/specie';

@Component({
  selector: 'app-add-specie',
  templateUrl: './add-specie.component.html',
  styleUrls: ['./add-specie.component.css']
})
export class AddSpecieComponent {
  specie: Specie = { id: '', name: '', occurring: Continents.AFRICA, dietType: DietType.CARNIVORE };
  continents = Object.values(Continents);
  dietTypes = Object.values(DietType);

  constructor(private specieService: SpecieService, private router: Router) {}

  onSubmit(): void {
    this.specieService.addSpecie(this.specie).subscribe(() => {
      this.router.navigate(['/species']);
    });
  }
}
