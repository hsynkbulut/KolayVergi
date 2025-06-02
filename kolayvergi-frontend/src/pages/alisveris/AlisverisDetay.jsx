import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { alisverisService } from '../../services/alisverisService';
import Button from '../../components/ui/Button';
import Icon from '../../components/ui/Icon';

const AlisverisDetay = () => {
  const { id } = useParams();
  const [alisveris, setAlisveris] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetchAlisverisDetay();
    // eslint-disable-next-line
  }, [id]);

  const fetchAlisverisDetay = async () => {
    try {
      setLoading(true);
      const response = await alisverisService.getAlisverisById(id);
      setAlisveris(response.data);
    } catch (err) {
      setError('Alışveriş detayı yüklenirken bir hata oluştu.');
      console.error('Alışveriş detayı yüklenirken hata:', err);
    } finally {
      setLoading(false);
    }
  };

  const urunTuruLabel = (value) => {
    if (value === 'OTOMOBIL') return 'Otomobil';
    const map = {
      GIDA: 'Gıda',
      ELEKTRONIK: 'Elektronik',
      GIYIM: 'Giyim',
      KITAP: 'Kitap',
      KOZMETIK: 'Kozmetik',
      MOBILYA: 'Mobilya',
      BEYAZ_ESYA: 'Beyaz Eşya',
    };
    return map[value] || value;
  };

  if (loading) return <div className="text-center py-8">Yükleniyor...</div>;
  if (error) return <div className="text-center text-red-500 py-8">{error}</div>;
  if (!alisveris) return null;

  return (
    <div className="w-full max-w-3xl mx-auto px-2 py-8">
      {/* Geri Butonu */}
      <button
        type="button"
        onClick={() => navigate(-1)}
        className="flex items-center gap-2 text-blue-600 hover:text-blue-800 font-medium mb-4"
      >
        <Icon name="FiArrowLeft" className="w-5 h-5" />
        <span>Geri</span>
      </button>
      {/* Başlık ve ikon */}
      <div className="flex flex-col items-center mb-8">
        <div className="bg-blue-100 rounded-full p-4 mb-3 shadow-sm">
          <Icon name="FiShoppingBag" className="w-10 h-10 text-blue-500" />
        </div>
        <h1 className="text-3xl font-extrabold text-gray-900 mb-2 text-center">Alışveriş Detayı</h1>
        <p className="text-gray-500 text-center text-base">Alışveriş detaylarını inceleyin!</p>
      </div>
      <div className="w-full bg-blue-50 rounded-2xl shadow-xl p-8">
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-x-8 gap-y-4">
          <div>
            <div className="text-gray-700 font-semibold">Ürün Türü</div>
            <div className="text-blue-900 font-bold text-lg mt-1">{urunTuruLabel(alisveris.urunTuru)}</div>
          </div>
          <div>
            <div className="text-gray-700 font-semibold">Tutar</div>
            <div className="text-blue-900 font-bold text-lg mt-1">{alisveris.tutar.toLocaleString('tr-TR')} ₺</div>
          </div>
          <div>
            <div className="text-gray-700 font-semibold">Taksit Sayısı</div>
            <div className="text-blue-900 font-bold text-lg mt-1">{alisveris.taksitSayisi}</div>
          </div>
        </div>
        {alisveris.aracBilgisi && (
          <div className="mt-8 bg-white rounded-xl p-6 shadow-sm">
            <div className="flex items-center gap-2 font-semibold mb-4 text-blue-700">
              <Icon name="FiTruck" className="w-5 h-5" />
              Araç Bilgisi
            </div>
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-x-8 gap-y-2 text-gray-700 text-base">
              <div><b>Marka:</b> {alisveris.aracBilgisi.marka}</div>
              <div><b>Model:</b> {alisveris.aracBilgisi.model}</div>
              <div><b>İlk Tescil Yılı:</b> {alisveris.aracBilgisi.ilkTescilYili}</div>
              <div><b>Motor Silindir Hacmi:</b> {alisveris.aracBilgisi.motorSilindirHacmi}</div>
              <div><b>Araç Tipi:</b> {alisveris.aracBilgisi.aracTipi}</div>
              <div><b>Araç Yaşı:</b> {alisveris.aracBilgisi.aracYasi}</div>
              <div><b>Araç Kapasitesi:</b> {alisveris.aracBilgisi.aracKapasitesi}</div>
              <div><b>Araç Ağırlığı:</b> {alisveris.aracBilgisi.aracAgirligi}</div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default AlisverisDetay; 