import axiosInstance from './axios.config';

const updateAlisveris = (id, data) => axiosInstance.put(`/alisverisler/${id}`, data);
const getAlisverisById = (id) => axiosInstance.get(`/alisverisler/${id}`);

const alisverisService = {
  getAllAlisverisler: () => {
    return axiosInstance.get('/alisverisler');
  },

  getAlisverisById,

  createAlisveris: (data) => {
    return axiosInstance.post('/alisverisler', data);
  },

  updateAlisveris,

  deleteAlisveris: (id) => {
    return axiosInstance.delete(`/alisverisler/${id}`);
  },

  getCurrentUserAlisverisler: () => {
    return axiosInstance.get('/alisverisler/benim-alisverislerim');
  }
};

export { alisverisService }; 