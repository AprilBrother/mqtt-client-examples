package com.testm;

import java.net.URISyntaxException;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Future;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;


public class MClient {  
    //private final static String CLIENT_ID = "publishService";  
    public  static Topic[] topics = {  
        new Topic("/beacons", QoS.AT_MOST_ONCE)};  //AT_LEAST_ONCE

    public static void main(String[] args)   {  
        //创建MQTT对象  
        MQTT mqtt = new MQTT();  
        try {  
            //设置mqtt broker的ip和端口  
            mqtt.setHost("tcp://192.168.1.123:1883");
            // 原来的值：876543210，用于设置客户端会话的ID。在setCleanSession(false);被调用时，MQTT服务器利用该ID获得相应的会话。此ID应少于23个字符，默认根据本机地址、端口和时间自动生成
            mqtt.setClientId("beacons"); 
            //连接前清空会话信息,  若设为false，MQTT服务器将持久化客户端会话的主体订阅和ACK位置，默认为true
            mqtt.setCleanSession(true);  
            //设置重新连接的次数  
            mqtt.setReconnectAttemptsMax(6);  
            //设置重连的间隔时间  
            mqtt.setReconnectDelay(2000);  
            //设置心跳时间  
            mqtt.setKeepAlive((short)30);  // 低耗网络，但是又需要及时获取数据，心跳30s 
            //设置缓冲的大小  
            mqtt.setSendBufferSize(2*1024*1024);  //发送最大缓冲为2M 

            //获取mqtt的连接对象BlockingConnection  
            //                final FutureConnection connection= mqtt.futureConnection();  
            BlockingConnection connection = mqtt.blockingConnection();  
            connection.connect(); 
            if(connection.isConnected()){
                System.out.println("连接成功");
            }else{
                System.out.println("连接失败");
            }
            byte[] qoses = connection.subscribe(topics);  
            Message message = connection.receive();
            System.out.println("getTopic:"+message.getTopic());  
            byte[] payload = message.getPayload();  
            message.ack();  
            while(true){  
                Thread.sleep(2000);
                //                    System.out.println("messages:"+messages);
                //                    System.out.println("getTopic:"+message.getTopic());
                //                    System.out.println("getPayload:"+message.getPayload());
                //                    System.out.println("getPayloadBuffer:"+message.getPayloadBuffer());
                //                    System.out.println("getTopicBuffer:"+message.getTopicBuffer());
                System.out.println("MQTTFutureClient.Receive Message "+ "Topic Title :"+message.getTopic()+" context :"+String.valueOf(message.getPayloadBuffer()));  
            }  
        } catch (URISyntaxException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  

        }  
    }  
}  
