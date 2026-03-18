import API from "../api/axios";

export const getNotifications = (studentId) =>
  API.get(`/notifications/${studentId}`);

export const markAsRead = (studentId) =>
  API.put(`/notifications/read/${studentId}`);