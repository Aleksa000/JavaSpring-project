package com.example.demo.controllers;
import com.example.demo.models.*;
import com.example.demo.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    private  final IUserService userService;
    private  final IUCommentService commentService;
    private  final IUBookService bookService;
    private  final IUContactService contactService;
    private  final IUNewsService newsService;

    private final PasswordEncoder passwordEncoderService;

    public UserController(IUserService userService, IUCommentService commentService, IUBookService bookService, IUContactService contactService, IUNewsService newsService, PasswordEncoder passwordEncoderService){
        this.userService = userService;
        this.commentService = commentService;
        this.bookService = bookService;
        this.contactService = contactService;
        this.newsService = newsService;
        this.passwordEncoderService = passwordEncoderService;
    }

    @GetMapping("index")
    @CrossOrigin("*")
    public List<UserModel> Index()
    {
        return userService.Index();
    }

    @GetMapping("get/by/email")
    @CrossOrigin("*")
    public UserModel GetByEmail(String email){return userService.GetByEmail(email);}

    @GetMapping("get/user/product/by/email")
    @CrossOrigin("*")
    public UserWithProductsModel GetUserAndProductByEmail(String email){return userService.GetUserAndProducts(email);}

    @GetMapping("get/all/by/name")
    @CrossOrigin("*")
    public List<UserModel>GetAllByFirstName(String firstName){return userService.GetAllByFirstName(firstName);}

    @GetMapping("get/all/by/last")
    @CrossOrigin("*")
    public List<UserModel>GetAllByLastName(String lastName){return userService.GetAllByLastName(lastName);}

    @GetMapping("get/by/password")
    @CrossOrigin("*")
    public UserModel GetByPassword(String password){return userService.GetByPassword(password);}

    @GetMapping("get/by/address")
    @CrossOrigin("*")
    public UserModel GetByAddress(String address){return userService.GetByAddress(address);}

    @GetMapping("get/by/phone")
    @CrossOrigin("*")
    public UserModel GetByPhoneHQL(String phone){return userService.GetByPhoneHQL(phone);}

    @GetMapping("get/by/name")
    @CrossOrigin("*")
    public CommentModel GetByName(String name){return commentService.GetByName(name);}

    @GetMapping("get/by/subject")
    @CrossOrigin("*")
    public ContactModel GetBySubject(String subject){return contactService.GetBySubject(subject);}

    @GetMapping("get/by/message")
    @CrossOrigin("*")
    public ContactModel GetByMessage(String message){return contactService.GetByMessage(message);}

    @GetMapping("get/by/date")
    @CrossOrigin("*")
    public BookModel GetByDate(String date){return bookService.GetByDate(date);}
    @GetMapping("get/by/time")
    @CrossOrigin("*")
    public BookModel GetByTime(String time){return bookService.GetByTime(time);}
    @GetMapping("get/all/by/service")
    @CrossOrigin("*")
    public List<BookModel> GetAllByService(String service){return bookService.GetAllByService(service);}

    @GetMapping("get/by/pet")
    @CrossOrigin("*")
    public NewsModel GetByPet(String pet){return newsService.GetByPet(pet);}
    @GetMapping("get/by/mail")
    @CrossOrigin("*")
    public NewsModel GetByMail(String mail){return newsService.GetByMail(mail);}

    @GetMapping("get/user/by/id")
    @CrossOrigin("*")
    public Optional<UserModel> GetUser(Integer id) { return userService.GetUser(id); }

    @PostMapping("create")
    @CrossOrigin("*")
    public ResponseEntity<?> Insert(@RequestBody @Valid UserModel model, BindingResult result)
    {
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.OK);
        }
        model.setPassword(passwordEncoderService.encode(model.getPassword()));
        return new ResponseEntity<UserModel>(userService.CreateUser(model), HttpStatus.OK);

    }
    @PostMapping("comment")
    @CrossOrigin("*")
    public ResponseEntity<?> Insert(@RequestBody CommentModel model, BindingResult result)
    {
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.OK);}
        return new ResponseEntity<CommentModel>(commentService.CreateComment(model), HttpStatus.OK);

    }
    @PostMapping("contact")
    @CrossOrigin("*")
    public ResponseEntity<?> Insert(@RequestBody ContactModel model, BindingResult result)
    {
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.OK);
        }
        return new ResponseEntity<ContactModel>(contactService.CreateContact(model), HttpStatus.OK);

    }
    @PostMapping("book")
    @CrossOrigin("*")
    public ResponseEntity<?> Insert(@RequestBody BookModel model, BindingResult result)
    {
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.OK);
        }
        return new ResponseEntity<BookModel>(bookService.CreateBook(model), HttpStatus.OK);

    }

    @PostMapping("news")
    @CrossOrigin("*")
    public ResponseEntity<?> Insert(@RequestBody NewsModel model, BindingResult result)
    {
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.OK);
        }
        return new ResponseEntity<NewsModel>(newsService.CreateNews(model), HttpStatus.OK);

    }

    @GetMapping(path="/{email}/{phone}/{address}")
    public String printUserInfo(@PathVariable String email,
                                @PathVariable Long phone,
                               @PathVariable String address) {
        return "My email is "+email+". My phone is "+phone+". I am living in "+address;
    }

    @PostMapping("edit")
    @CrossOrigin("*")
    public ResponseEntity<?> Edit(@RequestBody @Valid UserModel model, BindingResult result) {
        if (result.hasErrors())
        {
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.OK);
        }
        return new ResponseEntity<UserModel>(userService.EditUser(model), HttpStatus.OK);
    }
    @PostMapping("delete")
    @CrossOrigin("*")
    public void Delete(@RequestBody UserModel model) {

        userService.DeleteUser(model.getId());


    }

}
