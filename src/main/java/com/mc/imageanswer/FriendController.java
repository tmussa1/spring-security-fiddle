package com.mc.imageanswer;


import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private CloudinaryConfig cloudc;

    @Autowired
    FriendService friends;

    @Autowired
    AppRoleRepository roles;

    @RequestMapping("/")
    public String showFriends(Model model, Authentication authentication){
        model.addAttribute("myFriends", friends.getMyFriends(authentication));
        if(authentication.getAuthorities().contains(roles.findByRole("ADMIN"))){
            return "redirect:/friends/ranked";
        }
        return "/friends/add";
    }

    @RequestMapping("/add")
    public String addFriend(Model model){
        model.addAttribute("aFriend", new Friend());
        return "/friends/add";
    }

    @RequestMapping("/save")
    public String saveFriend(@ModelAttribute("aFriend") Friend theFriend, Authentication myDetails, MultipartHttpServletRequest request){
        MultipartFile f = request.getFile("file");
        if(f != null && !f.isEmpty()){
            try{
                Map uploadResult = cloudc.upload(f.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                String uploadName = (String) uploadResult.get("public_id");
                String transformedImage = cloudc.createUrl(uploadName);
                theFriend.setImage(transformedImage);
            } catch(IOException e){
                e.printStackTrace();
            }
        } else{
            theFriend.setImage("/img/friends.jpg");
        }
        friends.save(theFriend, myDetails);
        return "redirect:/friends/";
    }

    @RequestMapping("/ranked")
    public String showRankedFriends(Model model, Authentication authentication){
        model.addAttribute("myFriends", friends.rankMyFriends(authentication));
        return "/friends/list";
    }
}
