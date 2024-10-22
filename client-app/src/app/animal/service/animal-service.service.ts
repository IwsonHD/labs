import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Animal } from '../model/animal';
import { map }  from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AnimalService {

  private apiUrl = 'http://localhost:8080/api/animals';
  
  constructor(private http: HttpClient) { }

  getAnimalsBySpecie(specieId: string): Observable<Animal[]> {
    return this.http.get<{animals: Animal[]}>(`${this.apiUrl}/specie/${specieId}`).pipe(
      map(response => response.animals)
    );
  }

  deleteAnimal(id: string): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  addAnimal(specieId: string, animal: Animal): Observable<Animal> {
    return this.http.post<Animal>(`${this.apiUrl}/specie/${specieId}`, animal);
  }

  getAnimalById(id: string): Observable<Animal> {
    return this.http.get<Animal>(`${this.apiUrl}/${id}`);
  }
  
  updateAnimal(animal: Animal): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${animal.id}`, animal);
  }

}
