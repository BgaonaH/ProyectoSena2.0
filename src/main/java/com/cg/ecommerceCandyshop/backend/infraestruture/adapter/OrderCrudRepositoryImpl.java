package com.cg.ecommerceCandyshop.backend.infraestruture.adapter;

import com.cg.ecommerceCandyshop.backend.domain.model.Order;
import com.cg.ecommerceCandyshop.backend.domain.model.OrderState;
import com.cg.ecommerceCandyshop.backend.domain.port.IOrderRepository;
import com.cg.ecommerceCandyshop.backend.infraestruture.entity.OrderEntity;
import com.cg.ecommerceCandyshop.backend.infraestruture.entity.UserEntity;
import com.cg.ecommerceCandyshop.backend.infraestruture.mapper.IOrderMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCrudRepositoryImpl implements IOrderRepository {
    private final IOrderMapper iOrderMapper;
    private final IOrderCrudRepository iOrderCrudRepository;

    public OrderCrudRepositoryImpl(IOrderMapper iOrderMapper, IOrderCrudRepository iOrderCrudRepository) {
        this.iOrderMapper = iOrderMapper;
        this.iOrderCrudRepository = iOrderCrudRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = iOrderMapper.toOrderEntity(order);

        orderEntity.getOrderProducts().forEach(
                orderProductEntity -> orderProductEntity.setOrderEntity(orderEntity)
        );

        return iOrderMapper.toOrder(iOrderCrudRepository.save(orderEntity));
    }

    @Override
    public Order findById(Integer id) {
        return iOrderMapper.toOrder(iOrderCrudRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Orden con id: "+ id+" no encontrada")
        ));
    }

    @Override
    public Iterable<Order> findAll() {
        return iOrderMapper.toOrderList(iOrderCrudRepository.findAll());
    }

    @Override
    public Iterable<Order> findByUserId(Integer userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return iOrderMapper.toOrderList(iOrderCrudRepository.findByUserEntity(userEntity));
    }

    @Override
    public void updateStateById(Integer id, String state) {
        if(state.equals(OrderState.CANCELLED)){
            iOrderCrudRepository.updateStateById(id,OrderState.CANCELLED);
        }else{
            iOrderCrudRepository.updateStateById(id,OrderState.CONFIRMED);
        }

    }
}
