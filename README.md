## RocketMQ 2 Cloud
	
### 接入说明
   该Demo旨在帮助RocketMQ的用户无缝迁移到[阿里云上RocketMQ](https://www.aliyun.com/product/ons?spm=5176.8142029.388261.288.65o5Wc)
   
1. 本Demo使用RocketMQ Clinet 3.6.2，如需使用更高版本，则参照本Demo改(需要重写RPCHook，将com.alibaba的包替换成org.apache) 
2. 使用之前需要先申请Topic, Producer ID, Consumer ID，详情请参考[申请MQ资源](https://help.aliyun.com/document_detail/29536.html?spm=5176.doc29546.2.2.gWIToO)
3. 如果没有申请相关资源，则会直接导致鉴权失败
4. 暂时不支持云上的定时消息与事务消息，如需使用请利用[云上专用客户端OnsClient](https://help.aliyun.com/document_detail/52591.html?spm=5176.doc44711.6.585.khZCA4)
5. 欢迎访问[RocketMQ官网](https://rocketmq.apache.org/)查阅更多使用资料
6. 欢迎加钉钉群咨询，用钉钉扫描[群二维码](http://img3.tbcdn.cn/5476e8b07b923/TB1esD1RFXXXXb4aXXXXXXXXXXX)


	


