import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SpecieService } from '../../service/specie-service.service';
import { AnimalService } from '../../../animal/service/animal-service.service';
import { Continents, DietType, Specie } from '../../model/specie';
import { Animal } from '../../../animal/model/animal';

@Component({
  selector: 'app-view-specie',
  templateUrl: './view-specie.component.html',
  styleUrls: ['./view-specie.component.css']
})
export class ViewSpecieComponent implements OnInit {
  specie: Specie = { id: '', name: '', occurring: Continents.AFRICA, dietType: DietType.CARNIVORE };
  animals: Animal[] = [];

  constructor(
    private specieService: SpecieService,
    private animalService: AnimalService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const specieId = this.route.snapshot.paramMap.get('id');
    if(!specieId){ 
      this.router.navigate(['/species'])
      return;
    } 
    this.specieService.getSpecieById(specieId).subscribe(specie => {
      this.specie = specie;
    })

    this.animalService.getAnimalsBySpecie(specieId).subscribe(animals => {
      this.animals = animals;
    });
  }

  deleteAnimal(id: string): void {
    this.animalService.deleteAnimal(id).subscribe(() => {
      this.animals = this.animals.filter(animal => animal.id !== id);
    });
  }




}
