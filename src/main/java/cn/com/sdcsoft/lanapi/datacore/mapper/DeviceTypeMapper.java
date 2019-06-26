package cn.com.sdcsoft.lanapi.datacore.mapper;

import cn.com.sdcsoft.lanapi.datacore.entity.db.DeviceType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceTypeMapper {

    @Select("select * from DeviceType ")
    List<DeviceType> findAll();

    @Update("update DeviceType set DeviceType=#{deviceType} where Id=#{id}")
    void modifyDeviceType(@Param("id") int id, @Param("deviceType") String deviceType);

    @Select("select count(DeviceType) from DeviceType where DeviceType=#{deviceType}")
    int checkDeviceType(@Param("deviceType") String deviceType);

    @Insert("insert into DeviceType(DeviceType) values (#{deviceType})")
    void addDeviceType(@Param("deviceType") String deviceType);
}
