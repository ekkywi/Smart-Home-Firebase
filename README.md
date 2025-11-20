# 🏡 SmartHome Firebase — IoT Mobile App (Android + Firebase + Node.js Worker)
SmartHome Firebase adalah aplikasi Android berbasis **Jetpack Compose & Clean Architecture**, yang digunakan untuk mengontrol perangkat IoT (Smart Lamp, AC, Fan, Smart Door, dll).  
Aplikasi ini tidak memerlukan hardware fisik karena menggunakan **Firebase Realtime Database** dan **Node.js IoT Worker** sebagai simulator perangkat IoT.

---

## ✨ Fitur Utama

| Fitur | Status |
|-------|-------|
| Realtime Sync Firebase | ✔️ Siap pakai |
| Control Smart Devices (ON/OFF) | ✔️ |
| Control AC Temperature (Slider) | ✔️ |
| Control Fan Speed (Slider) | ✔️ |
| Multi-Room Layout | ✔️ |
| IoT Worker Simulation (No Hardware Required) | ✔️ |
| Clean Architecture MVVM | ✔️ |

---

## 🏗 Tech Stack

### **Android**
- Kotlin
- Jetpack Compose
- MVVM + StateFlow
- Firebase Realtime Database
- Material 3 UI

### **IoT Worker**
- Node.js
- Firebase Admin SDK
- Simulasi AC dan Fan otomatis

---

## 📂 Project Structure (Android)
```
app/
├── data/
│ ├── firebase/
│ ├── model/
│ └── repository/
├── ui/
│ ├── components/
│ ├── home/
│ └── roomdetail/
└── MainActivity.kt
```

## 🔥 Firebase Setup

1. Buat project Firebase
2. Tambahkan aplikasi Android
3. Download `google-services.json` → taruh di: root folder (app)
4. Enable **Realtime Database**
5. Tambahkan contoh struktur database di bawah ini:

```
json
{
  "rooms": {
    "living_room": {
      "name": "Living Room",
      "devices": {
        "lamp_1": {
          "type": "lamp",
          "name": "Main Lamp",
          "status": "OFF"
        },
        "ac_1": {
          "type": "ac",
          "name": "AC Panasonic",
          "status": "ON",
          "temperature": 24
        },
        "fan_1": {
          "type": "fan",
          "name": "Turbo Fan",
          "status": "ON",
          "speed": 3
        }
      }
    }
  }
}
```

---

## 🤖 IoT Worker (Simulasi Hardware)

### Struktur Worker
```
IoT-Worker/
├── src/
│   ├── firebase.js
│   ├── worker.js
│   └── index.js
└── serviceAccountKey.json
```
### Cara menjalankan
```
npm install
npm start
```
Worker akan:
- Membaca perintah dari Android
- Mensimulasikan perubahan suhu AC & speed Fan setiap 5 detik
- Mengupdate Firebase → realtime muncul di aplikasi

---

## 🚀 Cara Menjalankan
### Android
```
Open Android Studio → Run App
```
### IoT Worker
```
cd IoT-Worker
npm start
```

---

## 🧠 Clean Architecture Flow
```
UI → ViewModel → Repository → FirebaseService → Firebase RealtimeDB
```

---

## 🛠 Planned Features
- Edit Device Name (Popup Dialog)
- Add New Devices
- Add Room dynamically
- Monitoring chart
