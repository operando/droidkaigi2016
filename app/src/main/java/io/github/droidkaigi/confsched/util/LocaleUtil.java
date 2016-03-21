package io.github.droidkaigi.confsched.util;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import io.github.droidkaigi.confsched.BuildConfig;
import io.github.droidkaigi.confsched.prefs.DefaultPrefs;

public class LocaleUtil {

    public static final String LANG_EN_ID = "en";
    public static final String LANG_JA_ID = "ja";
    public static final String LANG_AR_ID = "ar";
    public static final String LANG_KO_ID = "ko";
    public static final String[] SUPPORT_LANG = {LANG_EN_ID, LANG_JA_ID, LANG_AR_ID, LANG_KO_ID};

    private static final String TAG = LocaleUtil.class.getSimpleName();

    private static final TimeZone CONFERENCE_TIMEZONE = TimeZone.getTimeZone(BuildConfig.CONFERENCE_TIMEZONE);

    private static final String LANG_STRING_RES_PREFIX = "lang_";

    private static final String RTL_MARK = "\u200F";

    private static final DateFormat DATE_FORMAT_TOKYO = SimpleDateFormat.getDateTimeInstance();

    private static final DateFormat DATE_FORMAT_LOCAL = SimpleDateFormat.getDateTimeInstance();

    static {
        DATE_FORMAT_TOKYO.setTimeZone(CONFERENCE_TIMEZONE);
        DATE_FORMAT_LOCAL.setTimeZone(TimeZone.getDefault());
    }

    public static boolean shouldRtl() {
        return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_RTL;
    }

    public static String getRtlConsideredText(String text) {
        if (shouldRtl()) {
            return RTL_MARK + text;
        } else {
            return text;
        }
    }

    public static void initLocale(Context context) {
        setLocale(context, getCurrentLanguageId(context));
    }

    public static void setLocale(Context context, @NonNull String languageId) {
        Configuration config = new Configuration();
        DefaultPrefs.get(context).putLanguageId(languageId);
        Locale locale = new Locale(languageId);
        Locale.setDefault(locale);
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    public static String getCurrentLanguageId(Context context) {
        String languageId = DefaultPrefs.get(context).getLanguageId();
        try {
            if (TextUtils.isEmpty(languageId)) {
                languageId = Locale.getDefault().getLanguage().toLowerCase();
            }
            if (!Arrays.asList(SUPPORT_LANG).contains(languageId)) {
                languageId = LANG_EN_ID;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } finally {
            if (TextUtils.isEmpty(languageId)) {
                languageId = LANG_EN_ID;
            }
        }
        return languageId;
    }

    public static String getCurrentLanguage(Context context) {
        return getLanguage(context, LocaleUtil.getCurrentLanguageId(context));
    }

    public static String getLanguage(Context context, String languageId) {
        return AppUtil.getString(context, LANG_STRING_RES_PREFIX + languageId);
    }

    public static String getLanguage(Context context, String languageId, String in) {
        return AppUtil.getString(context, LANG_STRING_RES_PREFIX + languageId + "_in_" + in);
    }

    public static Date getDisplayDate(@NonNull Date date, Context context) {
        DateFormat formatTokyo = SimpleDateFormat.getDateTimeInstance();
        formatTokyo.setTimeZone(CONFERENCE_TIMEZONE);
        DateFormat formatLocal = SimpleDateFormat.getDateTimeInstance();
        formatLocal.setTimeZone(getDisplayTimeZone(context));
        try {
            return formatLocal.parse(formatTokyo.format(date));
        } catch (ParseException e) {
            Log.e(TAG, "date: " + date + "can not parse." + e);
            return date;
        }
    }

    public static Date getConfTimezoneCurrentDate() {
        Date date = new Date();
        try {
            return DATE_FORMAT_LOCAL.parse(DATE_FORMAT_TOKYO.format(date));
        } catch (ParseException e) {
            Log.e(TAG, "date: " + date + "can not parse." + e);
            return date;
        }
    }

    public static TimeZone getDisplayTimeZone(Context context) {
        TimeZone defaultTimeZone = TimeZone.getDefault();
        boolean shouldShowLocalTime = DefaultPrefs.get(context).getShowLocalTimeFlag(false);
        return (shouldShowLocalTime && defaultTimeZone != null) ? defaultTimeZone : CONFERENCE_TIMEZONE;
    }

}
