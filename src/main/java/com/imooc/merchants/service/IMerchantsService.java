package com.imooc.merchants.service;

import com.imooc.merchants.vo.CreateMerchantsRequest;
import com.imooc.merchants.vo.PassTemplate;
import com.imooc.merchants.vo.Response;
import org.springframework.stereotype.Component;

/**
 * 对商户服务接口定义
 */
public interface IMerchantsService {

    /**
     * 创建商户服务
     *@param request 创建商户请求
     *@return {@link Response}
     */
    Response createMerchants(CreateMerchantsRequest request);

    /**
     * 根据id构造（获取）商户信息
     * @param id
     * @return
     */
    Response bulidMerchantsInfoById(Integer id);

    /**
     * 投放优惠券
     * @param template
     * @return
     */
    Response dropPassTemplate(PassTemplate template);
}
