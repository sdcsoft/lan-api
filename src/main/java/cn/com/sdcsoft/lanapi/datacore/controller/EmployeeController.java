package cn.com.sdcsoft.lanapi.datacore.controller;

import cn.com.sdcsoft.lanapi.datacore.entity.db.Employee;
import cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import cn.com.sdcsoft.lanapi.datacore.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 注册用户管理接口
 */
@RestController
@RequestMapping(value = "/datacore/employee")
public class EmployeeController {

    @Autowired
    private EmployeeMapper mapper;

    /**
     * 获取注册用户列表
     *
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
     * 创建用户
     *
     * @param employee
     * @return
     */
    @PostMapping(value = "/create")
    public Result create(@RequestBody Employee employee) {
        try {
            Employee checkEmployee = mapper.findOneByLoginId(employee.getMobile());
            if (null != checkEmployee) {
                return Result.getFailResult("账号已存在.");
            } else {
                employee.setStatus(Employee.STATUS_ENABLE);
                mapper.addEmployee(employee);
                return Result.getSuccessResult(employee);
            }
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }


    /**
     * 根据注册手机号/邮箱查询用户信息
     *
     * @param loginId
     * @return
     */
    @GetMapping(value = "/find")
    public Result findEmployee(String loginId) {
        Employee employee = mapper.findOneByLoginId(loginId);
        if (null != employee) return Result.getSuccessResult(employee);
        return Result.getFailResult("未查询到相关用户信息");
    }

    /**
     * 根据注册手机号/邮箱查询用户信息
     *
     * @param openId
     * @return
     */
    @GetMapping(value = "/wechat")
    public Result findWeChatEmployee(String openId) {
        Employee employee = mapper.findOneByWechat(openId);
        if (null == employee) {
            return Result.getFailResult("微信尚未绑定！");
        }
        return Result.getSuccessResult(employee);
    }

    /**
     * 根据注册手机号/邮箱查询用户信息
     *
     * @param loginId
     * @return
     */
    @GetMapping(value = "/find/company")
    public Result findCompanyEmployee(String loginId) {
        try {
            return Result.getSuccessResult(mapper.findOneByLoginId(loginId));
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 根据注册手机号/邮箱查询用户信息
     *
     * @param loginId
     * @return
     */
    @GetMapping(value = "/find/customer")
    public Result findCustomerEmployee(String loginId) {
        try {
            return Result.getSuccessResult(mapper.findCustomerEmployeeLoginId(loginId));
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 根据注册手机号/邮箱查询用户信息
     *
     * @param loginId
     * @return
     */
    @GetMapping(value = "/find/agent")
    public Result findAgentEmployee(String loginId) {
        try {
            return Result.getSuccessResult(mapper.findAgentEmployeeLoginId(loginId));
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 根据注册手机号/邮箱查询用户信息
     *
     * @param loginId
     * @return
     */
    @GetMapping(value = "/find/enduser")
    public Result findEndUserEmployee(String loginId) {
        try {
            return Result.getSuccessResult(mapper.findEndUserEmployeeLoginId(loginId));
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

//    @PostMapping(value = "/devices")
//    public List<DeviceEmployeeMapViewForDevice> getManageDevices(@RequestParam("employeeId") int employeeId) {
//        return demDao.findEmployeeDevices(employeeId);
//    }

    /**
     * 修改用户信息
     * 说明：核心管理平台专用接口
     *
     * @param employee
     * @return
     */
    @PostMapping(value = "/modify")
    public Result modifyEmployee(Employee employee) {
        try {
            mapper.modifyEmployee(employee);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 修改用户组织信息
     * 说明：核心管理平台专用接口
     * @param employeeId
     * @param orgType
     * @param orgId
     * @return
     */
    @PostMapping(value = "/modify/org")
    public Result modifyEmployeeOrg(@RequestParam Integer employeeId,@RequestParam int orgType,@RequestParam int orgId) {
        try {
            mapper.modifyEmployeeOrg(employeeId,orgType,orgId);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 绑定微信
     * 说明：微信专用接口
     *
     * @param loginId
     * @param openId
     * @return
     */
    @PostMapping(value = "/wechat/bind")
    public Result bindWechat(String loginId, String openId) {
        try {
            mapper.bindWechat(loginId, openId);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }



    /**
     * 修改用户状态
     * 说明：核心管理平台专用接口
     *
     * @param loginId
     * @param status
     * @return
     */
    @PostMapping(value = "/change/status")
    public Result modifyEmployeeStatus(String loginId, int status) {
        try {
            mapper.changeEmployeeStatus(loginId, status);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 修改基本信息
     * 说明：微信或平台用户修改基本信息的接口
     *
     * @param loginId
     * @param mobile
     * @param email
     * @param realName
     * @return
     */
    @PostMapping(value = "/change/infos")
    public Result changeEmployeeInfos(String loginId, String mobile, String email, String qq, String realName) {
        try {
            mapper.changeEmployeeInfos(loginId, mobile, email, qq, realName);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 修改密码
     *
     * @param loginId
     * @param password
     * @return
     */
    @PostMapping(value = "/change/user/password")
    public Result changeEmployeePassword(String loginId, String password) {
        try {
            mapper.changeEmployeePassword(loginId, password);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    @PostMapping(value = "/change/password")
    public Result changePassword(Integer id, String password) {
        try {
            mapper.changePassword(id, password);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 将用户变更为普通微信用户
     * 说明：提供锅炉厂、代理商、终端用户进行员工删除的后续操作
     *
     * @param loginId
     * @return
     */
    @PostMapping(value = "/free")
    public Result freeEmployee(String loginId) {
        try {
            mapper.freeEmployee(loginId);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

}
