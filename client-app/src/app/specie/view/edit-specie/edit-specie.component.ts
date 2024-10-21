import { Component, OnInit } from '@angular/core';
import { SpecieService } from 'src/app/specie/service/specie-service.service';
import { Specie, Continents, DietType } from '../../model/specie';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-edit-specie',
  templateUrl: './edit-specie.component.html',
  styleUrls: ['./edit-specie.component.css']
})
export class EditSpecieComponent implements OnInit {
  specie: Specie = { id: '', name: '', occurring: Continents.AFRICA, dietType: DietType.CARNIVORE };
  continents = Object.values(Continents);
  dietTypes = Object.values(DietType);

  constructor(
    private specieService: SpecieService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const specieId = this.route.snapshot.paramMap.get('id');  // Get specie ID from the URL
    this.specieService.getSpecieById(specieId!).subscribe((data: Specie) => {
      this.specie = data;  // Populate the form with the fetched specie data
    });
  }

  onSubmit(): void {
    this.specieService.updateSpecie(this.specie).subscribe(() => {
      this.router.navigate(['/species']);  // Redirect to the species list after editing
    });
  }
}