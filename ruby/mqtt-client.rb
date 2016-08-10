require 'rubygems'
require 'mqtt'


#client=MQTT::Client.new
#client.host='mqtt.bconimg.com'
#client.connect()
#client.subscribe('/beacons')
#
#client.get do |topic,message|
#	puts message;
#end


MQTT::Client.connect('mqtt.bconimg.com') do |c|
     c.get('/beacons') do |topic,message|
         puts "#{topic}: #{message}"
     end
end
