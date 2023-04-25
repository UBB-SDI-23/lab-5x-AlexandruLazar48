import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FiltersComponent } from './filters.component';
import { HttpClientModule } from '@angular/common/http';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    FiltersComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    TableModule,
    ButtonModule,
    ToastModule,
    FormsModule,
    ConfirmDialogModule  
  ],
  exports: [
    FiltersComponent
  ],
  providers: [MessageService, ConfirmationService]
})
export class FiltersModule { }
