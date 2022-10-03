package com.bookstore.spring.boot.services.order;

import com.bookstore.spring.boot.common.exceptions.ApplicationException;
import com.bookstore.spring.boot.common.utils.RestAPIStatus;
import com.bookstore.spring.boot.common.utils.UniqueID;
import com.bookstore.spring.boot.controllers.model.request.OrderDetailRequest;
import com.bookstore.spring.boot.controllers.model.response.Obj;
import com.bookstore.spring.boot.entities.OrderDetail;
import com.bookstore.spring.boot.entities.ProductEntity;
import com.bookstore.spring.boot.repositories.OrderDetailRepository;
import com.bookstore.spring.boot.repositories.OrderRepository;
import com.bookstore.spring.boot.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    final OrderDetailRepository orderDetailRepository;
    final OrderRepository order;
    final ProductRepository product;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, OrderRepository order, ProductRepository product) {
        this.orderDetailRepository = orderDetailRepository;
        this.order = order;
        this.product = product;
    }

    @Override
    public List<OrderDetail> addOrder(List<OrderDetailRequest> orderDetails) {
        ArrayList<OrderDetail> orderDetailList = new ArrayList<>();

        for (OrderDetailRequest detail : orderDetails) {
            try{
                if(!order.existsById(detail.getOrderId())){
                    throw new ApplicationException(RestAPIStatus.BAD_REQUEST);
                }

                if(!product.existsById(detail.getProductId())){
                    throw new ApplicationException(RestAPIStatus.BAD_REQUEST);
                }

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(UniqueID.getUUID());
                orderDetail.setOrderId(detail.getOrderId());
                orderDetail.setProductId(detail.getProductId());
                orderDetail.setQuantity(detail.getQuantity());

                orderDetailRepository.save(orderDetail);
                orderDetailList.add(orderDetail);
            }catch (Exception e){
                throw new ApplicationException(RestAPIStatus.BAD_REQUEST, e.getMessage());
            }
        }
        return orderDetailList;
    }

    @Override
    public List<Obj> getDetailByOrderId(String id) {
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderId(id);
        List<Obj> objList = new ArrayList<>();
        for (OrderDetail detail: orderDetailList) {
            ProductEntity productEntity = product.getById(detail.getProductId());
            Obj obj = new Obj();
            obj.setId(productEntity.getId());
            obj.setQuantity(detail.getQuantity());
            obj.setPrice(productEntity.getPrice());
            obj.setName(productEntity.getProductName());
            obj.setImage(productEntity.getProductImage());

            objList.add(obj);
        }
        return objList;
    }

    @Override
    public List<OrderDetail> getDetails() {
        return orderDetailRepository.findAll();
    }


    @Override
    public Page<OrderDetail> getPages(int pageNumber, int pageSize) {
        PageRequest page = PageRequest.of(pageNumber - 1, pageSize);
        return orderDetailRepository.findAll(page);
    }
}
