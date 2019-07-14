package cn.com.sdcsoft.lanapi.datacore.service;

import cn.com.sdcsoft.lanapi.datacore.entity.web.Result;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.stereotype.Service;

@Service
public class AliyunSmsService {
    public Result getSsm(String number, String domain,String product,String accessKeyId,String accessKeySecret,String signName,String tempCode) {

        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //随机生成六位验证码
            int code = (int)((Math.random()*9+1)*100000);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest requestt = new SendSmsRequest();
            //必填:待发送手机号
            requestt.setPhoneNumbers(number);
            //必填:短信签名-可在短信控制台中找到，你在签名管理里的内容

            requestt.setSignName(signName);
            //必填:短信模板-可在短信控制台中找到，你模板管理里的模板编号
            requestt.setTemplateCode(tempCode);

            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            requestt.setTemplateParam("{\"code\":\""+code+"\"}");
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(requestt);
            String resultCode = sendSmsResponse.getCode();
            if(resultCode != null && "OK".equals(resultCode)){//发送成功
                return Result.getSuccessResult(code);
            }else{
                return Result.getFailResult(sendSmsResponse.getMessage());
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return Result.getFailResult(e.getErrMsg());
        }
    }
}
