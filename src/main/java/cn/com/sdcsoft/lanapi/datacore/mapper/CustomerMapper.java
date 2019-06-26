package cn.com.sdcsoft.lanapi.datacore.mapper;

import cn.com.sdcsoft.lanapi.datacore.entity.db.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerMapper {
    @Select("select * from Customer")
    @ResultType(Customer.class)
    List<Customer> findAll();

    @Select("select * from Customer where id=#{id}")
    Customer findOneById(@Param("id") int id);

    @Select("select * from Customer where CustomerName like CONCAT('%',#{customerName},'%')")
    List<Customer> find(@Param("customerName") String customerName);

    @Update("update Customer set CustomerName=#{customerName},Status=#{status} where Id = #{id}")
    void modifyCustomer(@Param("id") int id, @Param("customerName") String customerName, @Param("status") int status);

//    @Update("update Customer set Status=#{status} where Id=#{id}")
//    int changeCustomerStatus(Customer customer);

    @Select("select count(CustomerName) from Customer where CustomerName=#{customerName}")
    int checkCustomer(@Param("customerName") String customerName);

    @Insert("insert into Customer (CustomerName,Status) values (#{customerName},#{status})")
    void addCustomer(@Param("customerName") String customerName,
                     @Param("status") int status);


}
