package com.example.nutritiontrackerguiv4.database;

import java.util.List;

public class NotificationsRepository {

    NotificationsDAO dao;

    public NotificationsRepository(NotificationsDAO d){
        dao = d;
    }

    public void addNotification(Notifications u){ dao.insert(u); }

   // public void updateNotification(Notifications u){ dao.update(u); }

    public void deleteNotification(Notifications u){
        dao.delete(u);
    }

    public List<Notifications> getAllNotifications(){
        return dao.getAllNotifications();
    }

}
