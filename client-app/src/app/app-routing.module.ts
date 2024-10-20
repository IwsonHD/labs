import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SpecieListComponent } from './specie/view/specie-list/specie-list.component';

const routes: Routes = [
  {path: "species", component: SpecieListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
