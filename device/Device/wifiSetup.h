#include "hidden.h"

const char* ssid = SSID_NAME;
const char* password = SSID_PASSWORD;
unsigned int attempt = 1;

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
