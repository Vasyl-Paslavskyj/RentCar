package myProject.controller;

import myProject.models.*;
import myProject.service.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Vasj on 11.08.2016.
 */
public class Start {
    private final static Scanner SC = new Scanner(System.in);
    static final ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");
    static final ClassOfCarService serviceClassOfCar = context.getBean(ClassOfCarService.class);
    static final StatusCarService serviceStatusCar = context.getBean(StatusCarService.class);
    static final ModelOfCarService serviseModelOfCar = context.getBean(ModelOfCarService.class);
    static final CarService serviseCar = context.getBean(CarService.class);
    static final UserService serviseUser = context.getBean(UserService.class);
    static final CustomService serviseCustom = context.getBean(CustomService.class);
    static final AccidentService serviseAccident = context.getBean(AccidentService.class);

    public static void main(String[] args){
        boolean isRun = true;
        while (isRun) {
            System.out.println("1) Add StatusCar");
            System.out.println("2) Find StatusCar By Status");
            System.out.println("3) Find All StatusCar");
            System.out.println("4) Delete StatusCar By Status");
            System.out.println("5) Add ClassOfCar");
            System.out.println("6) Find ClassOfCar By TypeClassOfCar");
            System.out.println("7) Find All ClassOfCar");
            System.out.println("8) Delete ClassOfCar By TypeClassOfCar");
            System.out.println("9) Update Price By TypeClassOfCar");
            System.out.println("10) Add ModelOfCar");
            System.out.println("11) Find ModelOfCar By TypeClassOfCar AND TypeModelCar");
            System.out.println("12) Find All ModelOfCar");
            System.out.println("13) Add Car");
            System.out.println("14) Get All Cars");
            System.out.println("15) Get Car By RegistrationNamber");
            System.out.println("16) Get Car By Type ClassOfCar And TypeModelCar And RegistrationNamber");
            System.out.println("17) Get All Cars By Price");
            System.out.println("18) Get All Cars By TypeClassOfCar");
            System.out.println("19) Delete Car By RegistrationNamber");
            System.out.println("20) Get All Cars That Was Ordered One User");
            System.out.println("21) Get All Cars By Status");
            System.out.println("22) Add User");
            System.out.println("23) Get All Users");
            System.out.println("24) Get User By Name And Passport");
            System.out.println("25) Delete User By Name And Passport");
            System.out.println("26) Get Users By Driving Experience");
            System.out.println("27) Get Users By Count Orders");
            System.out.println("28) Update User By Count Orders");
            System.out.println("29) Add Custom");
            System.out.println("30) Get All Customs");
            System.out.println("31) Get SUM (cost) By One User");
            System.out.println("32) Get Customs By Driavar Name And Passport And Car RegistrationNamber");
            System.out.println("33) Add Accident");
            System.out.println("34) Get All Accidents");
            System.out.println("35) Get List Accidents For One Car");
            System.out.println("36) Get List Accidents For One User");
            System.out.println("999) ????? ? ????????");
            switch (SC.nextInt()){
                case 1:
                    addStatusCar();
                    break;
                case 2:
                    getStatusCarByStatus();
                    break;
                case 3:
                    getAllStatusCar();
                    break;
                case 4:
                    deleteStatusCarByStatus();
                case 5:
                    addClassOfCar();
                    break;
                case 6:
                    getClassOfCarByTypeClassOfCar();
                    break;
                case 7:
                    getAllClassOfCar();
                    break;
                case 8:
                    deleteClassOfCarByTypeClassOfCar();
                    break;
                case 9:
                    updatePriceByTypeClassOfCar();
                    break;
                case 10:
                    addModelOfCar();
                    break;
                case 11:
                    getModelOfCarByClassOfCarAndTypeModelCar();
                    break;
                case 12:
                    getAllModelOfCar();
                    break;
                case 13:
                    addCar();
                    break;
                case 14:
                    getAllCars();
                    break;
                case 15:
                    getByRegistrationNamber();
                    break;
                case 16:
                    getByTypeClassOfCarAndTypeModelCarAndRegistrationNamber();
                    break;
                case 17:
                    getAllCarsByPrice();
                    break;
                case 18:
                    getAllCarsByTypeClassOfCar();
                    break;
                case 19:
                    deleteCarByRegistrationNamber();
                    break;
                case 20:
                    getAllCarsThatWasOrderedOneUser();
                    break;
                case 21:
                    getAllCarsByStatus();
                    break;
                case 22:
                    addUser();
                    break;
                case 23:
                    getAllUsers();
                    break;
                case 24:
                    getUserByNameAndPassport();
                    break;
                case 25:
                    deleteUserByNameAndPassport();
                    break;
                case 26:
                    getUsersByDriving_experience();
                    break;
                case 27:
                    getUsersByCount_orders();
                    break;
                case 28:
                    updateUserByCountOrders();
                    break;
                case 29:
                    addCustom();
                    break;
                case 30:
                    getAllCustoms();
                    break;
                case 31:
                    getCostByDriavarNameAndPpassport();
                    break;
                case 32:
                    findByDriavarNameAndPassportAndCarRegistrationNamber();
                    break;
                case 33:
                    addAccident();
                    break;
                case 34:
                    getAllAccidents();
                    break;
                case 35:
                    getAllAccidentsByRegistrationNambe();
                    break;
                case 36:
                    getAllAccidentsByDriavarNameAndPassport();
                    break;
                case 999:
                    isRun=false;
                    break;
                default:
                    break;
            }
        }
        context.close();
    }

