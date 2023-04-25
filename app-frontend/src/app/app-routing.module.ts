import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CarComponent } from './car/car.component';
import { FiltersComponent } from './filters/filters.component';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'car',
    component: CarComponent
  },
  {
    path: 'filters',
    component: FiltersComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
