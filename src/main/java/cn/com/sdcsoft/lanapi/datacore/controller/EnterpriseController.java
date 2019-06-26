package cn.com.sdcsoft.lanapi.datacore.controller;

import  cn.com.sdcsoft.lanapi.datacore.entity.db.EnterpriseCode;
import  cn.com.sdcsoft.lanapi.datacore.entity.db.EnterpriseCustomerCode;
import  cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import  cn.com.sdcsoft.lanapi.datacore.mapper.EnterpriseCodeMapper;
import  cn.com.sdcsoft.lanapi.datacore.mapper.EnterpriseCustomerCodeMapper;
import  cn.com.sdcsoft.lanapi.datacore.mapper.EnterpriseCustomerMapper;
import  cn.com.sdcsoft.lanapi.datacore.mapper.EnterpriseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 企业管理接口
 * 企业的客户、产品、员工 应该单独进行数据库存储，现临时放在核心库中
 * 需要将来分离的数据表有：
 * EnterpriseCustomer
 * EnterpriseCustomerCode
 * EnterpriseProduct
 * 现在EnterpriseProduct暂时没有使用，企业的产品临时由Device充当
 * 将来需要由Device数据构建出企业的产品信息并存入企业产品库
 */

@RestController
@RequestMapping(value = "/datacore/enterprise")
public class EnterpriseController {
    @Autowired
    EnterpriseMapper mapper;

    /**
     * 获取企业列表
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

//    @PostMapping(value = "/status")
//    public Result getEnterprisesByStatus(@RequestParam(name = "status", defaultValue = "-1") int status) {
//        try {
//            return Result.getSuccessResult(mapper.findEnterprisesByStatus(status));
//        } catch (Exception ex) {
//            return Result.getFailResult(ex.getMessage());
//        }
//    }

    /**
     * 创建企业
     * @param enterpriseName
     * @param status
     * @return
     */
    @PostMapping(value = "/create")
    public Result create(String enterpriseName,int status) {
        try {
            mapper.addEnterprise(enterpriseName,status);
            return Result.getFailResult("该企业已存在.");
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 修改企业信息
     * @param id
     * @param enterpriseName
     * @param status
     * @return
     */
    @PostMapping(value = "/modify")
    public Result modifyEnterprise(int id,String enterpriseName,int status) {
        try {
            mapper.modifyEnterprise(id,enterpriseName,status);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    @Autowired
    EnterpriseCodeMapper enterpriseCodeMapper;

    /**
     * 获取企业编号号段列表
     * 说明：该功能属于企业管理系统，临时放在核心接口中。在企业管理系统完成后会迁出
     * @return
     */
    @GetMapping(value = "/prefix/list")
    public Result getEnterprisePrefix() {
        try {
            List<EnterpriseCode> list = enterpriseCodeMapper.getList();
            return Result.getSuccessResult(list);
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 修改企业编号号段信息，只能改：状态是否可用
     * 说明：该功能属于企业管理系统，临时放在核心接口中。在企业管理系统完成后会迁出
     * @param enterpriseId
     * @param status
     * @return
     */
    @PostMapping(value = "/prefix/modify")
    public Result changePrefixStatus(int enterpriseId, int status) {
        try {
            enterpriseCodeMapper.updateState(enterpriseId, status > 0 ? 1 : 0);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 创建企业设备编号号段
     * 说明：该功能属于企业管理系统，临时放在核心接口中。在企业管理系统完成后会迁出
     * @param enterpriseId
     * @param prefix
     * @return
     */
    @PostMapping(value = "/prefix/create")
    public Result createPrefix(int enterpriseId, String prefix) {
        if (enterpriseCodeMapper.checkPrefix(prefix) > 0) {
            return Result.getFailResult("该编号已被使用。");
        }
        try {
            enterpriseCodeMapper.create(enterpriseId, prefix);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    @Autowired
    EnterpriseCustomerMapper enterpriseCustomerMapper;

    /**
     * 获取企业的客户列表
     * 说明：该功能属于企业管理系统，临时放在核心接口中。在企业管理系统完成后会迁出
     *       企业的客户是由企业自行维护的数据，不属于核心库
     * @param enterpriseId
     * @return
     */
    @GetMapping(value = "/customer/list")
    public Result getCustomer(int enterpriseId) {
        try {
            return Result.getSuccessResult(enterpriseCustomerMapper.findByEnterpriseId(enterpriseId));
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 创建企业客户
     * 说明：该功能属于企业管理系统，临时放在核心接口中。在企业管理系统完成后会迁出
     * @param enterpriseId
     * @param customerName
     * @param status
     * @return
     */
    @PostMapping(value = "/customer/create")
    public Result create(int enterpriseId, String customerName, int status) {
        try {
            if (0 < enterpriseCustomerMapper.checkCustomer(enterpriseId, customerName)) {
                return Result.getFailResult("该客户已存在.");
            }
            enterpriseCustomerMapper.addCustomer(enterpriseId, customerName);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }

    }

    /**
     * 修改企业客户信息
     * 说明：该功能属于企业管理系统，临时放在核心接口中。在企业管理系统完成后会迁出
     * @param id
     * @param customerName
     * @return
     */
    @PostMapping(value = "/customer/modify")
    public Result modifyCustomer(int id, String customerName) {
        try {
            enterpriseCustomerMapper.modifyCustomer(id, customerName);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }


    @Autowired
    EnterpriseCustomerCodeMapper enterpriseCustomerCodeMapper;

    /**
     * 获取企业客户号段列表
     * @param enterpriseCustomerId
     * @return
     */
    @GetMapping(value = "/customer/prefix/list")
    public Result getEnterpriseCustomerPrefixByEnterpriseId(int enterpriseCustomerId) {
        try {
            List<EnterpriseCustomerCode> list = enterpriseCustomerCodeMapper.getListByEnterpriseCustomer(enterpriseCustomerId);
            return Result.getSuccessResult(list);
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 创建企业客户号段
     * 说明：该功能属于企业管理系统，临时放在核心接口中。在企业管理系统完成后会迁出
     * @param enterpriseCustomerId
     * @param code
     * @return
     */
    @PostMapping(value = "/customer/prefix/create")
    public Result createEnterpriseCustomerPrefix(int enterpriseCustomerId, String code) {
        if (0 < enterpriseCustomerCodeMapper.checkCode(code)) {
            return Result.getFailResult("该编号已被使用。");
        }
        try {
            enterpriseCustomerCodeMapper.create(enterpriseCustomerId, code.substring(0, 2), code);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }



}