    static void addStatusCar(){
        System.out.println("Enter Status");
        String statusString = SC.next();
        Status status = Status.valueOf(statusString.trim().toUpperCase());
        serviceStatusCar.save(status);
    }
    static void getStatusCarByStatus(){
        System.out.println("Enter Status");
        String statusString = SC.next();
        Status status = Status.valueOf(statusString.trim().toUpperCase());
        System.out.println(serviceStatusCar.findByStatus(status).toString());
    }
    static void getAllStatusCar(){
        List<StatusCar> statusCarList = serviceStatusCar.findAll();
        for (StatusCar sc : statusCarList){
            System.out.println(sc.toString());
        }
    }
    static void deleteStatusCarByStatus(){
        System.out.println("Enter Status");
        String statusString = SC.next();
        Status status = Status.valueOf(statusString.trim().toUpperCase());
        serviceStatusCar.delete(status);
    }

    static void addClassOfCar(){
        System.out.println("Enter TypeClassOfCar");
        String typeClassOfCarString = SC.next();
        TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
        System.out.println("Enter Price");
        int price = SC.nextInt();
        serviceClassOfCar.save(typeClassOfCar, price);
    }
    static void getClassOfCarByTypeClassOfCar(){
        System.out.println("Enter TypeClassOfCar");
        String typeClassOfCarString = SC.next();
        TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
        System.out.println(serviceClassOfCar.findByTypeClassOfCar(typeClassOfCar).toString());
    }
    static void getAllClassOfCar(){
        List<ClassOfCar> classOfCars = serviceClassOfCar.findAll();
        for (ClassOfCar cc : classOfCars){
            System.out.println(cc.toString());
        }
    }
    static void deleteClassOfCarByTypeClassOfCar(){
        System.out.println("Enter TypeClassOfCar");
        String typeClassOfCarString = SC.next();
        TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
        serviceClassOfCar.delete(typeClassOfCar);
    }
    static void updatePriceByTypeClassOfCar(){
        System.out.println("Enter TypeClassOfCar");
        String typeClassOfCarString = SC.next();
        TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
        System.out.println("Enter Price");
        int price = SC.nextInt();
        serviceClassOfCar.updatePriceByTypeClassOfCar(typeClassOfCar, price);
    }

