import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SpecieListComponent } from './specie/view/specie-list/specie-list.component';
import { SpecieService } from './specie/service/specie-service.service';
import { FormsModule } from '@angular/forms';
import { AddSpecieComponent } from './specie/view/add-specie/add-specie.component';
import { EditSpecieComponent } from './specie/view/edit-specie/edit-specie.component';
import { ViewSpecieComponent } from './specie/view/view-specie/view-specie.component';
import { AddAnimalComponent } from './animal/view/add-animal/add-animal.component';
import { EditAnimalComponent } from './animal/view/edit-animal/edit-animal.component';
import { ViewAnimalComponent } from './animal/view/view-animal/view-animal.component';

@NgModule({
  declarations: [
    AppComponent,
    SpecieListComponent,
    AddSpecieComponent,
    EditSpecieComponent,
    ViewSpecieComponent,
    AddAnimalComponent,
    EditAnimalComponent,
    ViewAnimalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    SpecieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
