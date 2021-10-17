package interview.task.themarket;

import interview.task.themarket.models.entities.Item;
import interview.task.themarket.models.entities.User;
import interview.task.themarket.repository.ItemsRepository;
import interview.task.themarket.repository.UsersRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TheMarketApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // USERS
    @Autowired
    private UsersRepository usersRepository;

    private Long Test_User1_Id, Test_User2_Id;
    private String Test_User1_Name = "Ivan", Test_User2_Name = "Georgi";
    private Double Test_User1_Acc = 255d, Test_User2_Acc = 155d;

    // ITEMS
    @Autowired
    private ItemsRepository itemsRepository;

    private Long Test_Item1_Id, Test_Item2_Id;
    private String Test_Item1_Name = "Camera", Test_Item2_Name = "Laptop";


    // SET UP

    @BeforeEach
    public void setUp() {
        itemsRepository.deleteAll();
        usersRepository.deleteAll();

        User user1 = new User();
        user1.setUsername(Test_User1_Name).setAccount(Test_User1_Acc);
        user1 = usersRepository.save(user1);
        Test_User1_Id = user1.getId();

        User user2 = new User();
        user2.setUsername(Test_User2_Name).setAccount(Test_User2_Acc);
        user2 = usersRepository.save(user2);
        Test_User2_Id = user2.getId();


        Item item1 = new Item();
        item1.setName(Test_Item1_Name).setOwner(user1);
        item1 = itemsRepository.save(item1);
        Test_Item1_Id = item1.getId();

        Item item2 = new Item();
        item2.setName(Test_Item2_Name).setOwner(user2);
        item2 = itemsRepository.save(item2);
        Test_Item2_Id = item2.getId();
    }

    @AfterEach
    public void tearDown() {
        itemsRepository.deleteAll();
        usersRepository.deleteAll();
    }

    // TEST USERS

    @Test
    public void testUsers() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUserNotFound() throws Exception {
        this.mockMvc.perform(get("/users/6"))
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().equals("[]"));
    }

    @Test
    public void testUserFound() throws Exception {
        this.mockMvc.perform(get("/users/{id}", Test_User1_Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(Test_User1_Name)));
    }

    @Test
    public void testUserSize() throws Exception {
        this.mockMvc
                .perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


    // TEST ITEMS

    @Test
    public void testItems() throws Exception {
        this.mockMvc.perform(get("/items"))
                .andExpect(status().isOk());
    }

    @Test
    public void testItemFound() throws Exception {
        this.mockMvc.perform(get("/items/{id}", Test_Item1_Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(Test_Item1_Name)));
    }

    @Test
    public void testItemsSize() throws Exception {
        this.mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
