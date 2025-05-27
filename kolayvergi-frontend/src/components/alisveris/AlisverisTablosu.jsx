import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Table from '../ui/Table';
import Button from '../ui/Button';
import { alisverisService } from '../../services/alisverisService';

export const AlisverisTablosu = () => {
  const [alisverisler, setAlisverisler] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetchAlisverisler();
  }, []);

  const fetchAlisverisler = async () => {
    try {
      setLoading(true);
      const response = await alisverisService.getCurrentUserAlisverisler();
      setAlisverisler(response.data);
    } catch (err) {
      setError('Alışverişler yüklenirken bir hata oluştu.');
      console.error('Alışverişler yüklenirken hata:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleDetay = (id) => {
    navigate(`/alisverislerim/${id}`);
  };

  const handleDuzenle = (id) => {
    navigate(`/alisveris/${id}/duzenle`);
  };

  const handleSil = async (id) => {
    if (window.confirm('Bu alışverişi silmek istediğinizden emin misiniz?')) {
      try {
        await alisverisService.deleteAlisveris(id);
        fetchAlisverisler();
      } catch (err) {
        console.error('Alışveriş silinirken hata:', err);
      }
    }
  };

  const columns = [
    {
      header: 'Ürün Türü',
      accessor: 'urunTuru',
      cell: (value) => value === 'OTOMOBIL' ? 'Otomobil' : 'Diğer Ürün'
    },
    {
      header: 'Tutar',
      accessor: 'tutar',
      cell: (value) => `${value.toLocaleString('tr-TR')} ₺`
    },
    {
      header: 'Taksit Sayısı',
      accessor: 'taksitSayisi'
    },
    {
      header: 'İşlemler',
      accessor: 'id',
      cell: (id) => (
        <div className="flex gap-2">
          <Button
            variant="secondary"
            size="sm"
            onClick={() => handleDetay(id)}
          >
            Detay
          </Button>
          <Button
            variant="primary"
            size="sm"
            onClick={() => handleDuzenle(id)}
          >
            Düzenle
          </Button>
          <Button
            variant="danger"
            size="sm"
            onClick={() => handleSil(id)}
          >
            Sil
          </Button>
        </div>
      )
    }
  ];

  if (loading) {
    return <div className="text-center py-4">Yükleniyor...</div>;
  }

  if (error) {
    return <div className="text-center text-red-500 py-4">{error}</div>;
  }

  return (
    <Table
      columns={columns}
      data={alisverisler}
      emptyMessage="Henüz alışveriş bulunmuyor."
    />
  );
}; 