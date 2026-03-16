import API from "../api/axios";

/* STUDENT */

export const raiseComplaint = (complaint) => {
  return API.post("/complaints/raise", complaint);
};

export const getStudentComplaints = (studentId) => {
  return API.get(`/complaints/student/${studentId}`);
};


/* ADMIN */

export const getAllComplaints = () => {
  return API.get("/complaints");
};

export const updateComplaintStatus = (complaintId, status) => {
  return API.put(`/complaints/${complaintId}/status?status=${status}`);
};