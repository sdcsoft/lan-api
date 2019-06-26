package cn.com.sdcsoft.lanapi.datacore.controller;

import  cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import  cn.com.sdcsoft.lanapi.datacore.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 公司管理接口
 */
@RestController
@RequestMapping(value = "/datacore/company")
public class CompanyController {
    @Autowired
    CompanyMapper mapper;

    /**
     * 获取公司列表
     * @return
     */
    @GetMapping(value = "/list")
    public Result getAllCompany() {
        try {
            return Result.getSuccessResult(mapper.findAll());
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 创建公司
     * @param companyName
     * @param status
     * @return
     */
    @PostMapping(value = "/create")
    public Result create(String companyName,int status) {
        try {
            mapper.addCompany(companyName,status);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 修改公司信息
     * @param id
     * @param companyName
     * @param status
     * @return
     */
    @PostMapping(value = "/modify")
    public Result modifyCompany(int id,String companyName,int status) {
        try {
            mapper.modifyCompany(id,companyName,status);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

//    @PostMapping(value = "/change")
//    public Result modifyCompanyStatus(Company company) {
//        try {
//            mapper.changeCompanyStatus(company);
//            return Result.getSuccessResult();
//        } catch (Exception ex) {
//            return Result.getFailResult(ex.getMessage());
//        }
//    }
}
