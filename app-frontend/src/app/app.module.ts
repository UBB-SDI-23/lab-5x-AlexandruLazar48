import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarService } from './car/car.service';
import { HttpClientModule } from '@angular/common/http';
import { CarModule } from './car/car.module';
import { FiltersModule } from './filters/filters.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CarModule,
    FiltersModule
  ],
  providers: [CarService],
  bootstrap: [AppComponent]
})
export class AppModule { }
