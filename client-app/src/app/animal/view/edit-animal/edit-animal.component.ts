import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnimalService } from '../../service/animal-service.service';
import { Animal } from '../../model/animal';

@Component({
  selector: 'app-edit-animal',
  templateUrl: './edit-animal.component.html',
  styleUrls: ['./edit-animal.component.css']
})
export class EditAnimalComponent implements OnInit {
  animal: Animal = { id: '', age: 0, weight: 0, specie_id: '' };
  specieId: string | null = '';
  animalId: string | null = '';

  constructor(
    private animalService: AnimalService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Get the specie ID and animal ID from the path parameters
    this.specieId = this.route.snapshot.paramMap.get('specieId');
    this.animalId = this.route.snapshot.paramMap.get('animalId');
    
    // Fetch the animal details to pre-populate the form
    if (this.animalId) {
      this.animalService.getAnimalById(this.animalId).subscribe((animal) => {
        this.animal = animal;
      });
    }
  }

  onSubmit(): void {
    if (this.animalId) {
      this.animalService.updateAnimal(this.animal).subscribe(() => {
        // After successful update, navigate back to the specie details view
        this.router.navigate([`/species/${this.specieId}`]);
      });
    }
  }
}
