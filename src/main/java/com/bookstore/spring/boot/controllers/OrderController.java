package com.bookstore.spring.boot.controllers;

import com.bookstore.spring.boot.common.utils.RestAPIResponse;
import com.bookstore.spring.boot.controllers.model.request.OrderRequest;
import com.bookstore.spring.boot.controllers.model.response.OrderResponse;
import com.bookstore.spring.boot.controllers.model.response.PagingResponse;
import com.bookstore.spring.boot.services.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPath.ORDER_API)
public class OrderController extends AbstractBaseController{
    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestAPIResponse> getOder(@PathVariable String id) {
        return responseUtil.successResponse(new OrderResponse(orderService.getOrder(id)), "");
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestAPIResponse> updateTotal(@PathVariable String id) {
        return responseUtil.successResponse(new OrderResponse(orderService.getOrder(id)), "");
    }

    @GetMapping()
    public ResponseEntity<RestAPIResponse> getOders(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return responseUtil.successResponse(new PagingResponse(orderService.getAllOrders(), orderService.getOrderPages(pageNumber, pageSize)), "");
    }

    @PostMapping()
    public ResponseEntity<RestAPIResponse> newOrder(@RequestBody OrderRequest order) {
        return responseUtil.successResponse(new OrderResponse(orderService.createOrder(order)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestAPIResponse> deleteOrder(@PathVariable String id) {
        return responseUtil.successResponse(orderService.del(id));
    }
}
