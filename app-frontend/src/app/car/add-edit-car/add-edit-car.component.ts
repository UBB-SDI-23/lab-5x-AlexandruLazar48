import { Component, EventEmitter, Input, OnInit, Output, OnChanges, SimpleChanges } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { CarService } from '../car.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-add-edit-car',
  templateUrl: './add-edit-car.component.html',
  styleUrls: ['./add-edit-car.component.css']
})
export class AddEditCarComponent implements OnInit, OnChanges {

  @Input() displayAddEditModal: boolean = true;
  @Input() selectedCar: any = null;
  @Output() clickClose: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() clickAdd: EventEmitter<any> = new EventEmitter<any>();
  modalType = "Add";

  carForm = this.fb.group({
    id: [],
    code: [],
    make: ["", Validators.required],
    model: ["", Validators.required],
    value: ["", Validators.required],
    horsepower: ["", Validators.required],
    yearOfFabrication: ["", Validators.required]
  });

  constructor(private fb: FormBuilder, private carService: CarService,
    private messageService: MessageService) { }

  ngOnInit(): void {
  }
  
  ngOnChanges(): void {
    if (this.selectedCar) {
      this.modalType = 'Edit';
      this.carForm.patchValue(this.selectedCar);
    } else {
      this.carForm.reset();
      this.modalType = 'Add';
    }
  }

  closeModal() {
    this.carForm.reset();
    this.clickClose.emit(true);
  }

  addCar() {
    this.carService.addCar(this.carForm.value).subscribe(
      response => {
        this.clickAdd.emit(response);
        this.closeModal();
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Car added' });
      },
      error => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: error });
        console.log('Error occured');
      }
    )
  }

  editCar() {
    this.carService.updateCar(this.carForm.value).subscribe(
      response => {
        this.clickAdd.emit(response);
        this.closeModal();
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Car edited' });
      },
      error => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: error });
        console.log('Error occured');
      }
    )
  }

  addEditCar() {
    if (this.modalType == "Edit") {
      this.editCar();
    } else {
      this.addCar();
    }
  }

}