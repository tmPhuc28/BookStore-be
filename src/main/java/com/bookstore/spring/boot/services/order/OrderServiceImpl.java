package com.bookstore.spring.boot.services.order;

import com.bookstore.spring.boot.common.exceptions.ApplicationException;
import com.bookstore.spring.boot.common.utils.RestAPIStatus;
import com.bookstore.spring.boot.common.utils.UniqueID;
import com.bookstore.spring.boot.controllers.model.request.OrderRequest;
import com.bookstore.spring.boot.entities.Authenticated;
import com.bookstore.spring.boot.entities.OrderDetail;
import com.bookstore.spring.boot.entities.OrderEntity;
import com.bookstore.spring.boot.entities.ProductEntity;
import com.bookstore.spring.boot.repositories.AuthenticatedRepository;
import com.bookstore.spring.boot.repositories.OrderDetailRepository;
import com.bookstore.spring.boot.repositories.OrderRepository;
import com.bookstore.spring.boot.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    final OrderRepository orderRepository;
    final OrderDetailRepository orderDetailRepository;
    final ProductRepository productRepository;
    final AuthenticatedRepository authenticatedRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, ProductRepository productRepository, AuthenticatedRepository authenticatedRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.authenticatedRepository = authenticatedRepository;
    }

    @Override
    public List<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Page<OrderEntity> getOrderPages(int pageNumber, int pageSize) {
        PageRequest page = PageRequest.of(pageNumber - 1, pageSize);
        return orderRepository.findAll(page);
    }

    @Override
    public OrderEntity createOrder(OrderRequest order) {
        if(!authenticatedRepository.existsById(order.getAuthToken())){
            System.out.println(order.getAuthToken() + "token error");
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "");
        }
        Authenticated auth = authenticatedRepository.getById(order.getAuthToken());
        OrderEntity orderEntity = new OrderEntity();
        if(order.getReceiveName().isEmpty() || order.getReceiveName() == null){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST);
        }


        if(order.getPhoneReceive().isEmpty() || order.getPhoneReceive() == null){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST);
        }

        orderEntity.setId(UniqueID.getUUID());
        orderEntity.setUserId(auth.getUserId());
        orderEntity.setReceiveName(order.getReceiveName());
        orderEntity.setPhoneReceive(order.getPhoneReceive());
        orderEntity.setAddressReceive(order.getAddressReceive());
        orderEntity.setTotal(order.getTotal());
        System.out.println(orderEntity);
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity updateOrder(String id, OrderRequest order) {
        if(!orderRepository.existsById(order.getAuthToken())){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "");
        }
        Authenticated auth = authenticatedRepository.getById(order.getAuthToken());
        OrderEntity orderEntity = orderRepository.getById(id);
        if(order.getReceiveName().isEmpty() || order.getReceiveName() == null){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST);
        }


        if(order.getPhoneReceive().isEmpty() || order.getPhoneReceive() == null){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST);
        }

        orderEntity.getId();
        orderEntity.setUserId(auth.getUserId());
        orderEntity.setReceiveName(order.getReceiveName());
        orderEntity.setPhoneReceive(order.getPhoneReceive());
        orderEntity.setAddressReceive(order.getAddressReceive());
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity updateTotal(String id) {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(id);
        OrderEntity order = orderRepository.getById(id);
        double total = 0;
        for (OrderDetail orderDetail : orderDetailList) {
            System.out.println(orderDetail.getProductId() + "product id");
            try {
                ProductEntity product = productRepository.getById(orderDetail.getProductId().trim());
                total += (product.getPrice() * orderDetail.getQuantity());
            } catch (Exception e) {
                throw new ApplicationException(RestAPIStatus.BAD_REQUEST, e.getMessage());
            }
        }
        order.setTotal(total);
        return orderRepository.save(order);
    }

    @Override
    public OrderEntity del(String id) {
        orderRepository.deleteById(id);
        return null;
    }

    @Override
    public OrderEntity getOrder(String id) {
        return orderRepository.getById(id);
    }
}
