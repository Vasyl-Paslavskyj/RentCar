package myProject.controller;

import myProject.dao.*;
import myProject.dao.implementation.*;
import myProject.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Vasj on 27.07.2016.
 */
public class Main {
    private final static Scanner SC = new Scanner(System.in);
    private final static EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
    private final static EntityManager em = factory.createEntityManager();
    private static final ClassOfCarDao CLASS_OF_CAR_DAO = new ClassOfCarDaoImpl(em);
    private static final StatusCarDao STATUS_CAR_DAO = new StatusCarDaoImpl(em);
    private static final ModelOfCarDao MODEL_OF_CAR_DAO = new ModelOfCarDaoImpl(em);
    private static final CarDao CAR_DAO = new CarDaoImpl(em);
    private static final UserDao USER_DAO = new UserDaoImpl(em);
    private static final CustomDao CUSTOM_DAO = new CustomDaoImpl(em);
    private static final AccidentDao ACCIDENT_DAO = new AccidentDaoImpl(em);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            System.out.println("1) Add Class Of Car");
            System.out.println("2) Add Status Car");
            System.out.println("3) Add Model Of Car");
            System.out.println("4) Add Car");
            System.out.println("5) Add User");
            System.out.println("6) Add Custom");
            System.out.println("7) Add Accident");
            System.out.println("8) Get List Accidents for one Car");
            System.out.println("9) Get All Cars That Are Locaed In City");
            System.out.println("10) Get List Accidents For One User");
            System.out.println("11) Get All Cars That Ordered One User");
            System.out.println("12) Get All Cars Group By Users");
            System.out.println("13->Criteria-) Get Users By Driving Experience");
            System.out.println("14->Criteria-) Get Users By Count Orders");
            System.out.println("15->Criteria-) Get All Cars By Type Class Of Car");
            System.out.println("16->Criteria-) Get All Cars By Price");
            System.out.println("17->Criteria-) Get All Cars That Ordered One User");
            System.out.println("18->Criteria-) Get All Cars That Are Locaed In City");
            System.out.println("19->Criteria-) Get List Accidents For One Car");
            System.out.println("20->Criteria-) Get List Accidents For One User");
            System.out.println("21->Criteria-) Update All Cars That Are Ordered");
            System.out.println("22->Criteria-) Get All Costs That Where Payed One User");
            System.out.println("30) ����� � ��������");
            switch (SC.nextInt()) {
                case 1:
                    addClassOfCar();
                    break;
                case 2:
                    addStatusCar();
                    break;
                case 3:
                    addModelOfCar();
                    break;
                case 4:
                    addCar();
                    break;
                case 5:
                    addUser();
                    break;
                case 6:
                    addCustom();
                    break;
                case 7:
                    addAccident();
                    break;
                case 8:
//                    getListAccidentsForOneCar();
                    break;
                case 9:
//                    getAllCarsThatAreLocaedInCity();
                    break;
                case 10:
//                    getListAccidentsForOneUser();
                    break;
                case 11:
//                    getAllCarsThatWasOrderedOneUser();
                    break;
                case 12:
//                    getAllCarsGroupByUsers();
                    break;
                case 13:
                    getUsersByDrivingExperience_Criteria();
                    break;
                case 14:
                    getUsersByCountOrders_Criteria();
                    break;
                case 15:
                    getAllCarsByTypeClassOfCar_Criteria();
                    break;
                case 16:
                    getAllCarsByPrice_Criteria();
                    break;
                case 17:
                    getAllCarsThatOrderedOneUser_Criteria();
                    break;
                case 18:
//                    getAllCarsThatAreLocaedInCity_Criteria();
                    break;
                case 19:
                    getListAccidentsForOneCar_Criteria();
                    break;
                case 20:
                    getListAccidentsForOneUser_Criteria();
                    break;
                case 21:
//                    updateAllCarsThatAreOrdered_Criteria();
                    break;
                case 22:
                    getCostThatPayedOneUser_Criteria();
                    break;
                case 30:
                    isRun = false;
                    break;
                default:
                    break;
            }
        }
        em.close();
        factory.close();
    }
        static void addClassOfCar(){
            ClassOfCar classOfCar = new ClassOfCar();
            System.out.println("Enter TypeClassOfCar");
            String typeClassOfCarString = SC.next();
            TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
            classOfCar.setTypeClassOfCar(typeClassOfCar);
            System.out.println("Enter Price");
            classOfCar.setPrice(SC.nextInt());
            CLASS_OF_CAR_DAO.save(classOfCar);
        }
        static void addModelOfCar(){
            ModelOfCar modelOfCar = new ModelOfCar();
            System.out.println("Enter TypeClassOfCar");
            String typeClassOfCarString = SC.next();
            TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
            System.out.println("Enter TypeModelCar");
            String typeModelCarString = SC.next();
            TypeModelCar typeModelCar = TypeModelCar.valueOf(typeModelCarString.trim().toUpperCase());
            modelOfCar.setTypeModelCar(typeModelCar);
            ClassOfCar classOfCar = null;
            try{
                classOfCar = CLASS_OF_CAR_DAO.findOneByTypeClassOfCar(typeClassOfCar);
                modelOfCar.setClassOfCar(classOfCar);
            }catch (NoResultException ex){
                ex.printStackTrace();
            }
            MODEL_OF_CAR_DAO.save(modelOfCar);
        }
        static void addStatusCar(){
            StatusCar statusCar = new StatusCar();
            System.out.println("Enter Status");
            String statusString = SC.next();
            Status status = Status.valueOf(statusString.trim().toUpperCase());
            statusCar.setStatus(status);
            STATUS_CAR_DAO.save(statusCar);
        }
        static void addCar(){
            Car car = new Car();
            System.out.println("Enter TypeClassOfCar");
            String typeClassOfCarString = SC.next();
            TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
            System.out.println("Enter TypeModelCar");
            String typeModelCarString = SC.next();
            TypeModelCar typeModelCar = TypeModelCar.valueOf(typeModelCarString.trim().toUpperCase());
            System.out.println("Enter Status");
            String statusString = SC.next();
            Status status = Status.valueOf(statusString.trim().toUpperCase());
            ModelOfCar modelOfCar = null;
            StatusCar statusCar = null;

            try{
                modelOfCar = MODEL_OF_CAR_DAO.findByTypeClassOfCarAndTypeModelCar(typeClassOfCar, typeModelCar);
            }catch (NoResultException ex){
                ex.printStackTrace();
            }

            try{
                statusCar = STATUS_CAR_DAO.findOneByStatus(status);
            }catch (NoResultException ex){
                ex.printStackTrace();
            }
            car.setModelOfCar(modelOfCar);
            car.setStatusCar(statusCar);
            System.out.println("Enter Registration Namber");
            String registrationNamber = SC.next();
            car.setRegistrationNamber(registrationNamber);

            CAR_DAO.save(car);
        }

        static void addUser(){
            User user = new User();
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
            user.setName(name);
            user.setPassport(passport);
            user.setDrivingExperience(driving_experience);
            user.setCount_orders(count_orders);
            user.setAddress(address);
            USER_DAO.save(user);
        }

        static void addAccident(){
            Accident accident = new Accident();
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
            accident.setDateTime(dateTime);
            accident.setDamage(damage);
            accident.setWastage(wastage);

            System.out.println("Enter have many cars took part at accident");
            Integer countAccident = SC.nextInt();
            if(countAccident == 1){
                System.out.println("Enter driavar name");
                String name = SC.next();
                System.out.println("Enter driavar passport");
                String passport = SC.next();
                System.out.println("Enter Car Registration Namber");
                String registrationNamber = SC.next();
                Custom custom = null;
                try{
                    custom = CUSTOM_DAO.findByDriavarNameAndPassportAndCarRegistrationNamber(name, passport, registrationNamber);
                }catch (NoResultException ex){
                    ex.printStackTrace();
                }
                accident.getCustomSet().add(custom);
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
                List<Custom> customList = null;

                try{
                    customList = CUSTOM_DAO.findAllByDriavarNameAndPassportAndCarRegistrationNamber(nameFirst, passportFirst, registrationNamberFirst,
                            nameSecond, passportSecond, registrationNamberSecond);
                }catch (NoResultException ex){
                    ex.printStackTrace();
                }
                accident.getCustomSet().addAll(customList);
            }

            ACCIDENT_DAO.save(accident);
        }

        static void addCustom(){
            Custom custom = new Custom();
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
            custom.setDateTimeStart(dateTimeStart);
            System.out.println("Enter City start");
            String cityStartString = SC.next();
            City cityStart = City.valueOf(cityStartString.trim().toUpperCase());
            custom.setCityStart(cityStart);

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
            custom.setDateTimeFinish(dateTimeFinish);
            System.out.println("Enter City finish");
            String cityFinishString = SC.next();
            City cityFinish = City.valueOf(cityFinishString.trim().toUpperCase());
            custom.setCityFinish(cityFinish);

            System.out.println("Enter TypeClassOfCar");
            String typeClassOfCarString = SC.next();
            TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());
            System.out.println("Enter TypeModelCar");
            String typeModelCarString = SC.next();
            TypeModelCar typeModelCar = TypeModelCar.valueOf(typeModelCarString.trim().toUpperCase());
            System.out.println("Enter Car Registration Namber");
            String registrationNamber = SC.next();
            Car car = null;

            try{
                car = CAR_DAO.findByTypeClassOfCarAndTypeModelCarAndRegistrationNamber(typeClassOfCar, typeModelCar, registrationNamber);
            }catch (NoResultException ex){
                ex.printStackTrace();
            }
            custom.setCar(car);
            System.out.println("Enter name");
            String name = SC.next();
            System.out.println("Enter passport");
            String passport = SC.next();
            User user = null;
            try{
                user = USER_DAO.findByNameAndPassport(name, passport);
            }catch (NoResultException ex){
                ex.printStackTrace();
            }
            custom.setUser(user);
            System.out.println("Calculate road length");
            Integer rentalLength = SC.nextInt();
            System.out.println("Calculate cost");
            Integer cost = SC.nextInt();
            custom.setRentalLength(rentalLength);
            custom.setCost(cost);
            CUSTOM_DAO.save(custom);
        }

