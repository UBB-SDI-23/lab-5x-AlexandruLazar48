import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Car } from './car';

@Injectable({
    providedIn: 'root'
})
export class CarService {
    private apiServerUrl = '';
    
    constructor(private http: HttpClient) {}

    public getCars(): Observable<Car[]> {
        return this.http.get<Car[]>(`${this.apiServerUrl}/car`);
    }

    public addCar(car: Car): Observable<Car> {
        return this.http.post<Car>(`${this.apiServerUrl}/car`, car);
    }

    public updateCar(car: Car): Observable<Car> {
        return this.http.put<Car>(`${this.apiServerUrl}/car`, car);
    }

    public deleteCar(carId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/car/${carId}`);
    }
}