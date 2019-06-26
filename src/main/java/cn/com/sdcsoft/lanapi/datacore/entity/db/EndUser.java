package cn.com.sdcsoft.lanapi.datacore.entity.db;

import java.io.Serializable;

/**
 * 终端用户数据结构
 */
public class EndUser implements Serializable {
    private int id, status;
    private String endUserName;

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

    public String getEndUserName() {
        return endUserName;
    }

    public void setEndUserName(String endUserName) {
        this.endUserName = endUserName;
    }

}
