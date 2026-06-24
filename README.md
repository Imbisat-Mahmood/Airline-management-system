# ✈️ PIA Airline Management System

A desktop-based airline management system developed using **Java Swing** and **MySQL** for managing customer registration, flight booking, journey details, ticket cancellation, and boarding pass generation.

---

## ✨ Features

- 👤 **Customer Management** - Add, update, and manage passenger details
- ✈️ **Flight Booking** - Book domestic and international flights
- 🧾 **Journey Details** - View complete journey information
- 🎫 **Boarding Pass Generation** - Generate and print boarding passes
- ❌ **Ticket Cancellation** - Cancel booked tickets with ease
- 📊 **Flight Management** - View available flights and schedules
- 🗄️ **MySQL Integration** - Persistent data storage
- 🖥️ **Interactive GUI** - Java Swing interface with PIA theme

---

## 🛠️ Tech Stack

| Category | Technologies |
|----------|--------------|
| **Language** | Java (JDK 8+) |
| **GUI Framework** | Java Swing |
| **Database** | MySQL |
| **JDBC Driver** | MySQL Connector/J |
| **Libraries** | JCalendar, RS2XML |
| **IDE** | NetBeans / Eclipse / IntelliJ IDEA |

---

## 📁 Project Structure

```
Airline-management-system/
├── src/
│   └── airline/management/system/
│       ├── AddCustomer.java
│       ├── BoardingPass.java
│       ├── Bookflight.java
│       ├── CancelTicket.java
│       ├── Conn.java
│       ├── Flightinfo.java
│       ├── Home.java
│       ├── JDateChooser.java
│       ├── JourneyDetails.java
│       └── Login.java
├── icons/
├── build.xml
├── jcalendar-tz-1.3.3-4.jar
├── mysql-connector-java-8.0.28.jar
├── rs2xml.jar
└── README.md
```

---

## 🚀 Installation

### Prerequisites

- Java JDK 8 or higher
- MySQL Server (XAMPP/WAMP recommended)
- NetBeans / Eclipse / IntelliJ IDEA

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/Imbisat-Mahmood/Airline-management-system.git
   ```

2. **Set up database**
   - Open MySQL (phpMyAdmin or command line)
   - Create database `airline_db`
   - Import SQL file (if included)

3. **Update database configuration**
   
   Open `Conn.java` and update:
   ```java
   String url = "jdbc:mysql://localhost:3306/airline_db";
   String username = "root";
   String password = "";
   ```

4. **Add libraries**
   - Add all `.jar` files to project classpath
   - In NetBeans: Right-click Libraries → Add JAR/Folder

5. **Run the application**
   - Open project in IDE
   - Run `Login.java`
   - Default credentials: `admin` / `admin`

---

## 🔧 Troubleshooting

| Issue | Solution |
|-------|----------|
| Class not found errors | Add all `.jar` files to libraries |
| Database connection failed | Check MySQL credentials in `Conn.java` |
| MySQL not connecting | Make sure MySQL server is running |
| Date picker not working | Add `jcalendar-tz-1.3.3-4.jar` to libraries |

---

## 👨‍💻 Developer

**Imbisat Mahmood**
- 📧 imbisatmahmood05@gmail.com
- 🔗 [LinkedIn](https://www.linkedin.com/in/imbisat-mahmood-7219442a8)
- 🐙 [GitHub](https://github.com/Imbisat-Mahmood)

---

## 📝 License

MIT License

---

⭐ **If you found this helpful, please give it a star!** ⭐
