package com.bookstore.spring.boot.controllers;

import com.bookstore.spring.boot.common.utils.RestAPIResponse;
import com.bookstore.spring.boot.controllers.model.request.OrderDetailRequest;
import com.bookstore.spring.boot.controllers.model.response.PagingResponse;
import com.bookstore.spring.boot.services.order.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.ORDER_DETAIL_API)
@Slf4j
public class OrderDetailController extends AbstractBaseController{
    final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping()
    public ResponseEntity<RestAPIResponse> createOrderDetail(
            @RequestBody List<OrderDetailRequest> orderDetailRequest
            ){
        return responseUtil.successResponse(orderDetailService.addOrder(orderDetailRequest), "");
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestAPIResponse> createOrderDetail(
            @PathVariable String id
    ){
        return responseUtil.successResponse(orderDetailService.getDetailByOrderId(id), "");
    }

    @GetMapping()
    public ResponseEntity<RestAPIResponse> createOrderDetails(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        return responseUtil.successResponse(new PagingResponse(orderDetailService.getDetails(), orderDetailService.getPages(pageNumber, pageSize)));
    }
}
