<?php


$mqttclient=new Mosquitto\Client;

$mqttclient->onConnect(function() use ($mqttclient){
	echo "connect success \n";
	$mqttclient->subscribe('/beacons',0);
});

$mqttclient->onMessage(function($message)use($mqttclient){
	var_dump($message);
	echo "=================================\n";
});

$mqttclient->onLog(function($level,$msg)use($mqttclient){
//	echo "\n";
//	echo $msg;
//	echo "\n";
});

$mqttclient->connect('mqtt.bconimg.com',1883,60);

$mqttclient->loopforever();
