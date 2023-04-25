import { Component, OnInit } from '@angular/core';
import { MostRentedByMake } from './most-rented-by-make-dto';
import { FilterService } from './filters.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Car } from '../car/car';


@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.css']
})
export class FiltersComponent implements OnInit {
  public carsFiltered: Car[] = [];
  public average: number = 0;
  public make: string = "";

  constructor(private filterService: FilterService) {}

  ngOnInit() {
  }

  public getMostRentedByMake(make: string): void {
    this.filterService.getMostRentedByMake(make).subscribe(
      (response: MostRentedByMake) => {
        this.carsFiltered = response.cars;
        this.average = response.average;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
}
