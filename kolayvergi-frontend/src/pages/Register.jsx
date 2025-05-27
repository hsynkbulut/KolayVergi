import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { validateRegisterForm } from '../validation/registerValidation';
import Card from '../components/ui/Card';
import Input from '../components/ui/Input';
import Button from '../components/ui/Button';
import Label from '../components/ui/Label';
import Icon from '../components/ui/Icon';

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
    <div className="min-h-screen w-screen flex items-center justify-center bg-gradient-to-br from-blue-50 via-white to-blue-100">
      <Card className="w-full max-w-3xl p-8 flex flex-col items-center gap-4 relative overflow-visible">
        <div className="flex flex-col items-center w-full">
          <div className="-mt-16 mb-2 bg-blue-100 rounded-full p-4 shadow-lg flex items-center justify-center">
            <Icon name="FiUserPlus" className="w-12 h-12 text-blue-600" />
          </div>
          <h2 className="text-3xl font-extrabold text-blue-700 mb-1">Kayıt Ol</h2>
          <p className="text-gray-600 text-base mb-4 text-center">KolayVergi'ye katılmak için bilgilerinizi doldurun.</p>
        </div>
        <form className="w-full grid grid-cols-1 md:grid-cols-2 gap-8" onSubmit={handleSubmit} noValidate>
          {/* Sol Sütun */}
          <div className="flex flex-col gap-4">
            <Label htmlFor="tckn">TCKN</Label>
            <Input name="tckn" id="tckn" type="text" required placeholder="TCKN" value={form.tckn} onChange={handleChange} error={fieldErrors.tckn} />
            {fieldErrors.tckn && <span className="text-xs text-red-600">{fieldErrors.tckn}</span>}

            <Label htmlFor="ad">Ad</Label>
            <Input name="ad" id="ad" type="text" required placeholder="Ad" value={form.ad} onChange={handleChange} error={fieldErrors.ad} />
            {fieldErrors.ad && <span className="text-xs text-red-600">{fieldErrors.ad}</span>}

            <Label htmlFor="soyad">Soyad</Label>
            <Input name="soyad" id="soyad" type="text" required placeholder="Soyad" value={form.soyad} onChange={handleChange} error={fieldErrors.soyad} />
            {fieldErrors.soyad && <span className="text-xs text-red-600">{fieldErrors.soyad}</span>}

            <Label htmlFor="email">E-posta</Label>
            <Input name="email" id="email" type="email" required placeholder="E-posta" value={form.email} onChange={handleChange} error={fieldErrors.email} />
            {fieldErrors.email && <span className="text-xs text-red-600">{fieldErrors.email}</span>}

            <Label htmlFor="sifre">Şifre</Label>
            <Input name="sifre" id="sifre" type="password" required placeholder="Şifre" value={form.sifre} onChange={handleChange} error={fieldErrors.sifre} />
            {fieldErrors.sifre && <span className="text-xs text-red-600">{fieldErrors.sifre}</span>}
          </div>
          {/* Sağ Sütun */}
          <div className="flex flex-col gap-4 justify-between h-full">
            <div>
              <Label htmlFor="yas">Yaş</Label>
              <Input name="yas" id="yas" type="number" required placeholder="Yaş" value={form.yas} onChange={handleChange} error={fieldErrors.yas} />
              {fieldErrors.yas && <span className="text-xs text-red-600">{fieldErrors.yas}</span>}
            </div>
            <div>
              <Label htmlFor="maas">Maaş</Label>
              <Input name="maas" id="maas" type="number" required placeholder="Maaş" value={form.maas} onChange={handleChange} error={fieldErrors.maas} />
              {fieldErrors.maas && <span className="text-xs text-red-600">{fieldErrors.maas}</span>}
            </div>
            <div>
              <Label htmlFor="cinsiyet">Cinsiyet</Label>
              <select name="cinsiyet" id="cinsiyet" required value={form.cinsiyet} onChange={handleChange} className="border rounded-lg px-4 py-2 w-full focus:outline-none focus:ring-2 focus:ring-blue-400">
                <option value="">Cinsiyet</option>
                <option value="ERKEK">Erkek</option>
                <option value="KADIN">Kadın</option>
              </select>
              {fieldErrors.cinsiyet && <span className="text-xs text-red-600">{fieldErrors.cinsiyet}</span>}
            </div>
            <div>
              <Label htmlFor="meslek">Meslek</Label>
              <select name="meslek" id="meslek" required value={form.meslek} onChange={handleChange} className="border rounded-lg px-4 py-2 w-full focus:outline-none focus:ring-2 focus:ring-blue-400">
                <option value="">Meslek</option>
                {MESLEKLER.map((meslek) => (
                  <option key={meslek} value={meslek}>{meslek.replace('_', ' ')}</option>
                ))}
              </select>
              {fieldErrors.meslek && <span className="text-xs text-red-600">{fieldErrors.meslek}</span>}
            </div>
            <Button type="submit" disabled={loading} className="w-full mt-4">
              {loading ? 'Kayıt olunuyor...' : 'Kayıt Ol'}
            </Button>
            {error && (
              <div className="rounded-md bg-red-50 p-4 mt-2">
                <div className="text-sm text-red-700">{error}</div>
              </div>
            )}
            {success && (
              <div className="rounded-md bg-green-50 p-4 mt-2">
                <div className="text-sm text-green-700">{success}</div>
              </div>
            )}
            <div className="w-full flex flex-col items-center mt-2">
              <button onClick={() => navigate('/login')} className="text-indigo-600 hover:underline text-sm">Zaten hesabınız var mı? <span className="font-semibold">Giriş Yap</span></button>
              <div className="text-xs text-gray-400 mt-4">© 2025 KolayVergi. Tüm hakları saklıdır.</div>
            </div>
          </div>
        </form>
      </Card>
    </div>
  );
};

export default Register; 