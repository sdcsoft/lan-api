package cn.com.sdcsoft.lanapi.datacore.entity.db;

import java.io.Serializable;

/**
 * 企业客户数据结构
 */
public class EnterpriseCustomer implements Serializable {
    private int id, enterpriseId;
    private String customerName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
