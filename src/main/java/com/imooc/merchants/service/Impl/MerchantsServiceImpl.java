package com.imooc.merchants.service.Impl;


import com.alibaba.fastjson.JSON;
import com.imooc.merchants.constant.Constants;
import com.imooc.merchants.constant.ErrorCode;
import com.imooc.merchants.dao.MerchantsDao;
import com.imooc.merchants.entity.Merchants;
import com.imooc.merchants.service.IMerchantsService;
import com.imooc.merchants.vo.CreateMerchantsRequest;
import com.imooc.merchants.vo.CreateMerchantsResponse;
import com.imooc.merchants.vo.PassTemplate;
import com.imooc.merchants.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 商户服务接口实现
 */
@Slf4j
@Service
public class MerchantsServiceImpl implements IMerchantsService{

    @Autowired
    private MerchantsDao merchantsDao;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;



    @Override
    @Transactional
    public Response createMerchants(CreateMerchantsRequest request) {
        Response response = new Response();
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();

        ErrorCode errorCode = request.validate(merchantsDao);
        if (errorCode != ErrorCode.SUCCESS) {
            merchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }
        response.setData(merchantsResponse);
        return response;
    }

    @Override
    public Response bulidMerchantsInfoById(Integer id) {
        Response response = new Response();

        Merchants merchants = merchantsDao.findById(id).get();
        if (null == merchants) {
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }

        response.setData(merchants);

        return response;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {
        Response response = new Response();
        ErrorCode errorCode = template.validate(merchantsDao);

        if (errorCode != ErrorCode.SUCCESS) {
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            String passTemplate = JSON.toJSONString(template);
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC,
                    Constants.TOKEN_STRING,
                    passTemplate
            );
            log.info("DropPassTemplate: {}", passTemplate);
        }
        return response;
    }
}
