package cn.com.sdcsoft.lanapi.datacore.entity.db;

import java.io.Serializable;

/**
 * 公司数据结构
 */
public class Company implements Serializable {
    private int id, status;
    private String companyName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