//    static void getListAccidentsForOneCar(){
//        System.out.println("Enter Car Registration Namber");
//        String registrationNamber = SC.next();
//        List<Accident>  accidentList = em.createQuery("SELECT ac FROM Accident ac " +
//                "JOIN ac.customSet cuSet " +
//                "JOIN cuSet.car c " +
//                "WHERE c.registrationNamber=:registrationNamber", Accident.class)
//                .setParameter("registrationNamber", registrationNamber)
//                .getResultList();
//        System.out.println("All accidents with car that has this registration namber");
//        for(Accident accidents : accidentList){
//            System.out.println(accidents);
//        }
//    }
//
//    static void getAllCarsThatAreLocaedInCity(){
//        System.out.println("Enter City finish");
//        String cityFinishString = SC.next();
//        City cityFinish = City.valueOf(cityFinishString.trim().toUpperCase());
//        List<Car> cars = em.createQuery("SELECT c FROM Car c " +
//                "INNER JOIN c.customSet cuSet " +
//                "WHERE cuSet.id IN " +
//                "(SELECT cu.id FROM Custom cu " +
//                "WHERE cu.cityFinish=:cityFinish " +
//                "GROUP BY c.id " +
//                "ORDER BY cu.id DESC)", Car.class)
////                .setMaxResults(1)
//                .setParameter("cityFinish", cityFinish)
//                .getResultList();
//        System.out.println("Cars that are located in " +cityFinish);
//        for (Car car : cars){
//            System.out.println(car);
//        }
//    }
//
//    static void getListAccidentsForOneUser(){
//        System.out.println("Enter driavar name");
//        String name = SC.next();
//        System.out.println("Enter driavar passport");
//        String passport = SC.next();
//        List<Accident>  accidentList = em.createQuery("SELECT ac FROM Accident ac " +
//                "JOIN ac.customSet cuSet " +
//                "JOIN cuSet.user u " +
//                "WHERE u.name=:name AND u.passport=:passport", Accident.class)
//                .setParameter("name", name)
//                .setParameter("passport", passport)
//                .getResultList();
//        System.out.println("All accidents with user that has this name and passport");
//        for(Accident accidents : accidentList){
//            System.out.println(accidents);
//        }
//    }
//
//    static void getAllCarsThatWasOrderedOneUser(){
//        System.out.println("Enter driavar name");
//        String name = SC.next();
//        System.out.println("Enter driavar passport");
//        String passport = SC.next();
//        List<Car> carList = em.createQuery("SELECT c FROM Car c " +
//                "JOIN c.customSet cS " +
//                "JOIN cS.user u " +
//                "WHERE u.name=:name AND u.passport=:passport", Car.class)
//                .setParameter("name", name)
//                .setParameter("passport", passport)
//                .getResultList();
//        System.out.println("All Cars That Was Ordered By User -->" +name +" (passport: " +passport +")");
//        for(Car car : carList){
//            System.out.println(car);
//        }
//    }
//
//    static void getAllCarsGroupByUsers() {
//        System.out.println(
//                em.createQuery("SELECT distinct c FROM Car c " +
//                        "INNER JOIN FETCH c.customSet cuS " +
//                        "INNER JOIN FETCH cuS.user u " +
//                        "GROUP BY u.id " +
//                        "ORDER BY c.id DESC", Car.class));
//    }

    static void getUsersByDrivingExperience_Criteria(){
        System.out.println("Enter driving_experience");
        Integer driving_experience = SC.nextInt();

        List<User> userList = USER_DAO.findByDrivingExperience(driving_experience);

        System.out.println("All Users By Driving Experience ->" +driving_experience);
        for (User user : userList){
            System.out.println(user.toString());
        }
    }
    static void getUsersByCountOrders_Criteria(){
        System.out.println("Enter lowe level of count_orders");
        Integer count_orders_First = SC.nextInt();
        System.out.println("Enter upper level of count_orders");
        Integer count_orders_Second = SC.nextInt();

        List<User> userList = USER_DAO.findByCountOrders(count_orders_First, count_orders_Second);

        System.out.println("All Users By Count Orders between (" +count_orders_First +" and " +count_orders_Second +"):");
        for (User user : userList){
            System.out.println(user.toString());
        }
    }
    static void getAllCarsByTypeClassOfCar_Criteria(){
        System.out.println("Enter TypeClassOfCar");
        String typeClassOfCarString = SC.next();
        TypeClassOfCar typeClassOfCar = TypeClassOfCar.valueOf(typeClassOfCarString.trim().toUpperCase());

        List<Car> carList = CAR_DAO.findAllByTypeClassOfCar(typeClassOfCar);
        System.out.println("All Cars By Type Class Of Car --> " +typeClassOfCar);
        for (Car car : carList)
            System.out.println(car.toString());
    }
    static void getAllCarsByPrice_Criteria(){
        System.out.println("Enter lowe level of Price");
        Integer priceFirst = SC.nextInt();
        System.out.println("Enter upper level of Price");
        Integer priceSecond = SC.nextInt();

        List<Car> carList = CAR_DAO.findAllByPrice(priceFirst, priceSecond);
        System.out.println("All Cars By Price between (" +priceFirst +" and " +priceSecond +"):");
        for (Car car : carList)
            System.out.println(car.toString());
    }
    static void getAllCarsThatOrderedOneUser_Criteria(){
        System.out.println("Enter driavar name");
        String name = SC.next();
        System.out.println("Enter driavar passport");
        String passport = SC.next();

        List<Car> carList = CAR_DAO.findAllByDriavarNameAndPassport(name, passport);
        System.out.println("All Cars That Was Ordered By User (name: " +name +", passport: " +passport +")");
        for (Car car : carList)
            System.out.println(car.toString());
    }
