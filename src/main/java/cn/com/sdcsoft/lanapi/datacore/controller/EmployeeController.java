package cn.com.sdcsoft.lanapi.datacore.controller;

import  cn.com.sdcsoft.lanapi.datacore.entity.db.Employee;
import  cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import  cn.com.sdcsoft.lanapi.datacore.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param employee
     * @return
     */
    @PostMapping(value = "/create")
    public Result create(Employee employee) {
        try {
            Employee checkEmployee = mapper.findOneByLoginId(employee.getMobile());
            if (null != checkEmployee) {
                return Result.getFailResult("账号已存在.");
            } else {
                employee.setStatus(Employee.STATUS_ENABLE);
                mapper.addEmployee(employee);
                return Result.getSuccessResult();
            }
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

//    @PostMapping(value = "/search")
//    public List<Employee> search(@RequestParam(name = "orgType") int orgType, @RequestParam(name = "orgId") int orgId) {
//        return mapper.findEmployeesByOrg(orgType, orgId);
//    }

    /**
     * 根据注册手机号/邮箱查询用户信息
     * @param loginId
     * @return
     */
    @GetMapping(value = "/find")
    public Result findEmployee(String loginId) {
        try {
            return Result.getSuccessResult(mapper.findOneByLoginId(loginId));
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
     * 修改用户状态
     * 说明：核心管理平台专用接口
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
     * @param loginId
     * @param mobile
     * @param email
     * @param realName
     * @return
     */
    @PostMapping(value = "/change/infos")
    public Result changeEmployeeInfos(String loginId,String mobile, String email, String qq,String realName) {
        try {
            mapper.changeEmployeeInfos(loginId, mobile,email,qq,realName);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 修改密码
     * @param loginId
     * @param password
     * @return
     */
    @PostMapping(value = "/change/password")
    public Result changeEmployeePassword(String loginId, String password) {
        try {
            mapper.changeEmployeePassword(loginId, password);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

}
