import API from "../api/axios";

export const getRooms = () =>
  API.get("/rooms");

export const getRoomById = (roomId) =>
  API.get(`/rooms/${roomId}`);

export const createRoom = (data) =>
  API.post("/rooms", data);

export const updateRoom = (roomId,data) =>
  API.put(`/rooms/${roomId}`,data);

export const deleteRoom = (roomId) =>
  API.delete(`/rooms/${roomId}`);

export const getAvailableRooms = () =>
  API.get("/rooms/available");

export const allocateRoom = (studentId,roomId) =>
  API.post(`/rooms/allocate?studentId=${studentId}&roomId=${roomId}`);

export const deallocateRoom = (allocationId) =>
  API.delete(`/rooms/deallocate/${allocationId}`);

export const getAllAllocations = () => {
  return API.get("/rooms/allocations");
};

export const getStudentAllocations = (studentId) =>
  API.get(`/rooms/allocations/${studentId}`);

export const getCurrentAllocation = (studentId) => {
  return API.get(`/rooms/allocations/current/${studentId}`);
};