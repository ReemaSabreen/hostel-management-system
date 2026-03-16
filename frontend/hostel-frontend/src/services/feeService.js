import API from "../api/axios";

export const createFee = (data) =>
  API.post("/fees", data);

export const updateFee = (feeId,data) =>
  API.put(`/fees/${feeId}`,data);

export const getAllFees = () => {
  return API.get("/fees");
};

export const getFeeById = (feeId) =>
  API.get(`/fees/${feeId}`);

export const getStudentFees = (studentId) =>
  API.get(`/fees/student/${studentId}`);

export const getDefaulters = () =>
  API.get("/fees/defaulters");

export const makePayment = (feeId,data) =>
  API.post(`/fees/payments/${feeId}`,data);

export const getStudentPayments = (studentId) =>
  API.get(`/fees/payments/student/${studentId}`);

export const getReceipt = (paymentId) =>
  API.get(`/fees/payments/receipt/${paymentId}`);