package com.xbw.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Kernel32Util;
import com.sun.jna.win32.StdCallLibrary;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Map;

public class JNA {
    public static void main(String[] args) {
        CLibrary.INSTANCE.printf("Hello, World\n");

        LocalCLibrary.INSTANCE.printf("Hello, LocalCLibrary\n");

        Kernel32 kernel32 = Kernel32.INSTANCE;
        Kernel32.SYSTEMTIME systemTime = new Kernel32.SYSTEMTIME();
        kernel32.GetSystemTime(systemTime);
        String date = systemTime.wYear + "-" + systemTime.wMonth + "-" + systemTime.wDay + " " + systemTime.wHour + ":" + systemTime.wMinute + ":" + systemTime.wSecond;
        System.out.println("date = " + date);
        DateTime dateTime = DateTimeFormat.forPattern("yyyy-M-d HH:mm:ss").parseDateTime(date);
        System.out.println("dateTime = " + dateTime.toString("yyyy-MM-dd HH:mm:ss"));

        // jna-platform
        System.out.println(Kernel32Util.getComputerName());
        Map<String, String> map = Kernel32Util.getEnvironmentVariables();
        System.out.println("map = " + map);
        map.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
    }


    // This is the standard, stable way of mapping, which supports extensive
    // customization and mapping of Java to native types.

    public interface CLibrary extends Library {
        // CLibrary INSTANCE = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);
        CLibrary INSTANCE = Native.load((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);

        void printf(String format, Object... args);
    }


    // This is the standard, stable way of mapping, which supports extensive
    // customization and mapping of Java to native types.

    public interface LocalCLibrary extends Library {
        String filePath = LocalCLibrary.class.getResource("/lib").getPath().replaceFirst("/", "").replaceAll("%20", " ") + "/msvcrt.dll";
        CLibrary INSTANCE = Native.load(filePath, CLibrary.class);

        void printf(String format, Object... args);
    }


    // kernel32.dll uses the __stdcall calling convention (check the function
    // declaration for "WINAPI" or "PASCAL"), so extend StdCallLibrary
    // Most C libraries will just extend com.sun.jna.Library,

    public interface Kernel32 extends StdCallLibrary {
        // Method declarations, constant and structure definitions go here

        Kernel32 INSTANCE = (Kernel32) Native.load("kernel32", Kernel32.class);

        // Optional: wraps every call to the native library in a
        // synchronized block, limiting native calls to one at a time

        Kernel32 SYNC_INSTANCE = (Kernel32) Native.synchronizedLibrary(INSTANCE);

        @Structure.FieldOrder({"wYear", "wMonth", "wDayOfWeek", "wDay", "wHour", "wMinute", "wSecond", "wMilliseconds"})
        public static class SYSTEMTIME extends Structure {
            public short wYear;
            public short wMonth;
            public short wDayOfWeek;
            public short wDay;
            public short wHour;
            public short wMinute;
            public short wSecond;
            public short wMilliseconds;
        }

        void GetSystemTime(SYSTEMTIME result);
    }
}
