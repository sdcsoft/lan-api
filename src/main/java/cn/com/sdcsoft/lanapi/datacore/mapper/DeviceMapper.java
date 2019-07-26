package cn.com.sdcsoft.lanapi.datacore.mapper;

import cn.com.sdcsoft.lanapi.datacore.entity.db.Device;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceMapper {
    @Select("select * from Device")
    List<Device> findAll();

    //获取企业控制器
    @Select("select * from Device where EnterpriseId=#{enterpriseId}")
    List<Device> findDeviceByEnterpriseId(@Param("enterpriseId") int enterpriseId);

    //获取客户控制器
    @Select("select * from Device where CustomerId=#{customerId} and Status = 1")
    List<Device> findDeviceByCustomerId(@Param("customerId") int customerId);

    @Select("select * from Device where Status=#{status}")
    @ResultType(Device.class)
    List<Device> findAllByStatus(@Param("status") int status);

    @Select("select * from Device where DevicePrefix=1 and Status=#{status}")
    @ResultType(Device.class)
    List<Device> findAllCTLByStatus(@Param("status") int status);

    @Select("select * from Device where DevicePrefix=2 and Status=#{status}")
    @ResultType(Device.class)
    List<Device> findAllPLCByStatus(@Param("status") int status);

    @Select("select * from Device where DeviceNo=#{deviceNo} and Status=1")
    @ResultType(Device.class)
    Device findByDeviceNo(@Param("deviceNo") String deviceNo);

    @Select("select * from Device where DeviceSuffix=#{deviceSuffix} and Status=1")
    @ResultType(Device.class)
    Device findBySuffix(@Param("deviceSuffix") String deviceSuffix);

    @Select("select * from Device where id=#{id}")
    Device findById(@Param("id") int id);

    @Update("update Device set DeviceType=#{deviceType},SubType=#{subType} where DeviceSuffix=#{deviceSuffix}")
    void modifyDeviceType(@Param("deviceSuffix") String deviceSuffix, @Param("deviceType") String deviceType, @Param("subType") String subType);

    @Update("update Device set CustomerId=#{customerId} where DeviceSuffix=#{deviceSuffix} and ISNULL(CustomerId)")
    void modifyCustomerId(@Param("deviceSuffix") String deviceSuffix, @Param("customerId") Integer customerId);

    @Update("update Device set CustomerId=#{customerId} where DeviceSuffix=#{deviceSuffix}")
    void clearCustomerId(@Param("deviceSuffix") String deviceSuffix, @Param("customerId") Integer customerId);


    @Update("update Device set AgentId=#{agentId} where DeviceSuffix=#{deviceSuffix} and ISNULL(AgentId)")
    void modifyAgentId(@Param("deviceSuffix") String deviceSuffix, @Param("agentId") Integer agentId);

    @Update("update Device set EndUserId=#{endUserId} where DeviceSuffix=#{deviceSuffix} and ISNULL(EndUserId)")
    void modifyEndUserId(@Param("deviceSuffix") String deviceSuffix, @Param("EndUserId") Integer endUserId);

    @Select("select * from Device where DeviceSuffix=#{deviceSuffix}")
    @ResultType(Device.class)
    Device findBySuffixForEnterpriseUser(@Param("deviceSuffix") String deviceSuffix);

    @Update("<script>" +
            "update Device" +
            "<set>" +
            "<if test='saleStatus>0'>Status=1,SaleDatetime=Now(),</if>" +
            "<if test='1>saleStatus'>Status=0,SaleDatetime=NULL,</if>" +
            "<if test='1==1'>DeviceType=#{deviceType},DevicePrefix=#{devicePrefix},Power=#{power},Media=#{media}</if>" +
            "</set>" +
            "where DeviceSuffix=#{deviceSuffix}</script>")
    void modifyDeviceForEnterpriseUser(
            @Param("deviceSuffix") String deviceSuffix,
            @Param("devicePrefix") int devicePrefix,
            @Param("deviceType") String deviceType,
            @Param("saleStatus") int saleStatus,
            @Param("power") int power,
            @Param("media") int media);

    @Insert("<script>insert into Device (EnterpriseId,DeviceNo,DeviceSuffix,DeviceType,SubType,ImportDatetime) values "
            + "<foreach collection =\"deviceList\" item=\"device\" index=\"index\" separator =\",\"> "
            + "(#{device.enterpriseId},#{device.deviceNo},#{device.deviceSuffix},#{device.deviceType},#{device.deviceType},Now()) "
            + "</foreach ></script>")
    void addDevice(@Param("deviceList") List<Device> deviceList);

}
