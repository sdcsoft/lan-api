package cn.com.sdcsoft.lanapi.datacore.controller;

import cn.com.sdcsoft.lanapi.datacore.entity.db.Agent;
import  cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import  cn.com.sdcsoft.lanapi.datacore.mapper.AgentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代理商管理
 * 核心数据库中仅存储代理商基本信息与用户映射关系
 * 代理商产品、员工、客户均由锅炉厂数据库存储
 */

@RestController
@RequestMapping(value = "/datacore/agent")
public class AgentController {
    @Autowired
    AgentMapper mapper;

    /**
     * 获取代理商列表
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
    public Result getAgentById(int id) {
        try {
            Agent agent = mapper.findOneById(id);
            if(null == agent){
                return Result.getFailResult("不存在该组织！");
            }
            return Result.getSuccessResult(agent);
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 创建代理商
     * @param agentName
     * @param status
     * @return
     */
    @PostMapping(value = "/create")
    public Result create(String agentName, int status) {
        try {
            if (0 < mapper.checkAgent(agentName)) {
                return Result.getFailResult("该代理商已存在.");
            }
            mapper.addAgent(agentName, status);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }

    }

    /**
     * 修改代理商信息
     * @param id
     * @param agentName
     * @param status
     * @return
     */
    @PostMapping(value = "/modify")
    public Result modify(int id,String agentName,int status) {
        try {
            mapper.modifyAgent(id,agentName,status);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }
}