    static void addModelOfCar() {
        System.out.println("Enter TypeClassOfCar");
        String typeClassOfCarString = SC.next();
        TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
        System.out.println("Enter TypeModelCar");
        String typeModelCarString = SC.next();
        TypeModelCar typeModelCar = TypeModelCar.valueOf(typeModelCarString.trim().toUpperCase());
        serviseModelOfCar.save(typeClassOfCar, typeModelCar);
    }
    static void getModelOfCarByClassOfCarAndTypeModelCar(){
        System.out.println("Enter TypeClassOfCar");
        String typeClassOfCarString = SC.next();
        TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
        System.out.println("Enter TypeModelCar");
        String typeModelCarString = SC.next();
        TypeModelCar typeModelCar = TypeModelCar.valueOf(typeModelCarString.trim().toUpperCase());
        ModelOfCar modelOfCar = serviseModelOfCar.findByClassOfCarAndTypeModelCar(typeClassOfCar, typeModelCar);
        System.out.println(modelOfCar.toString());
    }
    static void getAllModelOfCar(){
        List<ModelOfCar> modelOfCars = serviseModelOfCar.findAll();
        for (ModelOfCar mc : modelOfCars){
            System.out.println(mc.toString());
        }
    }

    static void addCar(){
        System.out.println("Enter TypeClassOfCar");
        String typeClassOfCarString = SC.next();
        TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
        System.out.println("Enter TypeModelCar");
        String typeModelCarString = SC.next();
        TypeModelCar typeModelCar = TypeModelCar.valueOf(typeModelCarString.trim().toUpperCase());
        System.out.println("Enter Status");
        String statusString = SC.next();
        Status status = Status.valueOf(statusString.trim().toUpperCase());
        System.out.println("Enter Registration Namber");
        String registrationNamber = SC.next();
        serviseCar.save(typeClassOfCar, typeModelCar, status, registrationNamber);
    }
    static void getAllCars(){
        List<Car> carList = serviseCar.findAll();
        for(Car car : carList){
            System.out.println(car.toString());
        }
    }
    static void getByRegistrationNamber(){
        System.out.println("Enter Registration Namber");
        String registrationNamber = SC.next();
        Car car = serviseCar.findByRegistrationNamber(registrationNamber);
        System.out.println(car.toString());
    }
    static void getByTypeClassOfCarAndTypeModelCarAndRegistrationNamber(){
        System.out.println("Enter TypeClassOfCar");
        String typeClassOfCarString = SC.next();
        TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
        System.out.println("Enter TypeModelCar");
        String typeModelCarString = SC.next();
        TypeModelCar typeModelCar = TypeModelCar.valueOf(typeModelCarString.trim().toUpperCase());
        System.out.println("Enter Registration Namber");
        String registrationNamber = SC.next();
        Car car = serviseCar.findByTypeClassOfCarAndTypeModelCarAndRegistrationNamber(typeClassOfCar, typeModelCar, registrationNamber);
        System.out.println(car.toString());
    }
    static void getAllCarsByPrice(){
        System.out.println("Enter lowe level of Price");
        Integer priceFirst = SC.nextInt();
        System.out.println("Enter upper level of Price");
        Integer priceSecond = SC.nextInt();

        List<Car> carList = serviseCar.findAllByPrice(priceFirst, priceSecond);
        System.out.println("All Cars By Price between (" + priceFirst + " and " + priceSecond + "):");
        for (Car car : carList)
            System.out.println(car.toString());
    }
    static void getAllCarsByTypeClassOfCar(){
        System.out.println("Enter TypeClassOfCar");
        String typeClassOfCarString = SC.next();
        TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
        List<Car> carList = serviseCar.findAllByTypeClassOfCar(typeClassOfCar);
        System.out.println("All Cars By Type Class Of Car --> " +typeClassOfCar);
        for (Car car : carList){
            System.out.println(car.toString());
        }
    }
    static void deleteCarByRegistrationNamber(){
        System.out.println("Enter Registration Namber");
        String registrationNamber = SC.next();
        serviseCar.delete(registrationNamber);
    }
    static void getAllCarsByStatus(){
        System.out.println("Enter Status");
        String statusString = SC.next();
        Status status = Status.valueOf(statusString.trim().toUpperCase());
        List<Car> carList = serviseCar.findAllByStatus(status);
        for (Car car : carList){
            System.out.println(car.toString());
        }
    }
    static void getAllCarsThatWasOrderedOneUser() {
        System.out.println("Enter driavar name");
        String name = SC.next();
        System.out.println("Enter driavar passport");
        String passport = SC.next();
        List<Car> carList = serviseCar.findAllByDriavarNameAndPassport(name, passport);
        for (Car car : carList){
            System.out.println(car.toString());
        }
    }

