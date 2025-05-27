import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { alisverisService } from '../../services/alisverisService';
import Button from '../../components/ui/Button';

const initialForm = {
  urunTuru: '',
  tutar: '',
  taksitSayisi: '',
  aracBilgisi: {
    marka: '',
    model: '',
    ilkTescilYili: '',
    motorSilindirHacmi: '',
    aracTipi: '',
    aracYasi: '',
    aracKapasitesi: '',
    aracAgirligi: ''
  }
};

const ILK_TESCIL_YILI_OPTIONS = [
  { value: '2017 ve öncesi', label: '2017 ve öncesi' },
  { value: '2018', label: '2018' },
  { value: '2019', label: '2019' },
  { value: '2020', label: '2020' },
  { value: '2021', label: '2021' },
  { value: '2022', label: '2022' },
  { value: '2023', label: '2023' },
  { value: '2024', label: '2024' },
  { value: '2025', label: '2025' },
];
const MOTOR_SILINDIR_HACMI_OPTIONS = [
  { value: '0-1300 cm³', label: '0-1300 cm³' },
  { value: '1301-1600 cm³', label: '1301-1600 cm³' },
  { value: '1601-1800 cm³', label: '1601-1800 cm³' },
  { value: '1801-2000 cm³', label: '1801-2000 cm³' },
  { value: '2001-2500 cm³', label: '2001-2500 cm³' },
  { value: '2501-3000 cm³', label: '2501-3000 cm³' },
  { value: '3001-3500 cm³', label: '3001-3500 cm³' },
  { value: '3501-4000 cm³', label: '3501-4000 cm³' },
  { value: '4001+ cm³', label: '4001+ cm³' },
];
const ARAC_TIPI_OPTIONS = [
  { value: 'Otomobil-Kaptıkaçtı-Arazi Taşıtı ve benzerleri', label: 'Otomobil-Kaptıkaçtı-Arazi Taşıtı ve benzerleri' },
  { value: 'Minibüsler', label: 'Minibüsler' },
  { value: 'Otobüs ve benzerleri', label: 'Otobüs ve benzerleri' },
  { value: 'Kamyon-Kamyonet-Çekici ve benzerleri', label: 'Kamyon-Kamyonet-Çekici ve benzerleri' },
];
const ARAC_YASI_OPTIONS = [
  { value: '1-3 yaş', label: '1-3 yaş' },
  { value: '4-6 yaş', label: '4-6 yaş' },
  { value: '7-11 yaş', label: '7-11 yaş' },
  { value: '12-15 yaş', label: '12-15 yaş' },
  { value: '16+ yaş', label: '16+ yaş' },
];
const ARAC_KAPASITESI_OPTIONS = [
  { value: '25 kişiye kadar', label: '25 kişiye kadar' },
  { value: '26-35 kişiye kadar', label: '26-35 kişiye kadar' },
  { value: '36-45 kişiye kadar', label: '36-45 kişiye kadar' },
  { value: '46 kişi ve yukarısı', label: '46 kişi ve yukarısı' },
];
const ARAC_AGIRLIGI_OPTIONS = [
  { value: '1-1.500 kg arası', label: '1-1.500 kg arası' },
  { value: '1.501-3.500 kg arası', label: '1.501-3.500 kg arası' },
  { value: '3.501-5.000 kg arası', label: '3.501-5.000 kg arası' },
  { value: '5.001-10.000 kg arası', label: '5.001-10.000 kg arası' },
  { value: '10.001-20.000 kg arası', label: '10.001-20.000 kg arası' },
  { value: '20.001 kg ve yukarısı', label: '20.001 kg ve yukarısı' },
];

const URUN_TURU_OPTIONS = [
  { value: 'OTOMOBIL', label: 'Otomobil' },
  { value: 'GIDA', label: 'Gıda' },
  { value: 'ELEKTRONIK', label: 'Elektronik' },
  { value: 'GIYIM', label: 'Giyim' },
  { value: 'KITAP', label: 'Kitap' },
  { value: 'KOZMETIK', label: 'Kozmetik' },
  { value: 'MOBILYA', label: 'Mobilya' },
  { value: 'BEYAZ_ESYA', label: 'Beyaz Eşya' },
];

