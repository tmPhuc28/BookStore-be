package com.bookstore.spring.boot.controllers;

import com.bookstore.spring.boot.common.utils.RestAPIResponse;
import com.bookstore.spring.boot.controllers.model.request.CartRequest;
import com.bookstore.spring.boot.controllers.model.response.CartResponse;
import com.bookstore.spring.boot.controllers.model.response.PagingResponse;
import com.bookstore.spring.boot.repositories.CartRepository;
import com.bookstore.spring.boot.services.cart.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPath.CART_API)
@RestController
@Slf4j
public class CartController extends AbstractBaseController{
    final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping()
    public ResponseEntity<RestAPIResponse> addItem(
            @RequestBody CartRequest cartRequest) {
        return responseUtil.successResponse(cartService.addItem(cartRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestAPIResponse> updateQuantity(
            @PathVariable String id,
            @RequestBody CartRequest cartRequest) {
        return responseUtil.successResponse(cartService.updateItem(id, cartRequest));
    }

    @GetMapping()
    public ResponseEntity<RestAPIResponse> getAllItem(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return responseUtil.successResponse(new PagingResponse(cartService.getAllItem(), cartService.getPages(pageNumber, pageSize)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestAPIResponse> getAllItem(
            @PathVariable String id
    ) {
        return responseUtil.successResponse(cartService.getItem(id));
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<RestAPIResponse> getItemsByUserId(
            @PathVariable String id
    ) {
        return responseUtil.successResponse(cartService.getItemsByUserId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestAPIResponse> remove(
            @PathVariable String id
    ) {

        return responseUtil.successResponse(cartService.remove(id),"");
    }

    @DeleteMapping()
    public ResponseEntity<RestAPIResponse> removeAll() {

        return responseUtil.successResponse( cartService.removeAll(), "");
    }
}
