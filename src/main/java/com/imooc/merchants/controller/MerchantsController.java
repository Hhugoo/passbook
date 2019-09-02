package com.imooc.merchants.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.merchants.service.IMerchantsService;
import com.imooc.merchants.vo.CreateMerchantsRequest;
import com.imooc.merchants.vo.PassTemplate;
import com.imooc.merchants.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 商户服务controller
 *
 */
@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsController {

    @Autowired
    private IMerchantsService iMerchantsService;

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request) {
        log.info("CreateMerchants: {}", JSON.toJSONString(request));
        return iMerchantsService.createMerchants(request);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id) {
        log.info("BuildMerchantsInfo: {}", id);
        return iMerchantsService.bulidMerchantsInfoById(id);
    }

    @ResponseBody
    @PostMapping("/drop")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate) {
        log.info("DropPassTemplate: {}", passTemplate);
        return iMerchantsService.dropPassTemplate(passTemplate);
    }

}
