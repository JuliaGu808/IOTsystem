#include <WiFi.h>
#include <time.h>
#include <HTTPClient.h>
#include <DHT.h>
#include <ArduinoJson.h>
#include <WebSockets2_Generic.h>
#include "wifiSetup.h"
#include "hidden.h"

const char* serverName = SERVER_NAME;

unsigned long lastTime = 0;
unsigned long timerDelay = 10000; //10 sekunders delay
//using namespace websockets2_generic;

//WebsocketsClient client;
//bool connected = false;

DHT dht(DHTPORT, DHT11);

void onEventsCallback(WebsocketsEvent event, String data) 
{
  if (event == WebsocketsEvent::ConnectionOpened) 
  {
    Serial.println("Connnection Opened");
  } 
  else if (event == WebsocketsEvent::ConnectionClosed) 
  {
    Serial.println("Connnection Closed");
  } 
  else if (event == WebsocketsEvent::GotPing) 
  {
    Serial.println("Got a Ping!");
  } 
  else if (event == WebsocketsEvent::GotPong) 
  {
    Serial.println("Got a Pong!");
  }
}


void setup() 
{
  Serial.begin(115200);
  initWifi(); 
  Serial.println("Wifi OK");
  initSocket();
  configTime(3600, 0 , "pool.ntp.org");  //Uppdatera klockan mot time-server.
  Serial.print(time(NULL));
  dht.begin();
  Serial.print(dht.readTemperature());
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
      http.begin(serverName);
      
      DynamicJsonDocument message(1024);
      message["deviceName"] = DEVICE_NAME;
      message["deviceHolder"]   = DEVICE_HOLDER;
      dtostrf(dht.readTemperature(), 4, 2, tempString); 
      dtostrf(dht.readHumidity(), 4, 2, humidityString);  
      message["temperature"] = tempString;
      message["humidity"] = humidityString;
      int sweSummerTime = time(NULL) + (3600 * 2);
      sprintf (timeString, "%i", (time(NULL) + (3600 * 2)));
      message["recordtime"] = timeString;

      serializeJson(message, serializedMessage);
      http.addHeader("Content-Type", "application/json");
      //int httpResponseCode = http.POST("{\"deviceName\":\"xxxxxx\",\"deviceHolder\":\"zzzzz\",\"temperature\":\"bbbbb\",\"humidity\":\"dddddd\",\"timestamp\":\"hhhhh\"}");
      int httpResponseCode = http.POST(serializedMessage);
      client.send(serializedMessage);
      Serial.println(serializedMessage);
      Serial.print("HTTP Respone: ");
      Serial.println(httpResponseCode);
      http.end();
    }
    else 
    {
      Serial.println("WiFi not Connected...");
    }
    lastTime = millis();
  }
}
/*
{
  "deviceName": "String",
  "deviceHolder": "String",
  "temperature": "String",
  "humidity": "String",
  "timestamp": "String"    //Sensor measurement timestamp
}
*/