    static void addUser(){
        System.out.println("Enter name");
        String name = SC.next();
        System.out.println("Enter passport");
        String passport = SC.next();
        System.out.println("Enter driving_experience");
        Integer driving_experience = SC.nextInt();
        System.out.println("Enter count_orders");
        Integer count_orders = SC.nextInt();
        System.out.println("Enter address");
        String address = SC.next();
        serviseUser.save(name, passport, driving_experience, count_orders, address);
    }
    static void getAllUsers(){
        List<User> userList = serviseUser.findAll();
        for(User user : userList){
            System.out.println(user);
        }
    }
    static void getUserByNameAndPassport(){
        System.out.println("Enter name");
        String name = SC.next();
        System.out.println("Enter passport");
        String passport = SC.next();
        User user = serviseUser.findByNameAndPassport(name, passport);
        System.out.println(user);
    }
    static void deleteUserByNameAndPassport(){
        System.out.println("Enter name");
        String name = SC.next();
        System.out.println("Enter passport");
        String passport = SC.next();
        serviseUser.delete(name, passport);
    }
    static void getUsersByDriving_experience(){
        System.out.println("Enter driving_experience");
        Integer driving_experience = SC.nextInt();
        List<User> userList = serviseUser.findByDrivingExperience(driving_experience);
        for(User user : userList){
            System.out.println(user);
        }
    }
    static void getUsersByCount_orders(){
        System.out.println("Enter count_orders");
        Integer count_orders = SC.nextInt();
        List<User> userList = serviseUser.findByCountOrders(count_orders);
        for(User user : userList){
            System.out.println(user);
        }
    }
    static void updateUserByCountOrders(){
        System.out.println("Enter passport");
        String passport = SC.next();
        int count_orders = serviseCustom.findCountOrdersForUse(passport);
        serviseUser.updateUserByCountOrders(passport, count_orders);
    }

    static void addCustom(){
        System.out.println("Enter name");
        String name = SC.next();
        System.out.println("Enter passport");
        String passport = SC.next();
        System.out.println("Enter TypeClassOfCar");
        String typeClassOfCarString = SC.next();
        TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
        System.out.println("Enter TypeModelCar");
        String typeModelCarString = SC.next();
        TypeModelCar typeModelCar = TypeModelCar.valueOf(typeModelCarString.trim().toUpperCase());
        System.out.println("Enter Car Registration Namber");
        String registrationNamber = SC.next();

        System.out.println("Enter Year start");
        Integer yearStart = SC.nextInt();
        System.out.println("Enter Month start");
        Integer monthStart = SC.nextInt();
        System.out.println("Enter Day start");
        Integer dayStart = SC.nextInt();
        System.out.println("Enter Hour start");
        Integer hourStart = SC.nextInt();
        System.out.println("Enter Minute start");
        Integer minuteStart = SC.nextInt();
        LocalDateTime dateTimeStart = LocalDateTime.of(yearStart, monthStart, dayStart, hourStart, minuteStart);
        System.out.println("Enter City start");
        String cityStartString = SC.next();
        City cityStart = City.valueOf(cityStartString.trim().toUpperCase());

        System.out.println("Enter Year finish");
        Integer yearFinish = SC.nextInt();
        System.out.println("Enter Month finish");
        Integer monthFinish = SC.nextInt();
        System.out.println("Enter Day finish");
        Integer dayFinish = SC.nextInt();
        System.out.println("Enter Hour finish");
        Integer hourFinish = SC.nextInt();
        System.out.println("Enter Minute finish");
        Integer minuteFinish = SC.nextInt();
        LocalDateTime dateTimeFinish = LocalDateTime.of(yearFinish, monthFinish, dayFinish, hourFinish, minuteFinish);
        System.out.println("Enter City finish");
        String cityFinishString = SC.next();
        City cityFinish = City.valueOf(cityFinishString.trim().toUpperCase());

        System.out.println("Calculate road length");
        Integer rentalLength = SC.nextInt();
        System.out.println("Calculate cost");
        Integer cost = SC.nextInt();

        serviseCustom.save(name, passport, typeClassOfCar, typeModelCar, registrationNamber,
                dateTimeStart, cityStart, dateTimeFinish, cityFinish,
                rentalLength, cost);
    }
    static void getAllCustoms(){
        List<Custom> customList = serviseCustom.findAll();
        for (Custom custom : customList){
            custom.toString();
        }
    }
    static void getCostByDriavarNameAndPpassport(){
        System.out.println("Enter name");
        String name = SC.next();
        System.out.println("Enter passport");
        String passport = SC.next();
        int costs = serviseCustom.findCostByDriavarNameAndPpassport(name, passport);
        System.out.println(costs);
    }
    static void findByDriavarNameAndPassportAndCarRegistrationNamber(){
        System.out.println("Enter name");
        String name = SC.next();
        System.out.println("Enter passport");
        String passport = SC.next();
        System.out.println("Enter Car Registration Namber");
        String registrationNamber = SC.next();
        List<Custom> customList = serviseCustom.findByDriavarNameAndPassportAndCarRegistrationNamber(name, passport, registrationNamber);
    }

