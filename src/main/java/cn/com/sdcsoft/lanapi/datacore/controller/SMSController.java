package cn.com.sdcsoft.lanapi.datacore.controller;

import cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import cn.com.sdcsoft.lanapi.datacore.service.SmsService_En;
import cn.com.sdcsoft.lanapi.datacore.service.SmsService_Zh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信接口
 */
@RestController
@RequestMapping(value = "/datacore/sms")
public class SMSController {
    @Autowired
    SmsService_Zh smsService_zh;
    @Autowired
    SmsService_En smsService_en;

    @RequestMapping(value="/send/zh")
    public Result sendZh(String number){
        return smsService_zh.getSsm(number);
    }

    @RequestMapping(value="/send/en")
    public Result sendEn(String number){
        return smsService_en.getSsm(number);
    }
}
