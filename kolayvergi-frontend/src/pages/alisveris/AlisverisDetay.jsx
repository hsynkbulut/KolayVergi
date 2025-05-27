import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { alisverisService } from '../../services/alisverisService';
import Button from '../../components/ui/Button';

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

  if (loading) return <div className="text-center py-8">Yükleniyor...</div>;
  if (error) return <div className="text-center text-red-500 py-8">{error}</div>;
  if (!alisveris) return null;

  return (
    <div className="container mx-auto px-4 py-8 max-w-2xl">
      <h1 className="text-2xl font-semibold mb-6 text-gray-800">Alışveriş Detayı</h1>
      <div className="bg-white rounded-lg shadow p-6 space-y-4">
        <div><b>Ürün Türü:</b> {alisveris.urunTuru === 'OTOMOBIL' ? 'Otomobil' : 'Diğer Ürün'}</div>
        <div><b>Tutar:</b> {alisveris.tutar.toLocaleString('tr-TR')} ₺</div>
        <div><b>Taksit Sayısı:</b> {alisveris.taksitSayisi}</div>
        {alisveris.aracBilgisi && (
          <div className="mt-4">
            <b>Araç Bilgisi:</b>
            <ul className="list-disc ml-6 mt-2 text-sm text-gray-700">
              <li><b>Marka:</b> {alisveris.aracBilgisi.marka}</li>
              <li><b>Model:</b> {alisveris.aracBilgisi.model}</li>
              <li><b>İlk Tescil Yılı:</b> {alisveris.aracBilgisi.ilkTescilYili}</li>
              <li><b>Motor Silindir Hacmi:</b> {alisveris.aracBilgisi.motorSilindirHacmi}</li>
              <li><b>Araç Tipi:</b> {alisveris.aracBilgisi.aracTipi}</li>
              <li><b>Araç Yaşı:</b> {alisveris.aracBilgisi.aracYasi}</li>
              <li><b>Araç Kapasitesi:</b> {alisveris.aracBilgisi.aracKapasitesi}</li>
              <li><b>Araç Ağırlığı:</b> {alisveris.aracBilgisi.aracAgirligi}</li>
            </ul>
          </div>
        )}
        <div className="flex justify-end mt-6">
          <Button type="secondary" onClick={() => navigate(-1)}>
            Geri Dön
          </Button>
        </div>
      </div>
    </div>
  );
};

export default AlisverisDetay; 