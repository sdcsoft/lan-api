package cn.com.sdcsoft.lanapi.datacore.controller;

import  cn.com.sdcsoft.lanapi.datacore.entity.db.Device;
import  cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import  cn.com.sdcsoft.lanapi.datacore.mapper.DeviceMapper;
import  cn.com.sdcsoft.lanapi.datacore.mapper.DeviceTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备管理接口
 */
@RestController
@RequestMapping(value = "/datacore/device")
public class DeviceController {
    @Autowired
    DeviceMapper mapper;

    /**
     * 获取控制器列表
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
     * 查找企业的类型列表
     * @param enterpriseId
     * @return
     */
    @GetMapping(value = "/find/enterprise")
    public Result getEnterprise(int enterpriseId) {
        try {
            return Result.getSuccessResult(mapper.findDeviceByEnterpriseId(enterpriseId));
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 查找锅炉厂的设备列表
     * @param customerId
     * @return
     */
    @GetMapping(value = "/find/customer")
    public Result getCustomer(int customerId) {
        try {
            return Result.getSuccessResult(mapper.findDeviceByCustomerId(customerId));
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 根据deviceNo获取可用设备，设备状态非可用时，deviceNo有效也无法拿到数据
     * 专为手机APP/微信小程序提供的添加设备用的查询接口
     * @param deviceNo 加密或非加密的设备编号
     * @return
     */
    @GetMapping(value = "/get/deviceno")
    public Result findByNo(@RequestParam("deviceNo") String deviceNo) {
        try {
            return Result.getSuccessResult(mapper.findByDeviceNo(deviceNo));
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 根据suffix获取可用设备，设备状态非可用时，suffix有效也无法拿到数据
     * 专为手机APP/微信小程序提供的添加设备用的查询接口
     * @param suffix 未加密的设备编号
     * @return
     */
    @GetMapping(value = "/get/suffix")
    public Result findBySuffix(@RequestParam("id") String suffix) {
        try {
            return Result.getSuccessResult(mapper.findBySuffix(suffix));
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }


    /**
     * 根据suffix获取设备信息，设备状态非可用也可获取到数据
     * 专为微信小程序提供的企业内部员工查询设备的接口
     * @param suffix
     * @return
     */
    @GetMapping(value = "/fix/suffix")
    public Result findBySuffixForEuser(String suffix) {
        try {
            return Result.getSuccessResult(mapper.findBySuffixForEnterpriseUser(suffix));
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 根据suffix修改设备信息
     * 专为微信小程序提供的企业内部员工修改设备的接口
     * @param suffix
     * @param prefix 设备类型 1为控制器 2为PLC
     * @param deviceType 设备类型信息
     * @param saleStatus 销售状态 0 未销售 1 已销售
     * @return
     */
    @PostMapping(value = "/fix/modify")
    public Result modifyDevice(String suffix, int prefix, String deviceType, int saleStatus) {
        try {
            mapper.modifyDeviceForEnterpriseUser(suffix, prefix, deviceType, saleStatus);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 批量创建设备
     * @param deviceList  要创建的设备列表
     * @return
     */
    @PostMapping("/create")
    public Result insertManyDevice(@RequestBody List<Device> deviceList) {
        try {
            mapper.addDevice(deviceList);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 根据suffix修改设备类型信息
     * @param deviceType 设备类型信息
     * @param subType 具体类型信息
     * @return
     */
    @PostMapping(value = "/modify/type")
    public Result modifyDeviceType(String suffix, String deviceType, String subType) {
        try {
            mapper.modifyDeviceType(suffix, deviceType, subType);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    /**
     * 获取设备类型列表
     * @return
     */
    @GetMapping(value = "/type/list")
    public Result getDeviceTypeList() {
        try {
            return Result.getSuccessResult(deviceTypeMapper.findAll());
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 创建设备类型
     * @param typeName
     * @return
     */
    @PostMapping(value = "/type/create")
    public Result createDeviceType(String typeName) {
        try {
            if (0 < deviceTypeMapper.checkDeviceType((typeName))) {
                return Result.getFailResult("该设备类型已存在.");
            }
            deviceTypeMapper.addDeviceType(typeName);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }

    /**
     * 修改设备类型
     * @param id
     * @param typeName
     * @return
     */
    @PostMapping(value = "/type/modify")
    public Result modifyDeviceType(int id, String typeName) {
        try {
            deviceTypeMapper.modifyDeviceType(id, typeName);
            return Result.getSuccessResult();
        } catch (Exception ex) {
            return Result.getFailResult(ex.getMessage());
        }
    }




//    @GetMapping(value = "/sell")
//    public List<Device> getSell() {
//        return mapper.findAllByStatus(Device.STATUS_SELL);
//    }
//
//    @GetMapping(value = "/ctl")
//    public List<Device> getCTL() {
//        return mapper.findAllCTLByStatus(Device.STATUS_SELL);
//    }
//
//
//    @GetMapping(value = "/plc")
//    public List<Device> getPLC() {
//        return mapper.findAllPLCByStatus(Device.STATUS_SELL);
//    }


}
