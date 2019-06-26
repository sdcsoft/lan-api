package cn.com.sdcsoft.lanapi.datacore.entity.db;

import java.io.Serializable;

/**
 * 企业的客户号段数据结构
 */
public class EnterpriseCustomerCode implements Serializable {
    private int id;
    private int EnterpriseCustomerId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;
    private  String customerName,codePrefix,code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnterpriseCustomerId() {
        return EnterpriseCustomerId;
    }

    public void setEnterpriseCustomerId(int enterpriseCustomerId) {
        this.EnterpriseCustomerId = enterpriseCustomerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCodePrefix() {
        return codePrefix;
    }

    public void setCodePrefix(String codePrefix) {
        this.codePrefix = codePrefix;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