const AlisverisEkle = () => {
  const [form, setForm] = useState(initialForm);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name.startsWith('aracBilgisi.')) {
      setForm({
        ...form,
        aracBilgisi: {
          ...form.aracBilgisi,
          [name.replace('aracBilgisi.', '')]: value
        }
      });
    } else {
      setForm({ ...form, [name]: value });
    }
  };

  const handleUrunTuruChange = (e) => {
    const value = e.target.value;
    setForm({
      ...form,
      urunTuru: value,
      aracBilgisi: value === 'OTOMOBIL' ? form.aracBilgisi : initialForm.aracBilgisi
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setSuccess(null);
    setLoading(true);
    try {
      const payload = {
        urunTuru: form.urunTuru,
        tutar: parseFloat(form.tutar),
        taksitSayisi: parseInt(form.taksitSayisi, 10),
        aracBilgisi: form.urunTuru === 'OTOMOBIL' ? form.aracBilgisi : undefined
      };
      console.log('GÖNDERİLEN PAYLOAD:', payload);
      await alisverisService.createAlisveris(payload);
      setSuccess('Alışveriş başarıyla eklendi!');
      setTimeout(() => navigate('/alisverislerim'), 1200);
    } catch (err) {
      setError('Alışveriş eklenirken bir hata oluştu.');
      console.error('Alışveriş eklenirken hata:', err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mx-auto px-4 py-8 max-w-2xl">
      <h1 className="text-2xl font-semibold mb-6 text-gray-800">Yeni Alışveriş Ekle</h1>
      <form onSubmit={handleSubmit} className="bg-white rounded-lg shadow p-6 space-y-4">
        <div>
          <label className="block mb-1 font-medium">Ürün Türü</label>
          <select
            name="urunTuru"
            value={form.urunTuru}
            onChange={handleUrunTuruChange}
            className="w-full border rounded px-3 py-2"
            required
          >
            <option value="">Seçiniz</option>
            {URUN_TURU_OPTIONS.map(opt => <option key={opt.value} value={opt.value}>{opt.label}</option>)}
          </select>
        </div>
        <div>
          <label className="block mb-1 font-medium">Tutar (₺)</label>
          <input
            type="number"
            name="tutar"
            value={form.tutar}
            onChange={handleChange}
            className="w-full border rounded px-3 py-2"
            required
            min="1"
          />
        </div>
        <div>
          <label className="block mb-1 font-medium">Taksit Sayısı</label>
          <input
            type="number"
            name="taksitSayisi"
            value={form.taksitSayisi}
            onChange={handleChange}
            className="w-full border rounded px-3 py-2"
            required
            min="1"
          />
        </div>
        {form.urunTuru === 'OTOMOBIL' && (
          <div className="border rounded p-4 bg-gray-50">
            <div className="font-semibold mb-2">Araç Bilgisi</div>
            <div className="grid grid-cols-2 gap-4">
              <input type="text" name="aracBilgisi.marka" value={form.aracBilgisi.marka} onChange={handleChange} placeholder="Marka" className="border rounded px-3 py-2" required />
              <input type="text" name="aracBilgisi.model" value={form.aracBilgisi.model} onChange={handleChange} placeholder="Model" className="border rounded px-3 py-2" required />
              <select name="aracBilgisi.ilkTescilYili" value={form.aracBilgisi.ilkTescilYili} onChange={handleChange} className="border rounded px-3 py-2" required>
                <option value="">İlk Tescil Yılı</option>
                {ILK_TESCIL_YILI_OPTIONS.map(opt => <option key={opt.value} value={opt.value}>{opt.label}</option>)}
              </select>
              <select name="aracBilgisi.motorSilindirHacmi" value={form.aracBilgisi.motorSilindirHacmi} onChange={handleChange} className="border rounded px-3 py-2" required>
                <option value="">Motor Silindir Hacmi</option>
                {MOTOR_SILINDIR_HACMI_OPTIONS.map(opt => <option key={opt.value} value={opt.value}>{opt.label}</option>)}
              </select>
              <select name="aracBilgisi.aracTipi" value={form.aracBilgisi.aracTipi} onChange={handleChange} className="border rounded px-3 py-2" required>
                <option value="">Araç Tipi</option>
                {ARAC_TIPI_OPTIONS.map(opt => <option key={opt.value} value={opt.value}>{opt.label}</option>)}
              </select>
              <select name="aracBilgisi.aracYasi" value={form.aracBilgisi.aracYasi} onChange={handleChange} className="border rounded px-3 py-2" required>
                <option value="">Araç Yaşı</option>
                {ARAC_YASI_OPTIONS.map(opt => <option key={opt.value} value={opt.value}>{opt.label}</option>)}
              </select>
              <select name="aracBilgisi.aracKapasitesi" value={form.aracBilgisi.aracKapasitesi} onChange={handleChange} className="border rounded px-3 py-2" required>
                <option value="">Araç Kapasitesi</option>
                {ARAC_KAPASITESI_OPTIONS.map(opt => <option key={opt.value} value={opt.value}>{opt.label}</option>)}
              </select>
              <select name="aracBilgisi.aracAgirligi" value={form.aracBilgisi.aracAgirligi} onChange={handleChange} className="border rounded px-3 py-2" required>
                <option value="">Araç Ağırlığı</option>
                {ARAC_AGIRLIGI_OPTIONS.map(opt => <option key={opt.value} value={opt.value}>{opt.label}</option>)}
              </select>
            </div>
          </div>
        )}
        {error && <div className="text-red-500 text-center">{error}</div>}
        {success && <div className="text-green-600 text-center">{success}</div>}
        <div className="flex justify-end gap-2 mt-6">
          <Button type="secondary" onClick={() => navigate(-1)} disabled={loading}>
            Vazgeç
          </Button>
          <Button type="primary" htmlType="submit" disabled={loading}>
            {loading ? 'Kaydediliyor...' : 'Kaydet'}
          </Button>
        </div>
      </form>
    </div>
  );
};

export default AlisverisEkle; 