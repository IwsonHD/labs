import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AnimalService } from '../../service/animal-service.service';
import { Animal } from '../../model/animal';
import { Router } from '@angular/router';
import { Specie } from 'src/app/specie/model/specie';
import { SpecieService } from 'src/app/specie/service/specie-service.service';

@Component({
  selector: 'app-view-animal',
  templateUrl: './view-animal.component.html',
  styleUrls: ['./view-animal.component.css']
})
export class ViewAnimalComponent implements OnInit {
  animal: Animal | null = null;
  specie: Specie | null = null;
  specieId: string | null = '';
  animalId: string | null = '';

  constructor(
    private animalService: AnimalService,
    private specieService: SpecieService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.specieId = this.route.snapshot.paramMap.get('specieId');
    this.animalId = this.route.snapshot.paramMap.get('animalId');
    
    
    if (this.animalId && this.specieId) {
      this.animalService.getAnimalById(this.animalId).subscribe((animal) => {
        this.animal = animal;
      });
      this.specieService.getSpecieById(this.specieId).subscribe(specie => {
        this.specie = specie;
      });
    }else{
      this.router.navigate([`/species/${this.specieId}`]);
    }
  }
}
