package com.imooc.controller;


import com.imooc.pojo.Orders;
import com.imooc.pojo.Users;
import com.imooc.pojo.vo.UsersVO;
import com.imooc.service.center.MyOrdersService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.RedisOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.util.UUID;


@Controller
public class BaseController {

    @Autowired
    public MyOrdersService myOrdersService;

    @Autowired
    RedisOperator redisOperator;

    public final static String FOODIE_SHOPCART = "shopcart";

    public final static Integer COMMON_PAGE_SIZE = 10;
    public final static Integer PAGE_SIZE = 10;

    public final static String REDIS_USER_TOKEN = "redis_user_token";


    //支付中心的调用地址
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";		// produce

    //微信支付成功 --> 支付中心  --> 天天吃货平台
    //                        --> 回调通知url
    public static final String payReturnUrl = "http://8.131.88.186:8088/foodie-dev-api/orders/notifyMerchantOrderPaid";

    public static final String IMAGE_USER_FACE_LOCATION = File.separator+"Users"+
            File.separator+"yangjiaxin"+
            File.separator+"IdeaProjects"+
            File.separator+"images"+
            File.separator+"foodie"+
            File.separator+"faces";


    /**
     * 用于验证用户和订单是否有关联关系，避免非法用户调用
     * @param userId
     * @param orderId
     * @return
     */
    public IMOOCJSONResult checkUserOrder(String userId, String orderId){
        Orders orders = myOrdersService.queryMyOrder(userId, orderId);
        if (orderId == null){
            return IMOOCJSONResult.errorMsg("订单不存在!");
        }
        return IMOOCJSONResult.ok(orders);
    }


    public UsersVO conventUserVO(Users users){
        //实现redis分布式会话
        String uniqueToken = UUID.randomUUID().toString().trim();
        redisOperator.set(REDIS_USER_TOKEN + ":" + users.getId(),uniqueToken);

        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(users,usersVO);
        usersVO.setUserUniqueToken(uniqueToken);

        return usersVO;
    }




}
