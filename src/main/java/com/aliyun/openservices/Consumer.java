package com.aliyun.openservices;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.aliyun.openservices.ons.api.impl.authority.SessionCredentials;
import com.aliyun.openservices.ons.api.impl.rocketmq.ClientRPCHook;
import java.util.List;

public class Consumer {
    public static void main(String[] args) throws Exception {
        SessionCredentials sessionCredentials = new SessionCredentials();
        //这里传入阿里云账户的AccessKey，SecretKey，身份验证以及鉴权使用
        sessionCredentials.setAccessKey("XXX");
        sessionCredentials.setSecretKey("XXX");
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("CID_RocketMQTest", new ClientRPCHook(sessionCredentials), new AllocateMessageQueueAveragely());
        //云上该参数需要设置为false
        defaultMQPushConsumer.setVipChannelEnabled(false);
        //通过url获取NameServer地址
        //公有云生产环境：http://onsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
        //公有云公测环境：http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet
        //杭州金融云环境：http://jbponsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
        //杭州深圳云环境：http://mq4finance-sz.addr.aliyun.com:8080/rocketmq/nsaddr4client-internal
        //此处以公有云公测环境为例
        String url = "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet";
        defaultMQPushConsumer.setNamesrvAddr(HttpTinyClient.fetchNamesrvAddress(url));
        //订阅topic
        defaultMQPushConsumer.subscribe("RocketMQTopicTest", "*");
        //注册消息监听器
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                for (MessageExt ext: list) {
                    System.out.println(ext);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //启动生产者
        defaultMQPushConsumer.start();
    }
}
