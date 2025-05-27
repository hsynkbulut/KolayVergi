import React from 'react';
import { useNavigate } from 'react-router-dom';
import Button from '../../components/ui/Button';
import { AlisverisTablosu } from '../../components/alisveris/AlisverisTablosu';

const AlisverisListesi = () => {
  const navigate = useNavigate();

  const handleYeniAlisveris = () => {
    navigate('/alisveris/yeni');
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-semibold text-gray-800">Alışverişlerim</h1>
        <Button
          onClick={handleYeniAlisveris}
          variant="primary"
        >
          Yeni Alışveriş Ekle
        </Button>
      </div>
      
      <div className="bg-white rounded-lg shadow">
        <AlisverisTablosu />
      </div>
    </div>
  );
};

export default AlisverisListesi; 