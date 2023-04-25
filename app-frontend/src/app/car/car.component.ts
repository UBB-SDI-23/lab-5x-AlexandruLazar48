import { Component, OnInit } from '@angular/core';
import { CarService } from './car.service';
import { Car } from './car';
import { HttpErrorResponse } from '@angular/common/http';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {
  public cars: Car[] = [];
  displayAddEditModal = false;
  selectedCar: any = null;

  constructor (private carService: CarService, 
    private confirmationService: ConfirmationService,
    private messageService: MessageService) {}

  ngOnInit() {
    this.getCars();
  }

  public getCars(): void {
    this.carService.getCars().subscribe(
      (response: Car[]) => {
        this.cars = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  showAddModal() {
    this.displayAddEditModal = true;
    this.selectedCar = null;
  }

  hideAddModal(isClosed: boolean) {
    this.displayAddEditModal = !isClosed;
  }

  saveCarToList(newData: any) {
    if (this.selectedCar && newData.id === this.selectedCar.id) {
      const carIndex = this.cars.findIndex(data => data.id === newData.id);
      this.cars[carIndex] = newData;
    } else {
      this.cars.unshift(newData);
    }

    //this.getCarList();
  }

  showEditModal(car: Car) {
    this.displayAddEditModal = true;
    this.selectedCar = car;
  }

  deleteCar(car: Car) {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete this car?',
      accept: () => {
        //Actual logic to perform a confirmation
        this.carService.deleteCar(car.id).subscribe(
          response => {
            //this.getCarList();
            this.cars = this.cars.filter(data => data.id !== car.id);
            this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Deleted Successfully' });
          },
          error => {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: error });
          }
        )
      }
    });
  }
}
