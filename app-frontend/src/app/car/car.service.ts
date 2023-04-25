import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Car } from './car';
import { environment } from 'src/environment/environment';

@Injectable({
    providedIn: 'root'
})
export class CarService {
    private apiServerUrl = environment.apiBaseUrl;
    
    constructor(private http: HttpClient) {}

    public getCars(): Observable<Car[]> {
        return this.http.get<Car[]>(`${this.apiServerUrl}/car`);
    }

    public addCar(car: any) {
        return this.http.post<any>(`${this.apiServerUrl}/car`, car);
    }

    public updateCar(car: any): Observable<any> {
        return this.http.put<any>(`${this.apiServerUrl}/car`, car);
    }

    public deleteCar(carId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/car/${carId}`);
    }
}