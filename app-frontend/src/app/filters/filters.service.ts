import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';
import { MostRentedByMake } from './most-rented-by-make-dto';

@Injectable({
    providedIn: 'root'
})
export class FilterService {
    private apiServerUrl = environment.apiBaseUrl;
    
    constructor(private http: HttpClient) {}

    public getMostRentedByMake(make: string): Observable<MostRentedByMake> {
        return this.http.get<MostRentedByMake>(`${this.apiServerUrl}/car/most-rented/${make}`);
    }
}