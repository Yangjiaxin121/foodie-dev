package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersMapperCustom{

    public List queryMyOrders(@Param("paramsMap")Map<String,Object> map);


    public int getMyOrderStatusCounts(@Param("paramsMap")Map<String,Object> map);


    public List<OrderStatus> getMyOrderTrend(@Param("paramsMap")Map<String,Object> map);




}