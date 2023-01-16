package com.example.demo.services;
import com.example.demo.entity.UserEntity;
import com.example.demo.models.*;
import com.example.demo.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service()
public class UserService implements IUserService{
    private IUserRepository userRepository;
    private ModelMapper mapper;///automatski generise
    private IUProductRepository userProductRepository;

    public UserService(IUserRepository userRepository,IUProductRepository userProductRepository,ModelMapper mapper){
        this.userRepository=userRepository;
        this.userProductRepository = userProductRepository;
        this.mapper = mapper;

    }
    @Override
    public List<UserModel> Index() {
        var usersFromDb = userRepository.findAll();
        var models = mapper.map(usersFromDb, new ArrayList<UserModel>().getClass());
        return models;
    }
    @Override
    public UserModel GetByEmail(String email) {
        return mapper.map(userRepository.findByEmail(email), UserModel.class);
    }
    @Override
    public UserModel GetByPassword(String password) {
        return mapper.map(userRepository.findByPassword(password), UserModel.class);
    }
    @Override
    public UserModel GetByAddress(String address) {
        return mapper.map(userRepository.findByAddress(address), UserModel.class);
    }
    @Override
    public UserModel GetByPhoneHQL(String phone) {
        return mapper.map(userRepository.findByPhoneHQL(phone), UserModel.class);
    }
    @Override
    public UserWithProductsModel GetUserAndProducts(String email) {
        return userProductRepository.findByEmail(email);
    }
    @Override
    public List<UserModel> GetAllByFirstName(String firstName) {
        return mapper.map(userRepository.findAllByFirstName(firstName), new ArrayList<UserModel>().getClass());
    }
    @Override
    public Optional<UserModel> GetUser(Integer id) {
        var userFromDb = userRepository.findById(id);

        if (userFromDb == null)
        {
            return null;
        }

        return mapper.map(userFromDb, (Type) UserModel.class);
    }
    @Override
    public List<UserModel> GetAllByLastName(String lastName) {
        return mapper.map(userRepository.findAllByLastName(lastName), new ArrayList<UserModel>().getClass());
    }
    @Override
    public UserModel CreateUser(UserModel user) {
        var createdUser = userRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map(createdUser, UserModel.class);
    }
    @Override
    public UserModel EditUser(UserModel user) {
        var userFromDb = userRepository.findById(user.getId());
        if (userFromDb !=null)
        {
            user.setPassword(userFromDb.get().getPassword());
        }

        var editUser = userRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map(editUser, UserModel.class);
    }
    @Override
    public void DeleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public  UserModel insertWithProduct(UserModel userModel, ProductModel productModel){
        var userResult = userRepository.save(mapper.map(userModel, UserEntity.class));
        productModel.setAuthorId(userResult.getId());
        var productResult = userProductRepository.save(new UserWithProductsModel());
        return mapper.map(userResult, UserModel.class);
    }
    ///koji specificira semantiku transakcija
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = mapper.map(userRepository.findByEmail(username), UserModel.class);

        if (user == null){
            throw new UsernameNotFoundException("User not found!");
        }

        List authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
