import ch.qos.logback.classic.spi.ILoggingEvent;
import com.github.orest.car_shop.exceptions.ZeroCarStorageException;
import com.github.orest.car_shop.model.Car;
import com.github.orest.car_shop.repository.CarRepository;
import com.github.orest.car_shop.service.CarService;
import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;
    //private  CarService carService = new CarService();

    @Mock
    public CarRepository carRepository;

    public static final List<Car> cars = new ArrayList();
    static
    {
        cars.add(new Car(1, "Mercedes", "A-Class", "Black", "no damage", 32500, 2));
        cars.add(new Car(2, "Mercedes", "C-Class", "Red", "left wing", 37000, 1));
        cars.add(new Car(3, "Mercedes", "E-Class", "Black", "no damage", 53000, 3));
    }
    static final List<Car> zeroCars = new ArrayList();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("UsersService class tests...");
        System.out.println(cars.size());
    }

    @Before
    public void setUp() {
        System.out.println("Running preconditions...");
        System.out.println("Running test...");
    }

    @Test
    public void whenGettingBrandThenReturnListWithBrand() {
        //carRepository.findAll();
        System.out.println(carService.getMessage("zero allCars"));
        //assertThat(carService.getBrand("Mercedes", cars).size(), is(3));
    }
/*
    @Test
    public void whenGettingNonExistingBrandThenReturnEmptyListWithBrand() throws Exception {
        assertThat(carService.getBrand("Audi", cars).size(), is(0));
    }

    @Test
    // This method return List of Cars from DB
    public void whenGettingAllCarsThenReturnIterableWithAllCars(){
        assertThat(carService.getAllCars(), is(equals(carRepository.findAll())));
    }

    @Test
    public void whenGettingCarsFromZeroStoraThenThrowZeroCarStorageException() throws Exception {
        thrown.expect(ZeroCarStorageException.class);
        thrown.expectMessage("Something went wrong, list of cars is: null or 0 length");
        carService.getBrand("Audi", zeroCars);
    }
*/
    @After
    public void afterMethod() {
        System.out.println("SomethingAfter");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Tests finished");
    }
}
