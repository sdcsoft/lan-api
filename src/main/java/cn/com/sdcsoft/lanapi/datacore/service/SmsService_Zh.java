package cn.com.sdcsoft.lanapi.datacore.service;

import cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService_Zh {
    //产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static final String accessKeyId = "LTAIf5XDtOqoSIgo";
    private static final String accessKeySecret = "TsYsEk9UxpcqgbJWNdh5hgdNfU09YO";

    private static final String signName = "sdcsoft";
    //必填:短信模板-可在短信控制台中找到，你模板管理里的模板编号
     private static final String templateCode = "SMS_133967525";

    @Autowired
    AliyunSmsService smsService;
    public Result getSsm(String number) {
        return smsService.getSsm(number,domain,product,accessKeyId,accessKeySecret,signName,templateCode);
    }
}
