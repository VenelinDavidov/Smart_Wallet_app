package app.web;

import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.UserEditRequest;
import app.web.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;




    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }




// Edit profile user
    @GetMapping("/{id}/profile")
    public ModelAndView getProfileMenu (@PathVariable UUID id){

        User user = userService.getById (id);

        ModelAndView modelAndView = new ModelAndView ();
        modelAndView.addObject ("user", user);
        modelAndView.setViewName ("profile-menu");
        modelAndView.addObject ("userEditRequest", DtoMapper.mapUserToUserEditRequest(user));

        return modelAndView;
    }



    @PutMapping("/{id}/profile")
    public String updateUserProfile (@PathVariable UUID id, UserEditRequest userEditRequest){

        userService.editUserDetails(id, userEditRequest);

        return "redirect:/home";
    }
}