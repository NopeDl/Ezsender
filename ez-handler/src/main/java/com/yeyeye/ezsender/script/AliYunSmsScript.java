package com.yeyeye.ezsender.script;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponseBody;
import com.yeyeye.ezsender.pojo.SmsInfo;
import com.yeyeye.ezsender.pojo.SmsRecord;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author yeyeye
 * @Date 2023/4/5 23:21
 */
@Component
public class AliYunSmsScript implements SmsScript {
    @Value("${spring.sms.ali.accessKeyId}")
    private String accessKeyId;
    @Value("${spring.sms.ali.accessKeySecret}")
    private String accessKeySecret;

    @Override
    public SmsRecord send(SmsInfo info) {
        try (AsyncClient client = initClient();) {
            SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                    .signName(info.getSignName())
                    .templateCode(info.getTemplateCode())
                    .phoneNumbers(info.getPhoneNumbers())
                    .templateParam(info.getTemplateParam())
                    .smsUpExtendCode(info.getSmsUpExtendCode())
                    .outId(info.getOutId())
                    .build();
            CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
            SendSmsResponseBody body = response.get().getBody();
            return SmsRecord.builder()
                    .code(body.getCode())
                    .message(body.getMessage())
                    .bizId(body.getBizId())
                    .requestId(body.getRequestId()).build();
        } catch (InterruptedException | ExecutionException e) {
            return new SmsRecord(e.getMessage());
        }
    }

    private AsyncClient initClient() {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(accessKeyId)
                .accessKeySecret(accessKeySecret)
                .build());

        return AsyncClient.builder()
                .region("cn-hangzhou")
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();
    }
}
