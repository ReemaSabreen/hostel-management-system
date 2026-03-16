import API from "../api/axios";

export const login = (username,password) => {

  return API.post("/auth/login",{
    username,
    password
  });

};

export const register = (data) => {

  return API.post("/auth/register",data);

};

export const resetPassword = (email, newPassword) => {

  return API.post(
    `/auth/reset-password?email=${email}&newPassword=${newPassword}`
  );

};

export const getStudentUsersWithoutProfile = () => {
  return API.get("/auth/students-without-profile");
};