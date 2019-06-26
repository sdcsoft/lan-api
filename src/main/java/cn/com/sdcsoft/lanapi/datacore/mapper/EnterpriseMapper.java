package cn.com.sdcsoft.lanapi.datacore.mapper;

import cn.com.sdcsoft.lanapi.datacore.entity.db.Enterprise;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EnterpriseMapper {
    @Select("select * from Enterprise")
    @ResultType(Enterprise.class)
    List<Enterprise> findAll();

//    @Select("select * from Enterprise where status=#{status}")
//    List<Enterprise> findEnterprisesByStatus(@Param("status") int status);

    @Select("select * from Enterprise where id=#{id}")
    Enterprise findOneById(@Param("id") int id);


    @Update("update Enterprise set EnterpriseName=#{enterpriseName},Status=#{status} where Id = #{id}")
    void modifyEnterprise(@Param("id") int id, @Param("enterpriseName") String enterpriseName, @Param("status") int status);

    @Insert("insert into Enterprise (EnterpriseName,Status) values (#{enterpriseName},#{status})")
    void addEnterprise(@Param("enterpriseName") String enterpriseName, @Param("status") int status);

}
