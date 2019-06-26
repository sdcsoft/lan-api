package cn.com.sdcsoft.lanapi.datacore.mapper;

import cn.com.sdcsoft.lanapi.datacore.entity.db.EnterpriseCustomer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EnterpriseCustomerMapper {
    @Select("select * from EnterpriseCustomer where EnterpriseId=#{enterpriseId}")
    List<EnterpriseCustomer> findByEnterpriseId(@Param("enterpriseId") int enterpriseId);

    @Select("select * from EnterpriseCustomer where id=#{id}")
    EnterpriseCustomer findOneById(@Param("id") int id);

    @Select("select * from EnterpriseCustomer where CustomerName like CONCAT('%',#{customerName},'%')")
    List<EnterpriseCustomer> findByName(@Param("customerName") String customerName);

    @Update("update EnterpriseCustomer set CustomerName=#{customerName} where Id = #{id}")
    void modifyCustomer(@Param("id") int id, @Param("customerName") String customerName);

//    @Update("update Customer set Status=#{status} where Id=#{id}")
//    int changeCustomerStatus(Customer customer);

    @Select("select count(CustomerName) from EnterpriseCustomer where EnterpriseId=#{enterpriseId} and CustomerName=#{customerName}")
    int checkCustomer(@Param("enterpriseId") int enterpriseId, @Param("customerName") String customerName);

    @Insert("insert into EnterpriseCustomer (EnterpriseId,CustomerName) values (#{enterpriseId},#{customerName})")
    void addCustomer(@Param("enterpriseId") int enterpriseId,
                     @Param("customerName") String customerName);


}
