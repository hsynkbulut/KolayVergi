import React from 'react';
import { useNavigate } from 'react-router-dom';
import Button from '../../components/ui/Button';
import Icon from '../../components/ui/Icon';
import { AlisverisTablosu } from '../../components/alisveris/AlisverisTablosu';

const AlisverisListesi = () => {
  const navigate = useNavigate();

  const handleYeniAlisveris = () => {
    navigate('/alisveris/yeni');
  };

  return (
    <div className="relative w-full">
      <div className="flex justify-between items-center mb-8 mt-2">
        <h1 className="text-4xl font-extrabold text-gray-900 tracking-tight drop-shadow-sm select-none border-b-0">
          Alışverişlerim
        </h1>
        <button
          onClick={handleYeniAlisveris}
          className="flex items-center gap-2 bg-gradient-to-r from-[#2563eb] to-blue-700 hover:from-blue-700 hover:to-blue-800 focus:ring-2 focus:ring-blue-300 text-white font-semibold rounded-lg px-6 py-3 shadow-md transition-all duration-200 outline-none text-base active:scale-95"
          title="Yeni Alışveriş Ekle"
        >
          <Icon name="FiShoppingBag" className="w-5 h-5" />
          <span>Yeni Alışveriş Ekle</span>
        </button>
      </div>
      <AlisverisTablosu />
    </div>
  );
};

export default AlisverisListesi; 