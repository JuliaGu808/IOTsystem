//#include <WiFi.h>
#include <time.h>
#include <HTTPClient.h>
#include <DHT.h>
#include <ArduinoJson.h>
#include <WebSockets2_Generic.h>
#include "wifiSetup.h"

DHT dht(DHT_PORT, DHT11);

unsigned long lastTime = 0;
unsigned long timerDelay = 1000; //10 sekunders delay

void setup() 
{
  Serial.begin(115200);                  //Init Serial for local debugging
  initWifi();                            //Init Wifi connection
  initSocket();                          //Init Websocket connection
  configTime(3600, 0 , "pool.ntp.org");  //Update device clock from time server
  delay(2000);                           //Just in case, delay 2 seconds for answer from timeserver.
  Serial.print(time(NULL));              
  dht.begin();

  client.onMessage([&](WebsocketsMessage message) 
  {
    Serial.print("Got Message: ");
    Serial.println(message.data());
  });
}

void loop() 
{
  if ((millis() - lastTime) > timerDelay) 
  {
    if(WiFi.status()== WL_CONNECTED && connected)
    {
      //initSocket();      
      char serializedMessage[255];
      char tempString[10];
      char humidityString[10];
      char timeString[15];
    
      
      DynamicJsonDocument message(1024);
      message["deviceName"] = DEVICE_NAME;
      message["deviceHolder"]   = DEVICE_HOLDER;
      dtostrf(dht.readTemperature(), 4, 2, tempString); 
      dtostrf(dht.readHumidity(), 4, 2, humidityString);
      if(!(strcmp("nan",tempString)) || !(strcmp("nan",humidityString)))
      {
        Serial.println("Felaktigt värde från sensor.");
      }
      else
      {
      message["temperature"] = tempString;
      message["humidity"] = humidityString;
      sprintf (timeString, "%u", (time(NULL) + (3600 * 2)));
      message["recordtime"] = timeString;

      serializeJson(message, serializedMessage);
     
      client.send(serializedMessage);
      
      Serial.println(serializedMessage);
     
  
      }
    }
    else if ((WiFi.status()== WL_CONNECTION_LOST) || (WiFi.status()== WL_DISCONNECTED))
    {
      Serial.println("WiFi not Connected... Trying to reconnect...");
      initWifi();
      initSocket();                          //Init Websocket connection
      configTime(3600, 0 , "pool.ntp.org");  //Update device clock from time server
      delay(2000);                         
    }
    lastTime = millis();
    if (client.available()) 
    {
    client.poll();
    }
  }
}
