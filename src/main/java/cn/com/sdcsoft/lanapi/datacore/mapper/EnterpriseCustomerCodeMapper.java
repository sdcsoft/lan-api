package cn.com.sdcsoft.lanapi.datacore.mapper;

import cn.com.sdcsoft.lanapi.datacore.entity.db.EnterpriseCustomerCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EnterpriseCustomerCodeMapper {
    @Select("select c.Id,c.Code,e.CustomerName,p.Status from EnterpriseCustomerCode as c inner join  EnterpriseCode as p on p.CodePrefix = c.CodePrefix inner join EnterpriseCustomer as e on e.Id = c.EnterpriseCustomerId where p.EnterpriseId={enterpriseId}")
    List<EnterpriseCustomerCode> getListByEnterpriseId(@Param("enterpriseId") int enterpriseId);

    @Select("select ecc.Id,ec.CodePrefix,ecc.Code,e.CustomerName,ec.Status from EnterpriseCustomerCode as ecc inner join  EnterpriseCode as ec on ec.CodePrefix = ecc.CodePrefix inner join EnterpriseCustomer as e on e.Id = ecc.EnterpriseCustomerId where ecc.EnterpriseCustomerId = #{enterpriseCustomerId}")
    List<EnterpriseCustomerCode> getListByEnterpriseCustomer(@Param("enterpriseCustomerId") int customerId);

    @Select("select count(Code) from EnterpriseCustomerCode where Code=#{code}")
    int checkCode(@Param("code") String code);

    @Insert("insert into EnterpriseCustomerCode ( EnterpriseCustomerId,CodePrefix,Code) values(#{ enterpriseCustomerId},#{codePrefix},#{code})")
    void create(@Param("enterpriseCustomerId") int enterpriseCustomerId, @Param("codePrefix") String codePrefix, @Param("code") String code);

}
