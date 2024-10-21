import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SpecieListComponent } from './specie/view/specie-list/specie-list.component';
import { AddSpecieComponent } from './specie/view/add-specie/add-specie.component';
import { EditSpecieComponent } from './specie/view/edit-specie/edit-specie.component';

const routes: Routes = [
  {path: "species", component: SpecieListComponent},
  {path: "species/add", component: AddSpecieComponent},
  { path: 'species/edit/:id', component: EditSpecieComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
