import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { validateLoginForm } from '../validation/loginValidation';
import Card from '../components/ui/Card';
import Input from '../components/ui/Input';
import Button from '../components/ui/Button';
import Label from '../components/ui/Label';
import Icon from '../components/ui/Icon';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [fieldErrors, setFieldErrors] = useState({});
  const navigate = useNavigate();
  const location = useLocation();
  const { login } = useAuth();

  const from = location.state?.from?.pathname || '/';

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setFieldErrors({});
    setLoading(true);
    const errors = validateLoginForm({ email, password });
    setFieldErrors(errors);
    if (Object.keys(errors).length > 0) {
      setLoading(false);
      return;
    }
    try {
      await login(email, password);
      navigate(from, { replace: true });
    } catch (err) {
      if (err.response && err.response.data) {
        const apiError = err.response.data;
        if (apiError.errors) {
          setFieldErrors(apiError.errors);
        }
        if (apiError.message) {
          setError(apiError.message);
        } else if (!apiError.errors) {
          setError('Giriş başarısız. Lütfen e-posta ve şifrenizi kontrol edin.');
        }
      } else {
        setError('Giriş başarısız. Lütfen e-posta ve şifrenizi kontrol edin.');
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen w-screen flex items-center justify-center bg-gradient-to-br from-blue-50 via-white to-blue-100">
      <Card className="w-full max-w-lg p-8 flex flex-col items-center gap-4 relative overflow-visible">
        <div className="flex flex-col items-center w-full">
          <div className="-mt-16 mb-2 bg-blue-100 rounded-full p-4 shadow-lg flex items-center justify-center">
            <Icon name="FiLock" className="w-12 h-12 text-blue-600" />
          </div>
          <h2 className="text-3xl font-extrabold text-blue-700 mb-1">Giriş Yap</h2>
          <p className="text-gray-600 text-base mb-4 text-center">KolayVergi'ye hoş geldiniz! Lütfen hesabınıza giriş yapın.</p>
        </div>
        <form className="w-full space-y-5" onSubmit={handleSubmit} noValidate>
          {error && (
            <div className="rounded-md bg-red-50 p-4">
              <div className="text-sm text-red-700">{error}</div>
            </div>
          )}
          <div className="space-y-4">
            <div>
              <Label htmlFor="email">E-posta</Label>
              <Input
                id="email"
                name="email"
                type="email"
                autoComplete="email"
                required
                placeholder="E-posta adresi"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                error={fieldErrors.email}
              />
              {fieldErrors.email && <span className="text-xs text-red-600">{fieldErrors.email}</span>}
            </div>
            <div>
              <Label htmlFor="password">Şifre</Label>
              <Input
                id="password"
                name="password"
                type="password"
                autoComplete="current-password"
                required
                placeholder="Şifre"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                error={fieldErrors.password}
              />
              {fieldErrors.password && <span className="text-xs text-red-600">{fieldErrors.password}</span>}
            </div>
          </div>
          <Button type="submit" disabled={loading} className="w-full mt-2">
            {loading ? 'Giriş yapılıyor...' : 'Giriş Yap'}
          </Button>
        </form>
        <div className="w-full flex flex-col items-center mt-4">
          <button
            onClick={() => navigate('/register')}
            className="text-indigo-600 hover:underline text-sm mt-2"
          >
            Hesabınız yok mu? <span className="font-semibold">Kayıt Ol</span>
          </button>
          <div className="text-xs text-gray-400 mt-4">&copy; {new Date().getFullYear()} KolayVergi. Tüm hakları saklıdır.</div>
        </div>
      </Card>
    </div>
  );
};

export default Login; 