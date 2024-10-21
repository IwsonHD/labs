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

@NgModule({
  declarations: [
    AppComponent,
    SpecieListComponent,
    AddSpecieComponent,
    EditSpecieComponent
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
