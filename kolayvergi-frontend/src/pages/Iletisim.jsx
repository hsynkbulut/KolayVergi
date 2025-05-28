import React, { useState } from 'react';
import Icon from '../components/ui/Icon';

const Iletisim = () => {
  const [form, setForm] = useState({ ad: '', email: '', mesaj: '' });
  const [success, setSuccess] = useState(false);
  const [error, setError] = useState('');

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setError('');
    setSuccess(false);
    // Burada backend'e gönderme işlemi yapılabilir
    if (!form.ad || !form.email || !form.mesaj) {
      setError('Lütfen tüm alanları doldurun.');
      return;
    }
    setSuccess(true);
    setForm({ ad: '', email: '', mesaj: '' });
  };

  return (
    <div className="w-full max-w-2xl mx-auto px-4 py-10">
      <div className="flex flex-col items-center mb-8">
        <div className="bg-blue-100 rounded-full p-4 mb-3 shadow-sm">
          <Icon name="FiMail" className="w-10 h-10 text-blue-500" />
        </div>
        <h1 className="text-3xl font-extrabold text-gray-900 mb-2 text-center">İletişim</h1>
        <p className="text-gray-500 text-center text-base">Bizimle iletişime geçmekten çekinmeyin. Size en kısa sürede dönüş yapacağız.</p>
      </div>
      <div className="bg-white rounded-2xl shadow-xl p-8 border border-gray-100 mb-8">
        <h2 className="text-xl font-bold mb-4 text-blue-900">Şirket Bilgileri</h2>
        <div className="space-y-2 text-gray-700">
          <div><b>Adres:</b> Örnek Mah. KolayVergi Cad. No: 123, İstanbul</div>
          <div><b>Telefon:</b> <a href="tel:+902121234567" className="text-blue-700 hover:underline">(0212) 123 45 67</a></div>
          <div><b>E-posta:</b> <a href="mailto:destek@kolayvergi.com" className="text-blue-700 hover:underline">destek@kolayvergi.com</a></div>
        </div>
      </div>
      <div className="bg-white rounded-2xl shadow-xl p-8 border border-gray-100">
        <h2 className="text-xl font-bold mb-4 text-blue-900">İletişim Formu</h2>
        <form onSubmit={handleSubmit} className="space-y-5">
          <div>
            <label className="block mb-1 font-semibold text-gray-700">Ad Soyad</label>
            <input
              type="text"
              name="ad"
              value={form.ad}
              onChange={handleChange}
              className="w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-blue-300 focus:border-blue-500 transition"
              required
              placeholder="Adınızı ve soyadınızı girin"
            />
          </div>
          <div>
            <label className="block mb-1 font-semibold text-gray-700">E-posta</label>
            <input
              type="email"
              name="email"
              value={form.email}
              onChange={handleChange}
              className="w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-blue-300 focus:border-blue-500 transition"
              required
              placeholder="E-posta adresinizi girin"
            />
          </div>
          <div>
            <label className="block mb-1 font-semibold text-gray-700">Mesajınız</label>
            <textarea
              name="mesaj"
              value={form.mesaj}
              onChange={handleChange}
              className="w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-blue-300 focus:border-blue-500 transition"
              required
              rows={5}
              placeholder="Mesajınızı yazın"
            />
          </div>
          {error && <div className="text-red-500 text-sm font-semibold">{error}</div>}
          {success && <div className="text-green-600 text-sm font-semibold">Mesajınız başarıyla gönderildi!</div>}
          <button
            type="submit"
            className="bg-gradient-to-r from-blue-500 to-blue-600 hover:from-blue-600 hover:to-blue-700 text-white font-semibold rounded-lg px-6 py-2 shadow-sm transition-all"
          >
            Gönder
          </button>
        </form>
      </div>
    </div>
  );
};

export default Iletisim; 