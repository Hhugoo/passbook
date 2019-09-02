package com.imooc.merchans.service;

import com.alibaba.fastjson.JSON;
import com.imooc.merchants.service.IMerchantsService;
import com.imooc.merchants.vo.CreateMerchantsRequest;
import com.imooc.merchants.vo.PassTemplate;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 商户服务测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServiceTest {

    @Autowired
    private IMerchantsService iMerchantsService;

    @Test
    @Transactional
    public void testCreatemerchantsService() {
        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setName("imooc");
        request.setLogoUrl("www.imooc.com");
        request.setBusinessLicenseUrl("www.imooc.com");
        request.setPhone("123456789");
        request.setAddress("杭州师范大学");

        System.out.println(JSON.toJSONString(iMerchantsService.createMerchants(request)));
    }

    @Test
    @Transactional
    public void testBulidMerchantsInfoById(Integer id) {
        System.out.println(JSON.toJSONString(iMerchantsService.bulidMerchantsInfoById(id)));
    }

    @Test
    @Transactional
    public void testDropPassTemplate() {
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(1);
        passTemplate.setTitle("hugoo");
        passTemplate.setSummary("hugoo商场优惠券");
        passTemplate.setDesc("hugoosdfbsdjhbfjsd");
        passTemplate.setLimit(1000L);
        passTemplate.setBackground(2);
        passTemplate.setStart(new Date());
        passTemplate.setEnd(DateUtils.addDays(new Date(), 10));

        System.out.println(JSON.toJSONString(
                iMerchantsService.dropPassTemplate(passTemplate)
        ));
    }

}
