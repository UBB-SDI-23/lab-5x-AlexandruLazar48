package com.example.labmpp;

import com.example.labmpp.entities.Car;
import com.example.labmpp.entities.Customer;
import com.example.labmpp.entities.RentalTransaction;
import com.example.labmpp.repository.CarRepository;
import com.example.labmpp.services.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LabMppApplicationTests {

	@Autowired
	CarService carService;

	@MockBean
	private CarRepository carRepository;

	@Test
	void getMostRentedByMake() {
		Mockito.when(carRepository.findAll())
				.thenReturn(makeCarList());

		List<Car> orderedCars = carService.getCarsAboveAverageSortedByMake("Audi");
		assertEquals(2L, orderedCars.get(1).getId());
		assertEquals(0.5f, carService.getAverageRentalsByMake("Audi"));
	}

	@Test
	void getMostRentedByHp() {
		Mockito.when(carRepository.findAll())
				.thenReturn(makeCarList());

		List<Car> orderedCars = carService.getCarsAboveHpSortedByRentals(150);
		assertEquals(2L, orderedCars.get(0).getId());
		assertEquals(1f, carService.getAverageRentalsAboveHp(150));
	}

	private List<Car> makeCarList() {
		Car car1 = new Car();
		Car car2 = new Car();

		car1.setId(1L);
		car1.setCode(UUID.randomUUID());
		car1.setMake("Audi");
		car1.setModel("A4");
		car1.setHorsepower(140);
		car1.setYearOfFabrication(2006);
//		car1.setServiceHistory();
//		car1.setRentalTransactions();

		car2.setId(2L);
		car2.setCode(UUID.randomUUID());
		car2.setMake("Audi");
		car2.setModel("A6");
		car2.setHorsepower(180);
		car2.setYearOfFabrication(2006);
//		car2.setServiceHistory();
		List<RentalTransaction> rentalTransactions2 = new ArrayList<>();
		rentalTransactions2.add(makeNewRentalTransaction());
		car2.setRentalTransactions(rentalTransactions2);

		List<Car> cars = new ArrayList<>();
		cars.add(car1);
		cars.add(car2);

		return cars;
	}

	private RentalTransaction makeNewRentalTransaction() {
		RentalTransaction rentalTransaction = new RentalTransaction();

		rentalTransaction.setId(4L);
		rentalTransaction.setEndTime(100);
		rentalTransaction.setStartTime(50);
		rentalTransaction.setCustomer(makeNewCustomer());

		return rentalTransaction;
	}

	private Customer makeNewCustomer() {
		Customer customer = new Customer();
		customer.setId(3L);
		customer.setName("Andrei");
		customer.setAge(25);
		customer.setBudget(10000f);
		customer.setDescription("test");
		customer.setCnp("1990804170076");

		return customer;
	}
}
