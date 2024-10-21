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

  ngOnInit(): void{
    this.getSpecies();
    console.log(this.species);
  }

  getSpecies(): void{
    this.specieService.getSpecies().subscribe((data: Specie[]) => {
      
      this.species = data;
    } )
  }

  deleteSpecie(id: string): void{
    this.specieService.deleteSpecie(id).subscribe(() => {
      this.species = this.species.filter(specie => specie.id !== id);
    });
  }
}
