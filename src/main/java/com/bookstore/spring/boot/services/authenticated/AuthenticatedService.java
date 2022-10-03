package com.bookstore.spring.boot.services.authenticated;

import com.bookstore.spring.boot.controllers.model.request.AuthenticatedRequest;
import com.bookstore.spring.boot.entities.Authenticated;

import java.util.List;

public interface AuthenticatedService {
    Authenticated signIn(AuthenticatedRequest auth);

    Authenticated signOut(String id);

    List<Authenticated> authInfo();

    Authenticated getAuth(String id);
}
