import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SpecieListComponent } from './specie/view/specie-list/specie-list.component';
import { AddSpecieComponent } from './specie/view/add-specie/add-specie.component';
import { EditSpecieComponent } from './specie/view/edit-specie/edit-specie.component';
import { ViewSpecieComponent } from './specie/view/view-specie/view-specie.component';
import { AddAnimalComponent } from './animal/view/add-animal/add-animal.component';
import { EditAnimalComponent } from './animal/view/edit-animal/edit-animal.component';
import { ViewAnimalComponent } from './animal/view/view-animal/view-animal.component';

const routes: Routes = [
  {path: "species", component: SpecieListComponent},
  {path: "species/add", component: AddSpecieComponent},
  {path: 'species/edit/:id', component: EditSpecieComponent},
  {path: "species/:id", component: ViewSpecieComponent},
  {path: "species/:id/add-animal", component: AddAnimalComponent},
  {path: "species/:specieId/edit-animal/:animalId", component: EditAnimalComponent},
  {path: "species/:specieId/animals/:animalId", component: ViewAnimalComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
