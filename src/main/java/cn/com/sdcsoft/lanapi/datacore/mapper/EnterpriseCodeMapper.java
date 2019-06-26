package cn.com.sdcsoft.lanapi.datacore.mapper;

import cn.com.sdcsoft.lanapi.datacore.entity.db.EnterpriseCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EnterpriseCodeMapper {
    @Select("select c.Id,c.CodePrefix,e.EnterpriseName,c.Status,c.EnterpriseId from Enterprise as e inner join  EnterpriseCode as c on e.id = c.EnterpriseId")
    List<EnterpriseCode> getList();

    @Select("select count(CodePrefix) from EnterpriseCode where CodePrefix=#{codePrefix}")
    int checkPrefix(@Param("codePrefix") String codePrefix);


    @Update("update EnterpriseCode set Status=#{status} where Id = #{id}")
    void updateState(@Param("id") int id, @Param("status") int status);

    @Insert("insert into EnterpriseCode (EnterpriseId,CodePrefix,Status) values(#{enterpriseId},#{codePrefix},1)")
    void create(@Param("enterpriseId") int enterpriseId, @Param("codePrefix") String codePrefix);


}
