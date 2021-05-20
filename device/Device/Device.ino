#include <WiFi.h>
#include <time.h>
#include <HTTPClient.h>
#include <DHT.h>
#include <ArduinoJson.h>
#include <WebSockets2_Generic.h>
#include "wifiSetup.h"

DHT dht(DHT_PORT, DHT11);

unsigned long lastTime = 0;
unsigned long timerDelay = 10000; //10 sekunders delay

void setup() 
{
  Serial.begin(115200);                  //Init Serial for local debugging
  initWifi();                            //Init Wifi connection
  initSocket();                          //Init Websocket connection
  configTime(3600, 0 , "pool.ntp.org");  //Update device clock from time server
  delay(2000);                           //Just in case, delay 2 seconds for answer from timeserver.
  Serial.print(time(NULL));              
  dht.begin();
}

void loop() 
{
  if ((millis() - lastTime) > timerDelay) 
  {
    if(WiFi.status()== WL_CONNECTED && connected)
    {
      char serializedMessage[255];
      char tempString[10];
      char humidityString[10];
      char timeString[15];
      
      HTTPClient http;
      http.begin(HTTP_SERVER_ADRESS);
      
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
      http.addHeader("Content-Type", "application/json");
      int httpResponseCode = http.POST(serializedMessage); 
      client.send(serializedMessage);
      Serial.println("Could not send message to websocket, trying to reconnect...");
      Serial.println(serializedMessage);
      Serial.print("HTTP Respone: ");
      Serial.println(httpResponseCode);
      http.end();
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
    else if (!connected)
    {
      Serial.println("Websocket not Connected... Trying to reconnect...");
      initSocket();                          //Init Websocket connection
      delay(2000);                         
    }
    Serial.print("Available?:");
    Serial.println(client.available(1));

    lastTime = millis();
  }
}
