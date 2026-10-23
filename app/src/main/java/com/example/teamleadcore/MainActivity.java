package com.example.teamleadcore;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import static androidx.core.app.NotificationCompat.PRIORITY_HIGH;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnMorning, btnDay, btnEvening, btnNight;
    private ImageView imgMorning, imgDay, imgEvening, imgNight;
    private ConstraintLayout layoutMorning;
    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "CHANNEL_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // поиск кнопок по id
        btnMorning = findViewById(R.id.btnMorning);
        btnDay = findViewById(R.id.btnDay);
        btnEvening = findViewById(R.id.btnEvening);
        btnNight = findViewById(R.id.btnNight);

        // поиск картинок по id
        imgMorning = findViewById(R.id.imageMorning);
        imgDay = findViewById(R.id.imageDay);
        imgEvening = findViewById(R.id.imageEvening);
        imgNight = findViewById(R.id.imageNight);

        // поиск утренней локации
        layoutMorning = findViewById(R.id.layoutMorning);

        // создание менеджера уведомлений
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // создание канала уведомлений
        createChannelIfNeeded(notificationManager);

        // запрос разрешения на уведомления в Android 13+
        if (Build.VERSION.SDK_INT >= 33) {
            if (checkSelfPermission(
                    android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(
                        new String[]{
                                android.Manifest.permission.POST_NOTIFICATIONS
                        },
                        100
                );
            }
        }
    }

    // утро
    public void btnMorningClick(View view) {
        layoutMorning.setVisibility(View.VISIBLE);
        imgDay.setVisibility(View.INVISIBLE);
        imgEvening.setVisibility(View.INVISIBLE);
        imgNight.setVisibility(View.INVISIBLE);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(
                        getApplicationContext(), CHANNEL_ID)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.team)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setContentTitle("Утро")
                        .setContentText(
                                "Провести стендап, успокоить команду")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setVisibility(
                                NotificationCompat.VISIBILITY_PUBLIC);

        notificationManager.notify(
                NOTIFY_ID, notificationBuilder.build());
    }
    // день
    public void btnDayClick(View view) {
        layoutMorning.setVisibility(View.INVISIBLE);
        imgDay.setVisibility(View.VISIBLE);
        imgEvening.setVisibility(View.INVISIBLE);
        imgNight.setVisibility(View.INVISIBLE);

        notificationManager=(NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(
                        getApplicationContext(), CHANNEL_ID)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.calendar)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setContentTitle("День")
                        .setContentText("Успеть на 5 созвонов по Jira")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setVisibility(
                                NotificationCompat.VISIBILITY_PUBLIC);

        notificationManager.notify(NOTIFY_ID, notificationBuilder.build());
    }
    // вечер
    public void btnEveningClick(View view) {
        layoutMorning.setVisibility(View.INVISIBLE);
        imgDay.setVisibility(View.INVISIBLE);
        imgEvening.setVisibility(View.VISIBLE);
        imgNight.setVisibility(View.INVISIBLE);

        notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(
                getApplicationContext(), MainActivity.class);

        intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
                        | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(
                        getApplicationContext(), CHANNEL_ID)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.work)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setContentTitle("Вечер")
                        .setContentText("Сказать заказчику, что всё готово (нет)")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setVisibility(
                                NotificationCompat.VISIBILITY_PUBLIC);

        notificationManager.notify(
                NOTIFY_ID, notificationBuilder.build());
    }
    // ночь
    public void btnNightClick(View view) {
        layoutMorning.setVisibility(View.INVISIBLE);
        imgDay.setVisibility(View.INVISIBLE);
        imgEvening.setVisibility(View.INVISIBLE);
        imgNight.setVisibility(View.VISIBLE);

        notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(
                getApplicationContext(), MainActivity.class);

        intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
                        | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(
                        getApplicationContext(), CHANNEL_ID)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.laptop)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setContentTitle("Ночь")
                        .setContentText("Написать весь фронтенд и бэкенд за ночь")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setVisibility(
                                NotificationCompat.VISIBILITY_PUBLIC);

        notificationManager.notify(
                NOTIFY_ID, notificationBuilder.build());
    }

    // Создание канала уведомлений
    public static void createChannelIfNeeded(NotificationManager manager) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            CHANNEL_ID,
                            NotificationManager.IMPORTANCE_DEFAULT);

            manager.createNotificationChannel(notificationChannel);
        }
    }
    // Тимлид
    public void onTeamLeadClick(View view) {
        Toast toast = Toast.makeText(
                getApplicationContext(),
                "Тимлид",
                Toast.LENGTH_LONG);

        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    // Доска задач
    public void onTaskBoardClick(View view) {
        Toast toast = Toast.makeText(
                getApplicationContext(),
                "Доска задач",
                Toast.LENGTH_LONG);

        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    // Jira
    public void onJiraClick(View view) {
        Toast toast = Toast.makeText(
                getApplicationContext(),
                "Jira: Здесь 100 задач, и все горят",
                Toast.LENGTH_LONG);

        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    // Zoom
    public void onZoomClick(View view) {
        Toast toast = Toast.makeText(
                getApplicationContext(),
                "Zoom: Мой родной дом",
                Toast.LENGTH_LONG);

        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    // Кофе
    public void onCoffeeClick(View view) {
        Toast toast = Toast.makeText(
                getApplicationContext(),
                "Кофе: Единственный, кто меня успокаивает",
                Toast.LENGTH_LONG);

        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}