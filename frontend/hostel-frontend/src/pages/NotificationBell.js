import { useEffect, useState } from "react";
import { FaBell } from "react-icons/fa";
import { getNotifications, markAsRead } from "../services/notificationService";

function NotificationBell() {

  const [notifications, setNotifications] = useState([]);
  const [open, setOpen] = useState(false);

  const studentId = localStorage.getItem("studentId");

  useEffect(() => {
    loadNotifications();
  }, []);

  const loadNotifications = async () => {
    try {
      const res = await getNotifications(studentId);
      setNotifications(res.data);
    } catch (e) {
      console.error("Failed to load notifications");
    }
  };

  const handleBellClick = async () => {
    setOpen(!open);

    // Mark all as read when opened
    try {
      await markAsRead(studentId);
      loadNotifications();
    } catch (e) {
      console.error("Failed to mark as read");
    }
  };

  const hasUnread = notifications.some(n => !n.read);

  return (
    <div className="notification-container">

      {/*  Bell Icon */}
      <div className="bell" onClick={handleBellClick}>
        <FaBell size={28} />

        {hasUnread && <span className="dot"></span>}
      </div>

      {/* Dropdown */}
      {open && (
        <div className="notification-dropdown">

          {notifications.length === 0 ? (
            <p className="empty-msg">No notifications</p>
          ) : (
            notifications.map(n => (
              <div
                key={n.id}
                className={`notification-item ${!n.read ? "unread" : ""}`}
              >
                {n.message}
              </div>
            ))
          )}

        </div>
      )}

    </div>
  );
}

export default NotificationBell;