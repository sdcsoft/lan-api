package cn.com.sdcsoft.lanapi.datacore.controller;

import cn.com.sdcsoft.lanapi.datacore.entity.db.EndUser;
import  cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import  cn.com.sdcsoft.lanapi.datacore.mapper.EndUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 终端用户接口
 * 注意：终端用户是一个组织，类似与企业
 */
@RestController
@RequestMapping(value = "/datacore/enduser")
public class EndUserController {
    @Autowired
    EndUserMapper mapper;

    /**
     * 获取终端用户列表
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

    @GetMapping(value = "/find")
    public Result getEndUserById(int id) {
        try {
            EndUser endUser = mapper.findOneById(id);
            if(null == endUser){
                return Result.getFailResult("不存在该组织！");
            }
            return Result.getSuccessResult(endUser);
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 创建终端用户
     * @param endUserName
     * @param status
     * @return
     */
    @PostMapping(value = "/create")
    public Result create(String endUserName, int status) {
        try {
            if (0 < mapper.checkEndUser(endUserName)) {
                return Result.getFailResult("该终端用户已存在.");
            }
            mapper.addEndUser(endUserName, status);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 修改终端用户
     * @param id
     * @param endUserName
     * @param status
     * @return
     */
    @PostMapping(value = "/modify")
    public Result modify(int id, String endUserName, int status) {
        try {
            mapper.modifyEndUser(id, endUserName, status);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }


}
