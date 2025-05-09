# Event Countdown App

Event Countdown is an Android application that allows users to schedule events, set reminders, and track the time remaining for each event with a live countdown timer. Notifications are triggered when events start, ensuring you never miss an important moment.

## Features

* **Add New Events:** Create events with a title, description, date, time, and mark them as important if necessary.
* **Live Countdown Timer:** Real-time countdown displayed for each event.
* **Notifications:** Automatic notifications when an event starts.
* **Event List Display:** View all upcoming events in a structured list.

## Screenshots
![Screenshot 2025-05-09 153738](https://github.com/user-attachments/assets/11cb84c4-7305-4d62-a999-967fbce0c0e9)   ![Screenshot 2025-05-09 153656](https://github.com/user-attachments/assets/d5718dfd-d1e7-4ae2-9cc1-be63ca56a9a0)

![Screenshot 2025-05-09 153744](https://github.com/user-attachments/assets/50bc7d75-3e6d-4789-bcd8-50218e8a6e75)   ![Screenshot 2025-05-09 153751](https://github.com/user-attachments/assets/814b1446-9a60-460a-b77a-6c2aefd15195)





## Technologies Used

* **Language:** Kotlin
* **UI Design:** XML
* **Database:** Room Database for local storage
* **Notifications:** AlarmManager & BroadcastReceiver
* **Architecture:** MVVM (Model-View-ViewModel)

## Getting Started

### Prerequisites

* Android Studio Dolphin or higher
* Minimum SDK version: 21 (Lollipop)
* Gradle 7.0+

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/event-countdown-app.git
   ```

2. Open the project in **Android Studio**.

3. Sync Gradle and build the project.

4. Run the app on an emulator or physical device.

## Usage

1. Click the **Add Event** button to create a new event.
2. Enter the event details (Title, Date, Time, Description).
3. Click **Done** to save the event.
4. The countdown timer will be visible in the event list.
5. When the event starts, a notification will be triggered automatically.

## Project Structure

```
📂 app
 ┣ 📂 java/com/example/eventcountdown
 ┃ ┣ 📂 dataLayer
 ┃ ┃ ┣ EventModel.kt
 ┃ ┃ ┣ EventDao.kt
 ┃ ┃ ┗ EventDatabase.kt
 ┃ ┣ AddEventFragment.kt
 ┃ ┣ MainActivity.kt
 ┃ ┣ 📂 LogicLayer
 ┃ ┣ EventCounterFunction.kt
 ┃ ┗ NotificationUtils.kt
 ┣ 📂 res
 ┃ ┣ 📂 layout
 ┃ ┃ ┣ activity_main.xml
 ┃ ┃ ┗ fragment_add_event.xml
 ┃ ┗ 📂 values
 ┃ ┃ ┣ colors.xml
 ┃ ┃ ┗ strings.xml
 ┗ AndroidManifest.xml
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

* Icons by [Material Design Icons](https://materialdesignicons.com/)
* Notifications setup inspired by Android Developers Documentation

---

### Feel free to contribute and open PRs for improvements!
