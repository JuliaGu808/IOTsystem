#include "hidden.h"
////////////////////////////////////////////////////
// hidden.h should contain following defines modified for your system.
////////////////////////////////////////////////////
//#define SSID_NAME "ssid"   
//#define SSID_PASSWORD "wifiPassword"
//#define WEBSOCKET_PORT 8080
//#define WEBSOCKET_SERVER_ADRESS "192.168.1.10"
//#define WEBSOCKET_ADRESS_PATH "/device"
//#define HTTP_SERVER_ADRESS "192.168.1.5"
//#define DEVICE_NAME "Devicename"
//#define DEVICE_HOLDER "Holdername"
//#define DHT_PORT 32
//
////////////////////////////////////////////////////

using namespace websockets2_generic;
WebsocketsClient client;

bool connected = false;

/////WiFi function to connect to your local WiFi
void initWifi() 
{
  unsigned int attempt = 1;
  WiFi.begin(SSID_NAME, SSID_PASSWORD);
  while(WiFi.status() != WL_CONNECTED) 
  {
    delay(500);
    Serial.print("Connection attempt:  ");
    Serial.println(attempt);
    attempt++;
  }
  Serial.print("Connected to WiFi, recieved IP: ");
  Serial.println(WiFi.localIP());
}

/////////
/////Websocket Callback function
void onEventsCallback(WebsocketsEvent event, String data) 
{
  if (event == WebsocketsEvent::ConnectionOpened) 
  {
    Serial.println("Connnection Opened");
  } 
  else if (event == WebsocketsEvent::ConnectionClosed) 
  {
    Serial.println("Connnection Closed");
    connected=false;
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

/////Initialize websocket
void initSocket()
{
  connected = client.connect(WEBSOCKET_SERVER_ADRESS, WEBSOCKET_PORT, WEBSOCKET_ADRESS_PATH);
  client.onEvent(onEventsCallback);
  if (connected) 
  {
    Serial.println("-----------Connected!");
  } 
  else 
  {
    Serial.println("Websocket Not Connected!");
  }
}
