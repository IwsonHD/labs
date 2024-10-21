import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Specie } from '../model/specie';
import { map }  from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class SpecieService {
  private apiUrl = 'http://localhost:8080/api/species';
  
  constructor(private http: HttpClient) { }

  getSpecies(): Observable<Specie[]> {
    return this.http.get<{species: Specie[]}>(`${this.apiUrl}`).pipe(
      map(response => response.species)
    );
  }

  deleteSpecie(id: string): Observable<any>{
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  addSpecie(specie: Specie): Observable<Specie>{
    return this.http.post<Specie>(`${this.apiUrl}`, specie);
  }

  getSpecieById(id: string): Observable<Specie>{
    return this.http.get<Specie>(`${this.apiUrl}/${id}`);
  }

  updateSpecie(specie: Specie): Observable<Specie>{ 
    return this.http.post<Specie>(`${this.apiUrl}/${specie.id}`, specie);
  }

}
