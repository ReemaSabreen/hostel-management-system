import API from "../api/axios";

export const createMenu = (data) =>
  API.post("/mess/menu", data);

export const getMenu = (date) =>
  API.get(`/mess/view-menu?date=${date}`);

export const markAttendance = (data) =>
  API.post("/mess/mess-attendance", data);

export const calculateBill = (studentId) =>
  API.get(`/mess/bill/${studentId}`);

export const generateMessFee = (studentId) =>
  API.post(`/mess/generate-fee/${studentId}`);