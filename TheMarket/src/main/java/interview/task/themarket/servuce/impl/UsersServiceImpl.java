package interview.task.themarket.servuce.impl;

import interview.task.themarket.models.entities.User;
import interview.task.themarket.repository.UsersRepository;
import interview.task.themarket.servuce.UsersService;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User createUser(String username, Double account) {

        User user = usersRepository.findUserByNameNativeQ(username).orElse(null);
        if (user != null) {
            throw new IllegalStateException("User already exists");
        }

        var newUser = new User();
        newUser.setUsername(username).setAccount(account);
        usersRepository.save(newUser);

        return usersRepository.getById(newUser.getId());
    }

    @Override
    public User findUserById(Long ownerId) {
        return usersRepository.findUserById(ownerId).orElse(null);
    }

    @Override
    public Boolean increaseAccountBalance(Long userId, Double value) {
        var user = findUserById(userId);
        if (user == null) {
            return false;
        }

        var balance = user.getAccount();
        user.setAccount(balance + value);
        usersRepository.save(user);

        return true;
    }

    @Override
    public Boolean decreaseAccountBalance(Long userId, Double value) {
        var user = findUserById(userId);
        if (user == null) {
            return false;
        }
        var balance = user.getAccount();

        if (balance - value < 0) {
            return false;
        }

        user.setAccount(balance - value);
        usersRepository.save(user);

        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }
}
