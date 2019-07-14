package cn.com.sdcsoft.lanapi.datacore.service;

import cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService_En {
    //产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static final String accessKeyId = "LTAI3DHhrESK08aa";
    private static final String accessKeySecret = "vcd5FwZMzCPEQSqXoYFKN7Tb8PyPZV";

    //必填:短信签名-可在短信控制台中找到，你在签名管理里的内容
    private static final String signName = "山东简洁软件有限公司国际";
    //必填:短信模板-可在短信控制台中找到，你模板管理里的模板编号
    private static final String templateCode = "SMS_166375959";
    @Autowired
    AliyunSmsService smsService;

    public Result getSsm(String number) {
        return smsService.getSsm(number,domain,product,accessKeyId,accessKeySecret,signName,templateCode);
    }
}
