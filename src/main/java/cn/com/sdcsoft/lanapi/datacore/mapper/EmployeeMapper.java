package cn.com.sdcsoft.lanapi.datacore.mapper;

import cn.com.sdcsoft.lanapi.datacore.entity.db.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeMapper {
    @Select("select * from Employee")
    @ResultType(Employee.class)
    List<Employee> findAll();

    @Select("select * from Employee where OrgType = #{orgType} and OrgId = #{orgId}")
    @ResultType(Employee.class)
    List<Employee> findEmployeesByOrg(@Param("orgType") int orgType, @Param("orgId") int orgId);

    @Select("select * from Employee where id=#{id}")
    Employee findOneById(@Param("id") int id);

    @Select("select * from Employee where mobile=#{loginId} or email=#{loginId}")
    Employee findOneByLoginId(@Param("loginId") String loginId);

    @Update("update Employee set OrgType=#{orgType},OrgId=#{orgId},Password=#{password},Mobile=#{mobile},Email=#{email},WeiXin=#{weiXin},QQ=#{qQ},RealName=#{realName},Status=#{status},LastLoginDatetime=#{lastLoginDatetime},Mark=#{mark} where Id = #{id}")
    void modifyEmployee(Employee Employee);

    @Update("update Employee set Mobile=#{mobile},Email=#{email},RealName=#{realName},QQ=#{qq} where Mobile=#{loginId} or Email=#{loginId}")
    void changeEmployeeInfos(@Param("loginId") String loginId, @Param("mobile") String mobile, @Param("email") String email, @Param("qq") String qq, @Param("realName") String realName);

    @Update("update Employee set Password=#{password} where Mobile=#{loginId} or Email=#{loginId}")
    void changeEmployeePassword(@Param("loginId") String loginId, @Param("password") String password);

    @Update("update Employee set Status=#{status} where Mobile=#{loginId} or Email=#{loginId}")
    void changeEmployeeStatus(@Param("loginId") String loginId, @Param("status") int status);

    @Insert("insert into Employee (OrgType,OrgId,Password,Mobile,Email,QQ,RealName,Status,LastLoginDatetime,Mark) values (#{orgType},#{orgId},#{password},#{mobile},#{email},#{qQ},#{realName},#{status},#{lastLoginDatetime},#{mark})")
    void addEmployee(Employee Employee);

}