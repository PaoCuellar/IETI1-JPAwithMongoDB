package eci.ieti;

import eci.AppConfiguration;
import eci.ieti.data.CustomerRepository;
import eci.ieti.data.ProductRepository;
import eci.ieti.data.TodoRepository;
import eci.ieti.data.UserRepository;
import eci.ieti.data.model.Customer;
import eci.ieti.data.model.Product;
import eci.ieti.data.model.Todo;
import eci.ieti.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.swing.text.Document;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired private CustomerRepository customerRepository;

  @Autowired private ProductRepository productRepository;

  @Autowired private TodoRepository todoRepository;

  @Autowired private UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(AppConfiguration.class);
    MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

    customerRepository.deleteAll();

    customerRepository.save(new Customer("Alice", "Smith"));
    customerRepository.save(new Customer("Bob", "Marley"));
    customerRepository.save(new Customer("Jimmy", "Page"));
    customerRepository.save(new Customer("Freddy", "Mercury"));
    customerRepository.save(new Customer("Michael", "Jackson"));

    System.out.println("Customers found with findAll():");
    System.out.println("-------------------------------");

    customerRepository.findAll().stream().forEach(System.out::println);
    System.out.println();

    productRepository.deleteAll();

    productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
    productRepository.save(
        new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
    productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
    productRepository.save(
        new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
    productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
    productRepository.save(
        new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
    productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
    productRepository.save(
        new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
    productRepository.save(
        new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));

    System.out.println("Paginated search of products by criteria:");
    System.out.println("-------------------------------");

    productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
        .forEach(System.out::println);

    System.out.println();

    // Part 2, 6  New data
    // users
    userRepository.deleteAll();

    userRepository.save(new User(1, "Paola", "paola@mail.com"));
    userRepository.save(new User(2, "Andrea", "andrea@mail.com"));
    userRepository.save(new User(3, "Nicolas", "nicolas@mail.com"));
    userRepository.save(new User(4, "Juan", "juan@mail.com"));
    userRepository.save(new User(5, "Jimmy", "jimmy@mail.com"));
    userRepository.save(new User(6, "William", "william@mail.com"));
    userRepository.save(new User(7, "Daniela", "daniela@mail.com"));
    userRepository.save(new User(8, "Angelica", "angelica@mail.com"));
    userRepository.save(new User(9, "Leonardo", "leonardo@mail.com"));
    userRepository.save(new User(10, "Manuel", "manuel@mail.com"));

    System.out.println("Users found with findAll():");
    System.out.println("-------------------------------");
    userRepository.findAll().stream().forEach(System.out::println);
    System.out.println();

    // To-do

    todoRepository.deleteAll();

    todoRepository.save(
        Todo.builder()
            .id(1)
            .description("travel to Galapagos")
            .priority(7)
            .dueDate(LocalDate.of(2021, 1, 1))
            .responsible("paola@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(2)
            .description("travel to Spain")
            .priority(4)
            .dueDate(LocalDate.of(2021, 2, 2))
            .responsible("paola@mail.com")
            .status("Work in progress")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(3)
            .description("travel to Germany")
            .priority(8)
            .dueDate(LocalDate.of(2022, 6, 6))
            .responsible("nicolas@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(4)
            .description("travel to Mexico")
            .priority(9)
            .dueDate(LocalDate.of(2020, 8, 15))
            .responsible("paola@mail.com")
            .status("Cancel")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(5)
            .description("travel to China")
            .priority(10)
            .dueDate(LocalDate.of(2023, 10, 25))
            .responsible("nicolas@mail.com")
            .status("Work in progress")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(6)
            .description("travel to Scotland")
            .priority(1)
            .dueDate(LocalDate.of(2021, 10, 15))
            .responsible("paola@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(7)
            .description("travel to Austria")
            .priority(2)
            .dueDate(LocalDate.of(2021, 3, 23))
            .responsible("paola@mail.com")
            .status("Done")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(8)
            .description("travel to Galapagos")
            .priority(3)
            .dueDate(LocalDate.of(2020, 12, 7))
            .responsible("juan@mail.com")
            .status("Expired")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(9)
            .description("travel to India")
            .priority(4)
            .dueDate(LocalDate.of(2019, 5, 13))
            .responsible("andrea@mail.com")
            .status("Expired")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(10)
            .description("travel to Costa Rica")
            .priority(5)
            .dueDate(LocalDate.of(2021, 8, 3))
            .responsible("andrea@mail.com")
            .status("Work in progress")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(11)
            .description("travel to Japan")
            .priority(6)
            .dueDate(LocalDate.of(2021, 7, 15))
            .responsible("daniela@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(12)
            .description("Shop new computer")
            .priority(9)
            .dueDate(LocalDate.of(2021, 4, 1))
            .responsible("juan@mail.com")
            .status("In progress")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(1)
            .description("travel to Italy")
            .priority(6)
            .dueDate(LocalDate.of(2022, 12, 17))
            .responsible("jimmy@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(13)
            .description("Buy the new games")
            .priority(10)
            .dueDate(LocalDate.of(2021, 2, 3))
            .responsible("jimmy@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(14)
            .description("travel with family")
            .priority(8)
            .dueDate(LocalDate.of(2021, 6, 15))
            .responsible("manuel@mail.com")
            .status("In progress")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(15)
            .description("Dashboard project")
            .priority(10)
            .dueDate(LocalDate.of(2021, 3, 21))
            .responsible("manuel@mail.com")
            .status("Done")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(16)
            .description("travel to England")
            .priority(10)
            .dueDate(LocalDate.of(2022, 12, 8))
            .responsible("daniela@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(17)
            .description("travel to Portugal")
            .priority(6)
            .dueDate(LocalDate.of(2021, 5, 13))
            .responsible("william@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(18)
            .description("travel to Russia")
            .priority(7)
            .dueDate(LocalDate.of(2021, 7, 29))
            .responsible("angelica@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(19)
            .description("Adopt new cat")
            .priority(2)
            .dueDate(LocalDate.of(2021, 4, 15))
            .responsible("william@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(20)
            .description("travel to New Zeland")
            .priority(3)
            .dueDate(LocalDate.of(2021, 9, 8))
            .responsible("angelica@mail.com")
            .status("Work in progress")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(21)
            .description("Go out on a bike")
            .priority(1)
            .dueDate(LocalDate.of(2021, 4, 4))
            .responsible("angelica@mail.com")
            .status("Cancel")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(22)
            .description("Visit my sister")
            .priority(5)
            .dueDate(LocalDate.of(2021, 6, 18))
            .responsible("nicolas@mail.com")
            .status("In progress")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(23)
            .description("travel to Jungle")
            .priority(9)
            .dueDate(LocalDate.of(2021, 5, 12))
            .responsible("andrea@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(24)
            .description("travel to Turkey")
            .priority(4)
            .dueDate(LocalDate.of(2021, 1, 28))
            .responsible("leonardo@mail.com")
            .status("Pending")
            .build());

    todoRepository.save(
        Todo.builder()
            .id(25)
            .description("travel to Thailand")
            .priority(1)
            .dueDate(LocalDate.of(2022, 1, 1))
            .responsible("leonardo@mail.com")
            .status("Pending")
            .build());

    System.out.println();

    // Todos where the dueDate has expired
    Query query = new Query();
    query.addCriteria(Criteria.where("dueDate").lt(LocalDate.now()));
    List<Todo> task_Expired = mongoOperation.find(query, Todo.class);

    System.out.println("--------Expired tasks--------");
    System.out.println("Number of tasks expired = " + task_Expired.size());

    // Todos that are assigned to given user and have priority greater equal to 5
    query = new Query();
    query.addCriteria(
        Criteria.where("responsible")
            .is("paola@mail.com")
            .andOperator(Criteria.where("priority").gte(5)));
    List<Todo> tasksAssignedPriority = mongoOperation.find(query, Todo.class);

    System.out.println(
        "Number of tasks assigned to user paola and priority major 5 = "
            + tasksAssignedPriority.size());

    // Todos that contains a description with a length greater than 30 characters
    Aggregation agg2 =
        Aggregation.newAggregation(
            Aggregation.project(
                    "_id", "description", "priority", "dueDate", "responsible", "status")
                .andExpression("strLenBytes(description)")
                .as("strLength"),
            Aggregation.match(Criteria.where("strLength").gt(30)));
    List<Document> infoDescriptionLength =
        mongoOperation.aggregate(agg2, "todo", Document.class).getMappedResults();
    // Print result
    System.out.println(
        "Todos that have description grater than 30 characters = " + infoDescriptionLength.size());
  }
}
