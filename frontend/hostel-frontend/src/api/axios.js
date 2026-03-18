import axios from "axios";

const API = axios.create({
  baseURL: process.env.REACT_APP_API_URL
});

API.interceptors.request.use((req) => {

  const token = localStorage.getItem("token");

  if(token){
    req.headers.Authorization = `Bearer ${token}`;
  }

  return req;
});

// GLOBAL ERROR HANDLER
API.interceptors.response.use(

  // success response
  (response) => response,

  // error response
  (error) => {

    let message = "Something went wrong";

    if (error.response) {

      // backend returned error
      message =
        error.response.data?.message ||
        error.response.data ||
        message;

    } else if (error.request) {

      message = "Server not responding";

    }

    alert(message);

    return Promise.reject(error);
  }

);

export default API;