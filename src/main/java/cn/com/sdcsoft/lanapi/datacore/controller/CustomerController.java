package cn.com.sdcsoft.lanapi.datacore.controller;

import  cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import  cn.com.sdcsoft.lanapi.datacore.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 锅炉厂管理接口
 * 锅炉厂与企业解绑 二者业务无必然联系
 * 核心数据库中仅存储锅炉厂基本信息与用户映射关系
 * 锅炉厂产品、员工、客户均由锅炉厂数据库存储
 */

@RestController
@RequestMapping(value = "/datacore/customer")
public class CustomerController {
    @Autowired
    CustomerMapper mapper;

    /**
     * 获取锅炉厂列表
     * @return
     */
    @GetMapping(value = "/list")
    public Result getAll() {
        try {
            return Result.getSuccessResult(mapper.findAll());
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 创建锅炉厂
     * @param customerName
     * @param status
     * @return
     */
    @PostMapping(value = "/create")
    public Result create(String customerName, int status) {
        try {
            if (0 < mapper.checkCustomer(customerName)) {
                return Result.getFailResult("该锅炉厂已存在.");
            }
            mapper.addCustomer(customerName, status);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }

    }

    /**
     * 修改锅炉厂信息
     * @param id
     * @param customerName
     * @param status
     * @return
     */
    @PostMapping(value = "/modify")
    public Result modify(int id,String customerName,int status) {
        try {
            mapper.modifyCustomer(id,customerName,status);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

//    @PostMapping(value = "/change")
//    public Result modifyCustomerStatus(Customer customer) {
//        try {
//            mapper.changeCustomerStatus(customer);
//            return Result.getSuccessResult();
//        } catch (Exception ex) {
//            return Result.getFailResult(ex.getMessage());
//        }
//    }
}
