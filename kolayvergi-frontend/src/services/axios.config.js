import axios from 'axios';
import AuthService from './auth.service';

const API_URL = 'http://localhost:8080/api/v1';

const axiosInstance = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

axiosInstance.interceptors.request.use(
  (config) => {
    const user = AuthService.getCurrentUser();
    if (user?.accessToken) {
      config.headers['Authorization'] = `Bearer ${user.accessToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        const response = await AuthService.refreshToken();
        const { accessToken } = response.data;

        const user = AuthService.getCurrentUser();
        user.accessToken = accessToken;
        localStorage.setItem('user', JSON.stringify(user));

        originalRequest.headers['Authorization'] = `Bearer ${accessToken}`;
        return axiosInstance(originalRequest);
      } catch (refreshError) {
        AuthService.logout();
        window.location.href = '/login';
        return Promise.reject(refreshError);
      }
    }

    return Promise.reject(error);
  }
);

export default axiosInstance; 