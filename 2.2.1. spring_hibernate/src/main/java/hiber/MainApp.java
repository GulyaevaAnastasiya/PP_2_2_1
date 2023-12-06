package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car kia = new Car(user1, "Kia Ceed", 200);
      Car bmw = new Car(user2,"BMW X5", 500);
      Car mazda = new Car(user3, "Mazda CX-5", 600);
      Car volga = new Car(user4,"Volga the best", 10000);

      user1.setCar(kia);
      user2.setCar(bmw);
      user3.setCar(mazda);
      user4.setCar(volga);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
         System.out.println();
      }

      User user = userService.getUserByModelAndSeries("BMW X5", 500);
      System.out.println(user);

      context.close();
   }
}
