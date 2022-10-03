package com.bookstore.spring.boot.services.authenticated;

import com.bookstore.spring.boot.common.enums.Status;
import com.bookstore.spring.boot.common.exceptions.ApplicationException;
import com.bookstore.spring.boot.common.utils.RestAPIStatus;
import com.bookstore.spring.boot.common.utils.UniqueID;
import com.bookstore.spring.boot.controllers.model.request.AuthenticatedRequest;
import com.bookstore.spring.boot.controllers.model.response.AuthenticatedResponse;
import com.bookstore.spring.boot.entities.Authenticated;
import com.bookstore.spring.boot.entities.UserEntity;
import com.bookstore.spring.boot.repositories.AuthenticatedRepository;
import com.bookstore.spring.boot.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticatedServiceImpl implements AuthenticatedService{
    final AuthenticatedRepository authenticatedRepos;
    final UserRepository userRepos;

    public AuthenticatedServiceImpl(AuthenticatedRepository authenticatedRepos, UserRepository userRepos) {
        this.authenticatedRepos = authenticatedRepos;
        this.userRepos = userRepos;
    }

    @Override
    public Authenticated signIn(AuthenticatedRequest auth) {
        Optional<UserEntity> user = userRepos.findByUserName(auth.getUserName());
        if(!user.isPresent()){
            throw new ApplicationException(RestAPIStatus.NOT_FOUND, "User name not found");
        }

        if(!auth.getPassword().equals(user.get().getPasswordHash()) || user.get().getStatus() == Status.IN_ACTIVE){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Password invalid or account not verify");
        }
        Authenticated existsAuth = authenticatedRepos.getByUserName(auth.getUserName());
        if(existsAuth != null){
            Authenticated authenticated = existsAuth;
            authenticated.setStatus(Status.ACTIVE);

            return authenticatedRepos.save(authenticated);
        }
        Authenticated authenticated = new Authenticated();
        authenticated.setId(UniqueID.getUUID());
        authenticated.setUserId(user.get().getId());
        authenticated.setUserName(user.get().getUserName());
        authenticated.setStatus(Status.ACTIVE);
        authenticated.setRole(user.get().getRole());

        return authenticatedRepos.save(authenticated);
    }

    @Override
    public Authenticated signOut(@PathVariable String id) {
        authenticatedRepos.deleteById(id);
        return null;
    }

    @Override
    public List<Authenticated> authInfo() {
        return authenticatedRepos.findAll();
    }

    @Override
    public Authenticated getAuth(String id) {
        if(id.isEmpty() || id == null){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST);
        }
        return authenticatedRepos.getById(id);
    }
}
