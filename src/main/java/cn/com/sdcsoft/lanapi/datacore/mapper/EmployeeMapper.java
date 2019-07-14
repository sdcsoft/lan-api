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

    @Select("select * FROM Employee where WeiXin=#{openId}")
    Employee findOneByWechat(@Param("openId") String openId);

    @Select("select * FROM Employee where mobile=#{loginId} or email=#{loginId} ")
    Employee findOneByLoginId(@Param("loginId") String loginId);

    @Select("select e.* FROM Employee where OrgType = 0 and mobile=#{loginId} or email=#{loginId} ")
    Employee findCompanyEmployeeByLoginId(@Param("loginId") String loginId);

    @Select("select e.*, c.CustomerName as OrgName,c.Status as OrgStatus FROM Employee AS e INNER JOIN Customer c ON e.OrgType = 2 AND e.OrgId = c.Id  where mobile=#{loginId} or email=#{loginId} ")
    Employee findCustomerEmployeeLoginId(@Param("loginId") String loginId);

    @Select("select e.*, c.AgentName as OrgName,c.Status as OrgStatus FROM Employee AS e INNER JOIN Agent c ON e.OrgType = 3 AND e.OrgId = c.Id  where mobile=#{loginId} or email=#{loginId} ")
    Employee findAgentEmployeeLoginId(@Param("loginId") String loginId);

    @Select("select e.*, c.EndUserName as OrgName,c.Status as OrgStatus FROM Employee AS e INNER JOIN EndUser c ON e.OrgType = 3 AND e.OrgId = c.Id  where mobile=#{loginId} or email=#{loginId} ")
    Employee findEndUserEmployeeLoginId(@Param("loginId") String loginId);

    @Update("update Employee set OrgType=#{orgType},OrgId=#{orgId},Password=#{password},Mobile=#{mobile},Email=#{email},WeiXin=#{weiXin},QQ=#{qQ},RealName=#{realName},Status=#{status},LastLoginDatetime=#{lastLoginDatetime},Mark=#{mark} where Id = #{id}")
    void modifyEmployee(Employee Employee);

    @Update("update Employee set Mobile=#{mobile},Email=#{email},RealName=#{realName},QQ=#{qq} where Mobile=#{loginId} or Email=#{loginId}")
    void changeEmployeeInfos(@Param("loginId") String loginId, @Param("mobile") String mobile, @Param("email") String email, @Param("qq") String qq, @Param("realName") String realName);

    @Update("update Employee set Password=#{password} where Mobile=#{loginId} or Email=#{loginId}")
    void changeEmployeePassword(@Param("loginId") String loginId, @Param("password") String password);

    @Update("update Employee set WeiXin=#{openId} where Mobile=#{loginId} or Email=#{loginId}")
    void bindWechat(@Param("loginId") String loginId, @Param("openId") String openId);

    @Update("update Employee set Password=#{password} where Id=#{id}")
    void changePassword(@Param("id") Integer id, @Param("password") String password);


    @Update("update Employee set Status=#{status} where Mobile=#{loginId} or Email=#{loginId}")
    void changeEmployeeStatus(@Param("loginId") String loginId, @Param("status") int status);

    @Update("update Employee set OrgType=5,OrgId=0 where Mobile=#{loginId} or Email=#{loginId}")
    void freeEmployee(@Param("loginId") String loginId);

    @Insert("insert into Employee (OrgType,OrgId,Password,Mobile,Email,QQ,RealName,Status,LastLoginDatetime,Mark) values (#{orgType},#{orgId},#{password},#{mobile},#{email},#{qQ},#{realName},#{status},#{lastLoginDatetime},#{mark})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addEmployee(Employee Employee);

}