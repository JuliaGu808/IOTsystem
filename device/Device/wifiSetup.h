#include "hidden.h"

const char* ssid = SSID_NAME;
const char* password = SSID_PASSWORD;
unsigned int attempt = 1;
bool connected = false;
using namespace websockets2_generic;

WebsocketsClient client;

void initWifi()
{
 WiFi.begin(ssid, password);
  Serial.print("Trying to connect to WiFi with SSID: ");
  Serial.println(ssid);
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

void initSocket(){
  const char* websockets_server_host = "192.168.1.10"; //Enter server address
  connected = client.connect(websockets_server_host, 8080, "/device");
  
  if (connected) 
  {
    Serial.println("-----------Connected!");
  } 
  else 
  {
    Serial.println("Not Connected!");
  }

}
