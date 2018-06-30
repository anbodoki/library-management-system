package com.lms.application.controller.security;

import com.lms.application.security.PermissionCheck;
import com.lms.common.dto.request.GeneralFilteringRequest;
import com.lms.common.dto.request.UserFilteringRequest;
import com.lms.common.dto.response.ActionResponse;
import com.lms.common.dto.response.ActionResponseWithData;
import com.lms.common.dto.response.ListResult;
import com.lms.common.dto.security.UserDTO;
import com.lms.security.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/security/user-api/")
@PermissionCheck
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "quick-find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody GeneralFilteringRequest generalFilteringRequest) throws Exception {
        ListResult<UserDTO> result = userService.find(generalFilteringRequest.getQuery(), generalFilteringRequest.getLimit(), generalFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @PostMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse find(@RequestBody UserFilteringRequest userFilteringRequest) throws Exception {
        ListResult<UserDTO> result = userService.find(userFilteringRequest.getId(), userFilteringRequest.getUsername(), userFilteringRequest.getFirstName(),
                userFilteringRequest.getLastName(), userFilteringRequest.getEmail(), userFilteringRequest.getPhone(), userFilteringRequest.getActive(),
                userFilteringRequest.getFromCreationDate(), userFilteringRequest.getToCreationDate(),
                userFilteringRequest.getFromModificationDate(), userFilteringRequest.getToModificationDate(), userFilteringRequest.getRoleId(),
                userFilteringRequest.getLimit(), userFilteringRequest.getOffset());
        return new ActionResponseWithData<>(result, true);
    }

    @GetMapping(value = "authorized-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionResponse getAuthorizedUser() throws Exception {
        return new ActionResponseWithData<>(userService.getAuthorizedUser(), true);
    }

    @PostMapping(value = "update")
    @PermissionCheck
    public ActionResponse updateUser(@RequestBody @Validated UserDTO user) throws Exception {
        return new ActionResponseWithData<>(userService.updateUser(user), true);
    }

    @PostMapping(value = "save")
    @PermissionCheck
    public ActionResponse insertUser(@RequestBody @Validated UserDTO user) throws Exception {
        return new ActionResponseWithData<>(userService.saveUser(user), true);
    }

    @GetMapping(value = "find-by-username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse findByUsername(@PathVariable String username) throws Exception {
        return new ActionResponseWithData<>(userService.findByUsername(username), true);
    }

    @DeleteMapping(value = "user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermissionCheck
    public ActionResponse deleteCustomer(@PathVariable long id) throws Exception {
        userService.deleteUser(id);
        return new ActionResponse(true);
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ActionResponse logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ActionResponse(true);
    }
}