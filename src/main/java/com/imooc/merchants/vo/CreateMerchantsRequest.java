package com.imooc.merchants.vo;

import com.imooc.merchants.constant.ErrorCode;
import com.imooc.merchants.dao.MerchantsDao;
import com.imooc.merchants.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *创建商户请求对象
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsRequest {

    private String name;

    private String logoUrl;

    private String businessLicenseUrl;

    private String phone;

    private String address;

    /**
     * 验证请求的有效性
     * @param merchantsDao
     * @return
     */
    public ErrorCode validate(MerchantsDao merchantsDao) {

        if (null == name) {
            return ErrorCode.EMPTY_NAME;
        } else if (null != merchantsDao.findByName(name)) {
            return ErrorCode.DUPLICATE_NAME;
        } else if (null == logoUrl) {
            return ErrorCode.EMPTY_LOGO;
        } else if (null == businessLicenseUrl) {
            return ErrorCode.EMPTY_BUSINESS_LICENSE;
        } else if (null == address) {
            return ErrorCode.EMPTY_ADDRESS;
        } else if (null == phone) {
            return ErrorCode.ERROR_PHONE;
        }

        return ErrorCode.SUCCESS;
    }

    /**
     *将请求对象转换成商户对象
     * @return {@link Merchants}
     */
    public Merchants toMerchants() {
        Merchants merchants = new Merchants();

        merchants.setName(name);
        merchants.setLogoUrl(logoUrl);
        merchants.setBusinessLicenseUrl(businessLicenseUrl);
        merchants.setPhone(phone);
        merchants.setAddress(address);

        return merchants;
    }

}
