import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../services/axios.config";
import Icon from "../components/ui/Icon";
import Table from "../components/ui/Table";
import Button from "../components/ui/Button";
import { useAuth } from '../context/AuthContext';

const AnaSayfa = () => {
  const navigate = useNavigate();
  const { user } = useAuth();
  const [borc, setBorc] = useState(null);
  const [sonAlisverisler, setSonAlisverisler] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const borcRes = await axiosInstance.get("/borclar");
        setBorc(borcRes.data);

        const alisverisRes = await axiosInstance.get("/alisverisler/benim-alisverislerim");
        const alisverisler = alisverisRes.data
          .sort((a, b) => new Date(b.olusturulmaTarihi) - new Date(a.olusturulmaTarihi))
          .slice(0, 5);
        setSonAlisverisler(alisverisler);

        setLoading(false);
      } catch (err) {
        setError("Veriler alınamadı.");
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading)
    return (
      <div className="flex items-center justify-center h-[60vh]">
        <div className="text-lg animate-pulse">Yükleniyor...</div>
      </div>
    );
  if (error)
    return (
      <div className="flex flex-col items-center justify-center h-[60vh] gap-4">
        <Icon name="FiAlertTriangle" className="w-16 h-16 text-red-400" />
        <div className="text-red-500 text-lg font-semibold">Veriler alınırken bir hata oluştu. Lütfen daha sonra tekrar deneyin.</div>
      </div>
    );

  if (!borc && (!sonAlisverisler || sonAlisverisler.length === 0)) {
    return (
      <div className="flex flex-col items-center justify-center h-[60vh] gap-4">
        <Icon name="FiInbox" className="w-20 h-20 text-blue-300" />
        <div className="text-gray-500 text-xl font-semibold">Henüz herhangi bir veriniz bulunmuyor.</div>
        <div className="text-gray-400">Alışveriş ekleyerek veya borç oluşturarak başlayabilirsiniz.</div>
      </div>
    );
  }

  const hizliErisimButonlari = [
    {
      icon: "FiShoppingCart",
      label: "Alışveriş Ekle",
      onClick: () => navigate("/alisveris/yeni"),
      color: "from-blue-500 to-blue-600",
    },
    {
      icon: "FiCreditCard",
      label: "Borç Öde",
      onClick: () => navigate("/taksit-odeme"),
      color: "from-green-500 to-green-600",
    },
    {
      icon: "FiList",
      label: "Borçlarım",
      onClick: () => navigate("/borclarim"),
      color: "from-purple-500 to-purple-600",
    },
    {
      icon: "FiShoppingBag",
      label: "Alışverişlerim",
      onClick: () => navigate("/alisverislerim"),
      color: "from-orange-500 to-orange-600",
    },
  ];

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
    { header: "Ürün Türü", accessor: "urunTuru", cell: v => urunTuruLabel(v) },
    { header: "Tutar", accessor: "tutar", cell: v => v.toLocaleString('tr-TR') + " ₺" },
    { header: "Taksit", accessor: "taksitSayisi", cell: v => v + " Taksit" },
    {
      header: "İşlem",
      accessor: "id",
      cell: (_, row) => (
        <Button
          onClick={() => navigate(`/alisverislerim/${row.id}`)}
          className="bg-gradient-to-r from-blue-500 to-blue-600 hover:from-blue-600 hover:to-blue-700 text-white font-semibold rounded-lg px-3 py-1.5 shadow-sm flex items-center gap-1 text-sm"
        >
          <Icon name="FiEye" className="w-4 h-4" />
          Detay
        </Button>
      ),
    },
  ];

  return (
    <div className="w-full flex flex-col items-center py-8 px-4">
      {/* Başlık */}
      <div className="flex flex-col items-center mb-8">
        <div className="bg-blue-100 rounded-full p-4 mb-3 shadow-sm">
          <Icon name="FiHome" className="w-10 h-10 text-blue-500" />
        </div>
        <h2 className="text-3xl font-extrabold text-gray-900 mb-2 text-center">Ana Sayfa</h2>
        <p className="text-gray-500 text-center text-base">KolayVergi'ye hoş geldiniz. İşlemlerinizi kolayca gerçekleştirin.</p>
      </div>

      {/* Ana Grid */}
      <div className="w-full max-w-7xl mx-auto grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Sol Kolon - Kullanıcı Bilgileri ve Hızlı Erişim */}
        <div className="lg:col-span-1 space-y-6">
          {/* Kullanıcı Bilgileri Kartı */}
          <div className="bg-white rounded-2xl shadow-xl p-6 border border-gray-100">
            <div className="flex items-center gap-4 mb-4">
              <div className="bg-blue-100 p-3 rounded-full">
                <Icon name="FiUser" className="w-8 h-8 text-blue-600" />
              </div>
              <div>
                <h3 className="text-xl font-bold text-gray-900">{user?.ad} {user?.soyad}</h3>
                <p className="text-gray-500">{user?.email}</p>
              </div>
            </div>
            <div className="space-y-2">
              <div className="flex items-center gap-2 text-gray-600">
                <Icon name="FiMail" className="w-5 h-5" />
                <span>{user?.email}</span>
              </div>
            </div>
          </div>

          {/* Hızlı Erişim Menüsü */}
          <div className="bg-white rounded-2xl shadow-xl p-6 border border-gray-100">
            <h3 className="text-lg font-semibold text-gray-900 mb-4">Hızlı Erişim</h3>
            <div className="grid grid-cols-2 gap-3">
              {hizliErisimButonlari.map((btn, index) => (
                <button
                  key={index}
                  onClick={btn.onClick}
                  className={`bg-gradient-to-r ${btn.color} hover:opacity-90 text-white font-semibold rounded-xl p-4 shadow-sm flex flex-col items-center gap-2 transition-all duration-200`}
                >
                  <Icon name={btn.icon} className="w-6 h-6" />
                  <span className="text-sm">{btn.label}</span>
                </button>
              ))}
            </div>
          </div>
        </div>

        {/* Sağ Kolon - Borç Özeti ve Son Alışverişler */}
        <div className="lg:col-span-2 space-y-6">
          {/* Borç Özeti Kartı */}
          <div className="bg-white rounded-2xl shadow-xl p-6 border border-gray-100">
            <div className="flex items-center gap-3 mb-4">
              <div className="bg-blue-100 p-3 rounded-lg">
                <Icon name="FiDollarSign" className="w-6 h-6 text-blue-600" />
              </div>
              <h3 className="text-lg font-semibold text-gray-900">Borç Özeti</h3>
            </div>
            {borc ? (
              <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                <div className="bg-blue-50 rounded-xl p-4">
                  <p className="text-sm text-gray-600 mb-1">Toplam Borç</p>
                  <p className="text-xl font-bold text-blue-700">{borc?.toplamBorc?.toLocaleString('tr-TR')} ₺</p>
                </div>
                <div className="bg-red-50 rounded-xl p-4">
                  <p className="text-sm text-gray-600 mb-1">Kalan Borç</p>
                  <p className="text-xl font-bold text-red-700">{borc?.kalanBorc?.toLocaleString('tr-TR')} ₺</p>
                </div>
                <div className="bg-green-50 rounded-xl p-4">
                  <p className="text-sm text-gray-600 mb-1">Ödenen Borç</p>
                  <p className="text-xl font-bold text-green-700">
                    {(borc?.toplamBorc - borc?.kalanBorc)?.toLocaleString('tr-TR')} ₺
                  </p>
                </div>
              </div>
            ) : (
              <div className="flex flex-col items-center justify-center py-8">
                <Icon name="FiInbox" className="w-12 h-12 text-blue-200 mb-2" />
                <div className="text-gray-400 text-base">Henüz borç kaydınız bulunmuyor.</div>
              </div>
            )}
          </div>

          {/* Son Alışverişler Tablosu */}
          <div className="bg-white rounded-2xl shadow-xl p-6 border border-gray-100">
            <div className="flex items-center justify-between mb-4">
              <div className="flex items-center gap-3">
                <div className="bg-blue-100 p-3 rounded-lg">
                  <Icon name="FiShoppingBag" className="w-6 h-6 text-blue-600" />
                </div>
                <h3 className="text-lg font-semibold text-gray-900">Son Alışverişler</h3>
              </div>
              <Button
                onClick={() => navigate("/alisverislerim")}
                className="bg-gradient-to-r from-blue-500 to-blue-600 hover:from-blue-600 hover:to-blue-700 text-white font-semibold rounded-lg px-4 py-2 shadow-sm flex items-center gap-2"
              >
                <Icon name="FiList" className="w-5 h-5" />
                Tümünü Gör
              </Button>
            </div>
            <Table
              columns={columns}
              data={sonAlisverisler}
              emptyMessage="Henüz alışveriş bulunmuyor"
              className="rounded-xl shadow-sm border border-gray-100"
              theadClassName="bg-gray-50 text-gray-700"
              trClassName="border-b border-gray-100"
              tdClassName="py-3"
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default AnaSayfa; 