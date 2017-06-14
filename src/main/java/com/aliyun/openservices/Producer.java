package com.aliyun.openservices;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.aliyun.openservices.ons.api.impl.authority.SessionCredentials;
import com.aliyun.openservices.ons.api.impl.rocketmq.ClientRPCHook;

public class Producer {

    public static void main(String[] args) throws Exception {
        SessionCredentials sessionCredentials = new SessionCredentials();
        //这里传入阿里云账户的AccessKey，SecretKey，身份验证以及鉴权使用
        sessionCredentials.setAccessKey("XXX");
        sessionCredentials.setSecretKey("XXX");
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("PID_RocketMQTest", new ClientRPCHook(sessionCredentials));
        //云上该参数需要设置为false
        defaultMQProducer.setVipChannelEnabled(false);
        //通过url获取NameServer地址
        //公有云生产环境：http://onsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
        //公有云公测环境：http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet
        //杭州金融云环境：http://jbponsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
        //杭州深圳云环境：http://mq4finance-sz.addr.aliyun.com:8080/rocketmq/nsaddr4client-internal
        //此处以公有云公测环境为例
        String url = "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet";
        defaultMQProducer.setNamesrvAddr(HttpTinyClient.fetchNamesrvAddress(url));
        //启动生产者
        defaultMQProducer.start();
        //发送消息，暂时不支持使用RocketMQ发送定时消息与事务消息到云端，如有需要请使用云上客户端OnsClient
        Message message = new Message("RocketMQTopicTest", "Hello World".getBytes());
        SendResult sendResult = defaultMQProducer.send(message);
        System.out.println(sendResult);
        //停止生产者
        defaultMQProducer.shutdown();
    }
}
