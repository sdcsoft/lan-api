package cn.com.sdcsoft.lanapi.datacore.entity.db;

import java.io.Serializable;

/**
 * 代理商数据结构
 */
public class Agent implements Serializable {
    private int id, status;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    private String agentName;


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


}
