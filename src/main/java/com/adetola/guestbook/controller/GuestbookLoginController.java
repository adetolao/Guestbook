package com.adetola.guestbook.controller;

import com.adetola.guestbook.entity.GuestbookUser;
import com.adetola.guestbook.service.GuestbookLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class GuestbookLoginController {
    @Autowired
    private GuestbookLoginService guestbookLoginService;

    public GuestbookLoginController(GuestbookLoginService userService) {
        super();
        this.guestbookLoginService = userService;
    }

    // build create GuestbookUser REST API
    @PostMapping("/addUser")
    public ResponseEntity<GuestbookUser> saveUser(@RequestBody GuestbookUser user){
        if (guestbookLoginService.checkUserExist(user)){
            return new ResponseEntity<GuestbookUser>(HttpStatus.BAD_REQUEST);
        }

//        user.setAccessLevel("user");
        return new ResponseEntity<GuestbookUser>(guestbookLoginService.saveUser(user), HttpStatus.CREATED);
    }

    // build get all Users REST API
    @GetMapping("/getUsers")
    public ResponseEntity<List<GuestbookUser>> getAllUsers(){
        return new ResponseEntity<List<GuestbookUser>>(guestbookLoginService.getAllUsers(), HttpStatus.CREATED);
    }

    /* build update GuestbookUser REST API
     ** http://localhost:8080/guestbook/getUser/id
     */
    @GetMapping("/getUser/{id}")
    public ResponseEntity<GuestbookUser> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<GuestbookUser>(guestbookLoginService.getUserById(id), HttpStatus.OK);
    }

    /* build update GuestbookUser REST API
     ** http://localhost:8080/guestbook/updUser/username
     */
    @PutMapping(value="/updUser/{id}")
    public ResponseEntity<GuestbookUser> updateUser(@PathVariable("id") Long id, @RequestBody GuestbookUser User){
        return new ResponseEntity<GuestbookUser>(guestbookLoginService.updateUser(User, id), HttpStatus.OK);
    }

    /* build update GuestbookUser REST API
     ** http://localhost:8080/guestbook/updUser/id
     */
    @PutMapping(value="/changeUserPrivilege/adminUserId/{id}")
    public ResponseEntity<GuestbookUser> changeUserPrivilege(@PathVariable("adminUserId") Long adminId,
                                                             @PathVariable("id") Long id,
                                                             @RequestBody GuestbookUser GuestbookUser){
        return new ResponseEntity<GuestbookUser>(guestbookLoginService.changeUserPrivilege(GuestbookUser, adminId, id), HttpStatus.OK);
    }

    /* build delete GuestbookUser REST API
     ** http://localhost:8080/guestbook/delUser/id
     */
    @DeleteMapping(value="/delUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") Long id){

        // delete GuestbookUser from DB
        guestbookLoginService.deleteUser(id);

        return new ResponseEntity<String>("GuestbookUser deleted successfully!.", HttpStatus.OK);
    }

}
