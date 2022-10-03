package com.bookstore.spring.boot.controllers;

import com.bookstore.spring.boot.common.utils.RestAPIResponse;
import com.bookstore.spring.boot.controllers.model.request.AuthenticatedRequest;
import com.bookstore.spring.boot.controllers.model.response.AuthenticatedResponse;
import com.bookstore.spring.boot.controllers.model.response.PagingResponse;
import com.bookstore.spring.boot.services.authenticated.AuthenticatedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiPath.AUTHENTICATE_API)
@Slf4j
public class AuthenticateController extends AbstractBaseController {

    final AuthenticatedService authenticatedService;

    public AuthenticateController(AuthenticatedService authenticatedService) {
        this.authenticatedService = authenticatedService;
    }

    /**
     * Login to the system
     *
     * @param
     * @return
     */
    @PostMapping()
    public ResponseEntity<RestAPIResponse> signIn(
            @RequestBody AuthenticatedRequest auth
    ) {
        return responseUtil.successResponse(new AuthenticatedResponse(authenticatedService.signIn(auth)), "");
    }

    @GetMapping(path = "/info")
    public ResponseEntity<RestAPIResponse> info(){
        return responseUtil.successResponse(authenticatedService.authInfo());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestAPIResponse> signOut(@PathVariable String id) {
        return responseUtil.successResponse(authenticatedService.signOut(id), "");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RestAPIResponse> auth(
            @PathVariable String id
    ){
        return responseUtil.successResponse(new AuthenticatedResponse(authenticatedService.getAuth(id)));
    }
}
