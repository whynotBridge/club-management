package com.clubmanagement.commom;

public class Context {
    public static ThreadLocal<MySession> threadLocal = new ThreadLocal<>();

    public static void setCurrentSession(MySession mySession) {
        threadLocal.set(mySession);
    }

    public static MySession getCurrentSession() {
        return threadLocal.get();
    }

    public static void removeCurrentSession() {
        threadLocal.remove();
    }
}
