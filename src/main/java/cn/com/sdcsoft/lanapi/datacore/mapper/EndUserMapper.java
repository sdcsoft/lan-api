package cn.com.sdcsoft.lanapi.datacore.mapper;

import cn.com.sdcsoft.lanapi.datacore.entity.db.EndUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EndUserMapper {
    @Select("select * from EndUser")
    @ResultType(EndUser.class)
    List<EndUser> findAll();

    @Select("select * from EndUser where id=#{id}")
    EndUser findOneById(@Param("id") int id);

    @Select("select * from EndUser where EndUserName like CONCAT('%',#{endUserName},'%')")
    List<EndUser> find(@Param("endUserName") String endUserName);

    @Update("update EndUser set EndUserName=#{endUserName},Status=#{status} where Id = #{id}")
    int modifyEndUser(@Param("id") int id, @Param("endUserName") String endUserName, @Param("status") int status);

//    @Update("update EndUser set Status=#{status} where Id=#{id}")
//    int changeEndUserStatus(EndUser EndUser);

    @Select("select count(EndUserName) from EndUser where EndUserName=#{endUserName}")
    int checkEndUser(@Param("endUserName") String agentName);

    @Insert("insert into EndUser (EndUserName,Status) values (#{endUserName},#{status})")
    int addEndUser(@Param("endUserName") String endUserName, @Param("status") int status);
}