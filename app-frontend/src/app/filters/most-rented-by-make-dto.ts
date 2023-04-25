import { Car } from "../car/car";

export interface MostRentedByMake {
    cars: Car[];
    average: number;
}