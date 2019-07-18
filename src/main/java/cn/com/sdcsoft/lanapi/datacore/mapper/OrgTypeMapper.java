package cn.com.sdcsoft.lanapi.datacore.mapper;

import cn.com.sdcsoft.lanapi.datacore.entity.db.OrgType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrgTypeMapper {

    @Select("select * from OrgType ")
    List<OrgType> getTypeList();

    @Update("update OrgType set OrgTypeName=#{orgTypeName} where OrgType=#{orgType}")
    void modifyOrgType(@Param("orgType") int orgType, @Param("orgTypeName") String orgTypeName);


    @Select("select count(OrgType) from OrgType where OrgType=#{orgType}")
    int checkOrgType(@Param("orgType") int orgType);

    @Insert("insert into OrgType(OrgType,OrgTypeName) values (#{orgType},#{orgTypeName})")
    void addOrgType(@Param("orgType") int orgType, @Param("orgTypeName") String orgTypeName);
}
