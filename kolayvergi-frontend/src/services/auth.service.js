import axiosInstance from './axios.config';

class AuthService {
  async login(email, password) {
    const response = await axiosInstance.post('/auth/login', {
      email,
      password
    });
    if (response.data.accessToken) {
      localStorage.setItem('user', JSON.stringify(response.data));
    }
    return response.data;
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(tckn, ad, soyad, email, sifre, yas, cinsiyet, meslek, maas) {
    return axiosInstance.post('/auth/register', {
      tckn,
      ad,
      soyad,
      email,
      sifre,
      yas,
      cinsiyet,
      meslek,
      maas
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));
  }

  refreshToken() {
    const user = this.getCurrentUser();
    if (user?.refreshToken) {
      return axiosInstance.post('/auth/refresh', null, {
        headers: {
          'Authorization': `Bearer ${user.refreshToken}`
        }
      });
    }
    return Promise.reject('Refresh token bulunamadı');
  }
}

export default new AuthService(); 