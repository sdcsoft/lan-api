package cn.com.sdcsoft.lanapi.datacore.entity.db;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备数据结构
 */
public class Device implements Serializable {
    public static final int STATUS_SELL = 1;
    public static final int STATUS_READY = 0;

    private int id;
    private int enterpriseId;
    private Integer customerId;
    private Integer agentId;
    private Integer endUserId;

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getEndUserId() {
        return endUserId;
    }

    public void setEndUserId(Integer endUserId) {
        this.endUserId = endUserId;
    }

    private int status;
    private int runStatus;
    private int power;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    private int media;
    private String deviceNo;

    public String getiMEI() {
        return iMEI;
    }

    public void setiMEI(String iMEI) {
        this.iMEI = iMEI;
    }

    private String iMEI;
    /**
     * 设备前10位编码
     */
    private String devicePrefix;
    /**
     * 设备后10位编码
     */
    private String deviceSuffix;
    /**
     * 设备类型 如：CTL_NJZJ_IPK2 PLC_BKSE_DRY
     */
    private String deviceType;
    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    private String subType;
    public String getDevicePrefix() {
        return devicePrefix;
    }

    public void setDevicePrefix(String devicePrefix) {
        this.devicePrefix = devicePrefix;
    }

    public String getDeviceSuffix() {
        return deviceSuffix;
    }

    public void setDeviceSuffix(String deviceSuffix) {
        this.deviceSuffix = deviceSuffix;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date importDatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(int runStatus) {
        this.runStatus = runStatus;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceno) {
        this.deviceNo = deviceno;
    }


    public Date getImportDatetime() {
        return importDatetime;
    }

    public void setImportDatetime(Date importDatetime) {
        this.importDatetime = importDatetime;
    }

}