//    static void getAllCarsThatAreLocaedInCity_Criteria(){
//        System.out.println("Enter City finish");
//        String cityFinishString = SC.next();
//        City cityFinish = City.valueOf(cityFinishString.trim().toUpperCase());
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
//        Root<Car> root = cq.from(Car.class);
//        cq.select(root);
//
//        Join<Car, Custom> carCustomJoin = root.join("customSet");
//        Expression<City> cityExpression = carCustomJoin.get("cityFinish");
//        Predicate cityPredicate = cb.equal(cityExpression, cityFinish);
//
//        cq.where(cityPredicate);
//        List<Car> carList = em.createQuery(cq).getResultList();
//        System.out.println("All Cars That Are Locaed In City " +cityFinish);
//        for (Car car : carList)
//            System.out.println(car.toString());
//    }
    static void getListAccidentsForOneCar_Criteria(){
        System.out.println("Enter Car Registration Namber");
        String registrationNamber = SC.next();

        List<Accident> accidentList = ACCIDENT_DAO.findAllByRegistrationNambe(registrationNamber);
        System.out.println("List Accidents For One Car (registration namber - " +registrationNamber +")");
        for (Accident accident : accidentList){
            System.out.println(accident);
        }
    }
    static void getListAccidentsForOneUser_Criteria(){
        System.out.println("Enter driavar name");
        String name = SC.next();
        System.out.println("Enter driavar passport");
        String passport = SC.next();

        List<Accident> accidentList = ACCIDENT_DAO.findAllByDriavarNameAndPassport(name, passport);
        System.out.println("List Accidents For One User (name: " +name +", passport: " +passport +")");
        for (Accident accident : accidentList){
            System.out.println(accident);
        }
    }
    static void getCostThatPayedOneUser_Criteria(){
        System.out.println("Enter driavar name");
        String name = SC.next();
        System.out.println("Enter driavar passport");
        String passport = SC.next();

        Integer allCost = CUSTOM_DAO.findCostByDriavarNameAndPpassport(name, passport);
        System.out.println("All Costs That Where Payed By User (name: " +name +", passport: " +passport +") --> " +allCost);
    }

