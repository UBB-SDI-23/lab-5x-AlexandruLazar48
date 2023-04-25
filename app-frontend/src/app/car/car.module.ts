import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarComponent } from './car.component';
import { HttpClientModule } from '@angular/common/http';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { AddEditCarModule } from './add-edit-car/add-edit-car.module';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';



@NgModule({
  declarations: [
    CarComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    TableModule,
    ButtonModule,
    AddEditCarModule,
    ToastModule,
    ConfirmDialogModule
  ],
  exports: [
    CarComponent
  ],
  providers: [MessageService, ConfirmationService]
})
export class CarModule { }
