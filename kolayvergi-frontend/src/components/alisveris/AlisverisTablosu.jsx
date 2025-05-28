import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Table from '../ui/Table';
import Button from '../ui/Button';
import ConfirmModal from '../ui/ConfirmModal';
import { alisverisService } from '../../services/alisverisService';

export const AlisverisTablosu = () => {
  const [alisverisler, setAlisverisler] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [modalOpen, setModalOpen] = useState(false);
  const [silId, setSilId] = useState(null);
  const [silLoading, setSilLoading] = useState(false);
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

  const handleSil = (id) => {
    setSilId(id);
    setModalOpen(true);
  };

  const handleModalClose = () => {
    setModalOpen(false);
    setSilId(null);
    setSilLoading(false);
  };

  const handleModalSil = async () => {
    if (!silId) return;
    setSilLoading(true);
      try {
      await alisverisService.deleteAlisveris(silId);
        fetchAlisverisler();
      } catch (err) {
        console.error('Alışveriş silinirken hata:', err);
    } finally {
      handleModalClose();
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

  const columns = [
    {
      header: 'Ürün Türü',
      accessor: 'urunTuru',
      cell: (value) => urunTuruLabel(value)
    },
    {
      header: 'Tutar',
      accessor: 'tutar',
      cell: (value) => <span className="font-bold">{`${value.toLocaleString('tr-TR')} ₺`}</span>
    },
    {
      header: 'Taksit Sayısı',
      accessor: 'taksitSayisi',
      cell: (value) => <span className="font-bold">{value}</span>
    },
    {
      header: 'İşlemler',
      accessor: 'id',
      cell: (id) => (
        <div className="flex gap-2">
          <button
            onClick={() => handleDetay(id)}
            className="flex items-center gap-1 rounded-full bg-blue-50 text-blue-700 hover:bg-blue-100 px-4 py-2 transition-colors duration-150 shadow-sm font-medium text-sm"
            title="Detay"
          >
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={2} stroke="currentColor" className="w-5 h-5"><path strokeLinecap="round" strokeLinejoin="round" d="M2.25 12C3.5 7.5 7.5 4.5 12 4.5s8.5 3 9.75 7.5c-1.25 4.5-5.25 7.5-9.75 7.5s-8.5-3-9.75-7.5z" /><circle cx="12" cy="12" r="3" /></svg>
            <span>Detay</span>
          </button>
          <button
            onClick={() => handleDuzenle(id)}
            className="flex items-center gap-1 rounded-full bg-yellow-50 text-yellow-700 hover:bg-yellow-100 px-4 py-2 transition-colors duration-150 shadow-sm font-medium text-sm"
            title="Düzenle"
          >
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={2} stroke="currentColor" className="w-5 h-5"><path strokeLinecap="round" strokeLinejoin="round" d="M16.862 5.487l1.65 1.65a2.121 2.121 0 010 3l-8.486 8.486a2 2 0 01-.878.515l-4.243 1.06a.5.5 0 01-.606-.606l1.06-4.243a2 2 0 01.515-.878l8.486-8.486a2.121 2.121 0 013 0z" /></svg>
            <span>Düzenle</span>
          </button>
          <button
            onClick={() => handleSil(id)}
            className="flex items-center gap-1 rounded-full bg-red-50 text-red-700 hover:bg-red-100 px-4 py-2 transition-colors duration-150 shadow-sm font-medium text-sm"
            title="Sil"
          >
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={2} stroke="currentColor" className="w-5 h-5"><path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" /></svg>
            <span>Sil</span>
          </button>
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
    <>
    <Table
      columns={columns}
      data={alisverisler}
      emptyMessage="Henüz alışveriş bulunmuyor."
        className="rounded-2xl shadow-lg border border-blue-100"
        theadClassName="bg-blue-50 text-lg font-bold text-gray-700"
        trClassName="border-b border-gray-100"
        tdClassName="py-4"
      />
      <ConfirmModal
        open={modalOpen}
        onClose={handleModalClose}
        onConfirm={handleModalSil}
        loading={silLoading}
        icon={
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={2} stroke="currentColor" className="w-12 h-12 text-red-500"><path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" /></svg>
        }
        iconClass="bg-red-100"
        title="Silmek istediğinize emin misiniz?"
        description="Bu işlem geri alınamaz. Bu alışveriş kalıcı olarak silinecek."
        cancelText="Vazgeç"
        confirmText="Evet, Sil"
        confirmColor="bg-red-600 hover:bg-red-700"
      />
    </>
  );
}; 