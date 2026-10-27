/*
  SmartFind AIoT - ESP8266 collection-point event demo.
  Configure WIFI_SSID, WIFI_PASSWORD, and SERVER_URL before testing.
  The receiving endpoint is a placeholder: implement device authentication,
  payload validation, idempotency, and HTTPS for any real deployment.
*/
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>

const char* WIFI_SSID = "YOUR_WIFI_NAME";
const char* WIFI_PASSWORD = "YOUR_WIFI_PASSWORD";
const char* SERVER_URL = "http://192.168.1.10:8080/api/intake-events";

const char* DEVICE_ID = "sf-esp-01";
const long DEMO_REPORT_ID = 502;
unsigned long lastSend = 0;

String makeEventId() {
  // Demonstration only. For robust deployments use a UUID library or
  // persistent sequence + device ID to avoid collisions after reboot.
  return String(DEVICE_ID) + "-" + String(ESP.getChipId(), HEX) + "-" + String(millis());
}

void setup() {
  Serial.begin(115200);
  delay(200);
  WiFi.mode(WIFI_STA);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wi-Fi");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println();
  Serial.print("Connected; IP: ");
  Serial.println(WiFi.localIP());
}

void loop() {
  // Send one demo intake event every 30 seconds. Replace with a button,
  // barcode/QR scan, or staff-triggered action for the actual collection point.
  if (millis() - lastSend >= 30000UL) {
    lastSend = millis();
    sendIntakeEvent();
  }
}

void sendIntakeEvent() {
  if (WiFi.status() != WL_CONNECTED) {
    Serial.println("Wi-Fi unavailable; event not sent.");
    return;
  }

  WiFiClient client;
  HTTPClient http;
  if (!http.begin(client, SERVER_URL)) {
    Serial.println("Could not initialize HTTP request.");
    return;
  }
  http.addHeader("Content-Type", "application/json");
  String eventId = makeEventId();
  String body = "{\"event_id\":\"" + eventId +
                "\",\"device_id\":\"" + String(DEVICE_ID) +
                "\",\"report_id\":" + String(DEMO_REPORT_ID) +
                ",\"event_type\":\"ITEM_RECEIVED\"}";
  int code = http.POST(body);
  Serial.printf("Event %s -> HTTP %d\n", eventId.c_str(), code);
  if (code > 0) Serial.println(http.getString());
  http.end();
}
