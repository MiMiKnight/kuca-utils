package cn.yhm.developer.kuca.common.enumeration;

/**
 * 敏感信息类型
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-03-25 02:00:50
 */
public enum SensitiveType {

    /**
     * 中文姓名
     * <p>
     * 3个字及以下显示1个字，3个字以上显示2个字
     */
    CHINESE_NAME(),

    /**
     * 电子邮箱
     * <p>
     * 显示"@"及以后的字符
     */
    EMAIL(),

    /**
     * 密码
     */
    PASSWORD(),

    /**
     * 中国大陆身份证号
     * <p>
     * 显示前六位和后四位，其他位置用星号隐藏
     */
    ID_CARD(),

    /**
     * 移动电话
     * <p>
     * 显示前3后4
     */
    MOBILE_PHONE(),

    /**
     * 固定电话
     */
    FIXED_PHONE(),

    /**
     * 银行卡号
     */
    BANK_CARD(),

    /**
     * 国内（中国）投递地址（收货地址）
     * <p>
     * 12字及以下显示6个字，12字以上显示不超过50%
     */
    DELIVERY_ADDRESS(),

    /**
     * IPv4地址
     */
    IPv4(),

    /**
     * IPv6地址
     */
    IPv6(),

    /**
     * MAC地址（网络设备的物理地址）
     */
    MAC_ADDRESS(),

    /**
     * 中国汽车牌照
     */
    LICENSE_PLATE();
}