    static void addAccident(){
        System.out.println("Enter Date of Accident");
        System.out.println("Enter Year");
        Integer year = SC.nextInt();
        System.out.println("Enter Month");
        Integer month = SC.nextInt();
        System.out.println("Enter Day");
        Integer day = SC.nextInt();
        System.out.println("Enter Hour");
        Integer hour = SC.nextInt();
        System.out.println("Enter Minute");
        Integer minute = SC.nextInt();
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
        System.out.println("Enter Damage");
        String damage = SC.next();
        System.out.println("Enter Wastage");
        Integer wastage = SC.nextInt();

        System.out.println("Enter have many cars took part at accident");
        Integer countAccident = SC.nextInt();
        if(countAccident == 1){
            System.out.println("Enter driavar name");
            String name = SC.next();
            System.out.println("Enter driavar passport");
            String passport = SC.next();
            System.out.println("Enter Car Registration Namber");
            String registrationNamber = SC.next();

            serviseAccident.saveIfOneCar(dateTime, damage, wastage, name, passport, registrationNamber);
        }else{
            System.out.println("Enter first driavar name");
            String nameFirst = SC.next();
            System.out.println("Enter first driavar passport");
            String passportFirst = SC.next();
            System.out.println("Enter first Car Registration Namber");
            String registrationNamberFirst = SC.next();

            System.out.println("Enter second driavar name");
            String nameSecond = SC.next();
            System.out.println("Enter second driavar passport");
            String passportSecond = SC.next();
            System.out.println("Enter second Car Registration Namber");
            String registrationNamberSecond = SC.next();

            serviseAccident.saveIfTwoCars(dateTime, damage, wastage, nameFirst, passportFirst, registrationNamberFirst,
                    nameSecond, passportSecond, registrationNamberSecond);
        }
    }
    static void getAllAccidents(){
        List<Accident> accidentList = serviseAccident.findAll();
        for (Accident accident : accidentList){
            System.out.println(accident);
        }
    }
    static void getAllAccidentsByDriavarNameAndPassport(){
        System.out.println("Enter driavar name");
        String name = SC.next();
        System.out.println("Enter driavar passport");
        String passport = SC.next();
        List<Accident> accidentList = serviseAccident.findAllAccidentsByDriavarNameAndPassport(name, passport);
        for (Accident accident : accidentList){
            System.out.println(accident);
        }
    }
    static void getAllAccidentsByRegistrationNambe(){
        System.out.println("Enter Car Registration Namber");
        String registrationNamber = SC.next();
        List<Accident> accidentList = serviseAccident.findAllAccidentsByRegistrationNambe(registrationNamber);
        for (Accident accident : accidentList){
            System.out.println(accident);
        }
    }

}
