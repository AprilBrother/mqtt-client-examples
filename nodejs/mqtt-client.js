var mqtt=require('mqtt');
var client  = mqtt.connect('mqtt://mqtt.bconimg.com');
 
client.on('connect', function () {
  client.subscribe('/beacons');
});
 
client.on('message', function (topic, message) {
     // message is Buffer 
      console.log(message.toString());
});
