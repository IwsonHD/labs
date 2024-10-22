import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnimalService } from '../../service/animal-service.service';
import { Animal } from '../../model/animal';

@Component({
  selector: 'app-add-animal',
  templateUrl: './add-animal.component.html',
  styleUrls: ['./add-animal.component.css']
})
export class AddAnimalComponent implements OnInit {
  animal: Animal = { id: '', age: 0, weight: 0, specie_id: '' };
  specieId: string | null = '';

  constructor(
    private animalService: AnimalService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Get the specie ID from the path parameter
    this.specieId = this.route.snapshot.paramMap.get('id');
    if (!this.specieId) {
      this.router.navigate(['/species']);
      return;
    }


    if (this.specieId) {
      this.animal.specie_id = this.specieId;  // Assign the specie ID to the animal object
    }else{
      this.router.navigate([`/species/${this.specieId}`]);
    }
  }

  onSubmit(): void {
    if (this.specieId) {
      this.animalService.addAnimal(this.specieId, this.animal).subscribe(() => {
        // After successful addition, navigate back to the specie details view
        this.router.navigate([`/species/${this.specieId}`]);
      });
    } else {
      console.error('Specie ID is null');
    }
  }
}
