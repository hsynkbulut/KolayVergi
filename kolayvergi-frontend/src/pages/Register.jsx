import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { validateRegisterForm } from '../validation/registerValidation';

const MESLEKLER = [
  'AKADEMISYEN',
  'MUHENDIS',
  'DOKTOR',
  'POLIS',
  'OGRETMEN',
  'MEMUR',
  'SERBEST_CALISAN',
  'ISADAMI',
  'DIGER',
];

const initialState = {
  tckn: '',
  ad: '',
  soyad: '',
  email: '',
  sifre: '',
  yas: '',
  cinsiyet: '',
  meslek: '',
  maas: '',
};

const Register = () => {
  const [form, setForm] = useState(initialState);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [loading, setLoading] = useState(false);
  const [fieldErrors, setFieldErrors] = useState({});
  const navigate = useNavigate();
  const { register } = useAuth();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    setFieldErrors({ ...fieldErrors, [e.target.name]: undefined });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    setLoading(true);
    const errors = validateRegisterForm(form);
    setFieldErrors(errors);
    if (Object.keys(errors).length > 0) {
      setLoading(false);
      return;
    }
    try {
      await register({ ...form, yas: Number(form.yas), maas: Number(form.maas) });
      setSuccess('Kayıt başarılı! Giriş sayfasına yönlendiriliyorsunuz...');
      setTimeout(() => navigate('/login'), 2000);
    } catch (err) {
      if (err.response && err.response.data) {
        const apiError = err.response.data;
        if (apiError.errors) {
          setFieldErrors(apiError.errors);
        }
        if (apiError.message) {
          setError(apiError.message);
        } else if (!apiError.errors) {
          setError('Kayıt başarısız. Lütfen bilgilerinizi kontrol edin.');
        }
      } else {
        setError('Kayıt başarısız. Lütfen bilgilerinizi kontrol edin.');
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-md w-full space-y-8">
        <div>
          <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
            Kayıt Ol
          </h2>
        </div>
        <form className="mt-8 space-y-6" onSubmit={handleSubmit} noValidate>
          {error && (
            <div className="rounded-md bg-red-50 p-4">
              <div className="text-sm text-red-700">{error}</div>
            </div>
          )}
          {success && (
            <div className="rounded-md bg-green-50 p-4">
              <div className="text-sm text-green-700">{success}</div>
            </div>
          )}
          <div className="rounded-md shadow-sm -space-y-px grid grid-cols-2 gap-2">
            <div className="col-span-2">
              <input name="tckn" type="text" required placeholder="TCKN" value={form.tckn} onChange={handleChange} className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" />
              {fieldErrors.tckn && <span className="text-xs text-red-600">{fieldErrors.tckn}</span>}
            </div>
            <div>
              <input name="ad" type="text" required placeholder="Ad" value={form.ad} onChange={handleChange} className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" />
              {fieldErrors.ad && <span className="text-xs text-red-600">{fieldErrors.ad}</span>}
            </div>
            <div>
              <input name="soyad" type="text" required placeholder="Soyad" value={form.soyad} onChange={handleChange} className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" />
              {fieldErrors.soyad && <span className="text-xs text-red-600">{fieldErrors.soyad}</span>}
            </div>
            <div className="col-span-2">
              <input name="email" type="email" required placeholder="E-posta" value={form.email} onChange={handleChange} className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" />
              {fieldErrors.email && <span className="text-xs text-red-600">{fieldErrors.email}</span>}
            </div>
            <div className="col-span-2">
              <input name="sifre" type="password" required placeholder="Şifre" value={form.sifre} onChange={handleChange} className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" />
              {fieldErrors.sifre && <span className="text-xs text-red-600">{fieldErrors.sifre}</span>}
            </div>
            <div>
              <input name="yas" type="number" required placeholder="Yaş" value={form.yas} onChange={handleChange} className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" />
              {fieldErrors.yas && <span className="text-xs text-red-600">{fieldErrors.yas}</span>}
            </div>
            <div>
              <input name="maas" type="number" required placeholder="Maaş" value={form.maas} onChange={handleChange} className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" />
              {fieldErrors.maas && <span className="text-xs text-red-600">{fieldErrors.maas}</span>}
            </div>
            <div>
              <select name="cinsiyet" required value={form.cinsiyet} onChange={handleChange} className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm">
                <option value="">Cinsiyet</option>
                <option value="ERKEK">Erkek</option>
                <option value="KADIN">Kadın</option>
              </select>
              {fieldErrors.cinsiyet && <span className="text-xs text-red-600">{fieldErrors.cinsiyet}</span>}
            </div>
            <div>
              <select name="meslek" required value={form.meslek} onChange={handleChange} className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm">
                <option value="">Meslek</option>
                {MESLEKLER.map((meslek) => (
                  <option key={meslek} value={meslek}>{meslek.replace('_', ' ')}</option>
                ))}
              </select>
              {fieldErrors.meslek && <span className="text-xs text-red-600">{fieldErrors.meslek}</span>}
            </div>
          </div>
          <div>
            <button
              type="submit"
              disabled={loading}
              className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              {loading ? 'Kayıt olunuyor...' : 'Kayıt Ol'}
            </button>
          </div>
        </form>
        <div className="text-center">
          <button onClick={() => navigate('/login')} className="text-indigo-600 hover:underline text-sm">Zaten hesabınız var mı? Giriş Yap</button>
        </div>
      </div>
    </div>
  );
};

export default Register; 