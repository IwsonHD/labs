import { Component, OnInit } from '@angular/core';
import { SpecieService } from 'src/app/specie/service/specie-service.service';
import { Specie } from '../../model/specie';

@Component({
  selector: 'app-specie-list',
  templateUrl: './specie-list.component.html',
  styleUrls: ['./specie-list.component.css']
})
export class SpecieListComponent implements OnInit {

  species: Specie[] = [];

  constructor(private specieService: SpecieService) { }

  ngOnInit(): void {
    this.getSpecies();
  }

  getSpecies(): void {
    this.specieService.getSpecies().subscribe((data: any) => {
      if (Array.isArray(data)) {
        this.species = data;
      } else {
        console.error('Data is not an array:', data);
      }
    }, error => {
      console.error('Error fetching species:', error);
    });
  }

  deleteSpecie(id: string): void {
    this.specieService.deleteSpecie(id).subscribe(() => {
      this.species = this.species.filter(specie => specie.id !== id);
    });
  }
}
