import React, { createContext, useState, useContext, useEffect } from 'react';
import AuthService from '../services/auth.service';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user) {
      setUser(user);
    }
    setLoading(false);
  }, []);

  const login = async (email, password) => {
    try {
      const response = await AuthService.login(email, password);
      setUser(response);
      return response;
    } catch (error) {
      throw error;
    }
  };

  const logout = () => {
    AuthService.logout();
    setUser(null);
  };

  const register = async (userData) => {
    try {
      const response = await AuthService.register(
        userData.tckn,
        userData.ad,
        userData.soyad,
        userData.email,
        userData.sifre,
        userData.yas,
        userData.cinsiyet,
        userData.meslek,
        userData.maas
      );
      return response;
    } catch (error) {
      throw error;
    }
  };

  const refreshToken = async () => {
    try {
      const response = await AuthService.refreshToken();
      setUser(response.data);
      return response.data;
    } catch (error) {
      logout();
      throw error;
    }
  };

  const value = {
    user,
    loading,
    login,
    logout,
    register,
    refreshToken
  };

  return (
    <AuthContext.Provider value={value}>
      {!loading && children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth bir AuthProvider içinde kullanılmalıdır');
  }
  return context;
}; 