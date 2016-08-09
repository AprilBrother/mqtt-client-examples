import time
import paho.mqtt.client as mqtt

def on_subscribe(mosq, obj, mid, granted_qos):
    print("Subscribed: " + str(mid))

def on_message(mosq, obj, msg):
    print(msg.topic + " " + str(msg.qos) + " " + str(msg.payload))

def on_connect(mosq, obj, rc):
    mqttc.subscribe("/beacons", 0)
    print("Connected")

mqttc = mqtt.Client()
mqttc.on_connect = on_connect
mqttc.on_subscribe = on_subscribe
mqttc.on_message = on_message
mqttc.connect("mqtt.bconimg.com", 1883, 60)
mqttc.loop_forever()

