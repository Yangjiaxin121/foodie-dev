package com.imooc.service.center;

import com.imooc.pojo.OrderItems;
import com.imooc.pojo.bo.center.OrderItemsCommentBO;
import com.imooc.utils.PagedGridResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MyCommentsService {

    /**
     * 根据订单id查询关联的商品
     * @return
     */
    public List<OrderItems> queryPendingComments(String orderId);

    /**
     * 保存用户的评论
     * @param orderId
     * @param userId
     * @param commentList
     */
    public void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList);


    /**
     * 我的评价查询 分页
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult queryMyComment(String userId, Integer page, Integer pageSize);
}
