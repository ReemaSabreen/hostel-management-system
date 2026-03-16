import API from "../api/axios";

export const getStudents = () => {
  return API.get("/students");
};
export const getStudentById = (id) => {
  return API.get(`/students/${id}`);
};

export const createStudent = (student) => {
  return API.post("/students", student);
};

export const updateStudent = (id, student) => {
  return API.put(`/students/${id}`, student);
};

export const deleteStudent = (id) => {
  return API.delete(`/students/${id}`);
};

export const checkoutStudent = (id) => {
  return API.put(`/students/checkout/${id}`);
};