//    static void updateAllCarsThatAreOrdered_Criteria(){//�� ������
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaUpdate cu = cb.createCriteriaUpdate(Car.class);
//        Root<Car> root = cu.from(Car.class);
//
//        Join<Car, Custom> carCustomJoin = root.join("customSet");
//        LocalDateTime today = LocalDateTime.of(2016, 8, 15, 12, 12);//.now;
//        Expression<LocalDateTime> localDateTimeExpression1 = carCustomJoin.get("dateTimeStart");
//        Expression<LocalDateTime> localDateTimeExpression2 = carCustomJoin.get("dateTimeFinish");
//        Predicate localDateTimePredicate1 = cb.greaterThanOrEqualTo(localDateTimeExpression1, today);
//        Predicate localDateTimePredicate2 = cb.lessThanOrEqualTo(localDateTimeExpression2, today);
//        Predicate allPredicate = cb.and(localDateTimePredicate1, localDateTimePredicate2);
//
//        cu.set(root.get("statusCar"), Status.ORDERED);
//        cu.where(allPredicate);
//        int count = em.createQuery(cu).executeUpdate();
//        System.out.println("In" +count +" cars Status Was Updated");
//        em.getTransaction().begin();
//        em.merge(root);
//        em.getTransaction().commit();
//    }
//    static void getAllUsersByDrivingExperience_Criteria(){
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery cq = cb.createQuery();
//        Root<User> root = cq.from(User.class);
//        cq.select(root);
//        Expression<String> expName = root.get("name");
//        Expression<Integer> expDE = root.get("driving_experience");
////        cq.multiselect(root.get("driving_experience"), root);
//        cq.groupBy(root.get("driving_experience"));
////        cq.orderBy(cb.asc(expDE), cb.asc(expName));
////        List<Object[]> userList = em.createQuery(cq).getResultList();
////
////        for (Object[] ob : userList){
////            for (int i=0; i<ob.length; i++){
////                System.out.print(ob[i].toString() +"  ");
////            }
////            System.out.println();
////        }
//        List<User> userList = em.createQuery(cq).getResultList();
//
//        System.out.println("All Users By Driving Experience And Count Orders");
//        for (User user : userList){
//            System.out.println(user.toString());
//        }
//    }
}
