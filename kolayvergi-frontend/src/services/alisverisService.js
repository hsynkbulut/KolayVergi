import axiosInstance from './axios.config';

const alisverisService = {
  getAllAlisverisler: () => {
    return axiosInstance.get('/alisverisler');
  },

  getAlisverisById: (id) => {
    return axiosInstance.get(`/alisverisler/${id}`);
  },

  createAlisveris: (data) => {
    return axiosInstance.post('/alisverisler', data);
  },

  updateAlisveris: (id, data) => {
    return axiosInstance.put(`/alisverisler/${id}`, data);
  },

  deleteAlisveris: (id) => {
    return axiosInstance.delete(`/alisverisler/${id}`);
  },

  getCurrentUserAlisverisler: () => {
    return axiosInstance.get('/alisverisler/benim-alisverislerim');
  }
};

export { alisverisService }; 