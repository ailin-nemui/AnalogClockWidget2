package eu.nemui.ailin.uclock;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.widget.RemoteViews;

public class AnalogClockWidgetBase extends AppWidgetProvider {

    protected int layout;

    protected AnalogClockWidgetBase(int layout) {
        this.layout = layout;
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, int layout) {

        RemoteViews views = new RemoteViews(context.getPackageName(), layout);
        views.setOnClickPendingIntent(R.id.content,
                PendingIntent.getActivity(context, 0,
                        new Intent()
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .setAction(AlarmClock.ACTION_SHOW_ALARMS),
                        PendingIntent.FLAG_IMMUTABLE));
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, layout);
        }
    }

}