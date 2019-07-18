package cn.com.sdcsoft.lanapi.datacore.controller;

import  cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import  cn.com.sdcsoft.lanapi.datacore.mapper.OrgTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织类型管理接口
 */
@RestController
@RequestMapping(value = "/datacore/org")
public class OrgTypeController {
    @Autowired
    OrgTypeMapper orgTypeMapper;

    /**
     * 获取组织类型列表
     * @return
     */
    @GetMapping("/list")
    public Result getAll() {
        try {
            return Result.getSuccessResult(orgTypeMapper.getTypeList());
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 修改组织类型信息，只能修改类型名称
     * @param orgType
     * @param orgTypeName
     * @return
     */
    @PostMapping("/modify")
    public Result modify(int orgType, String orgTypeName) {
        try {
            orgTypeMapper.modifyOrgType(orgType, orgTypeName);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 创建组织类型
     * @param orgType
     * @param orgTypeName
     * @return
     */
    @PostMapping("/create")
    public Result create(int orgType, String orgTypeName) {
        try {
            if (0 < orgTypeMapper.checkOrgType(orgType)) {
                return Result.getFailResult("该组织编号已被占用！");
            }
            orgTypeMapper.addOrgType(orgType, orgTypeName);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }


}
