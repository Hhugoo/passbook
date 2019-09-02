package com.imooc.merchants.vo;

import com.imooc.merchants.constant.ErrorCode;
import com.imooc.merchants.dao.MerchantsDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 投放的优惠券对象定义
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplate {

    private Integer id;

    private String title;

    /** 优惠券摘要 */
    private String summary;

    private String desc;

    private Long limit;

    /**
     * 优惠券是否有token，用于商户核销
     * token 存储于Redis Set中，每次领取从redis中获取
     */
    private Boolean hasToken;

    private Integer background;

    private Date start;

    private Date end;

    /**
     * 校验优惠券对象的有效性
     * @param merchantsDao
     * @return
     */
    public ErrorCode validate(MerchantsDao merchantsDao) {
        if (merchantsDao.findById(id) == null) {
            return ErrorCode.MERCHANTS_NOT_EXIST;
        }
        return ErrorCode.SUCCESS;
    }

}
