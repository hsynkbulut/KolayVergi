import React, { useEffect, useState } from "react";
import axiosInstance from "../services/axios.config";
import Icon from "../components/ui/Icon";

const Borclarim = () => {
  const [borc, setBorc] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    axiosInstance
      .get("/borclar")
      .then((res) => {
        setBorc(res.data);
        setLoading(false);
      })
      .catch((err) => {
        setError("Borç bilgisi alınamadı.");
        setLoading(false);
      });
  }, []);

  if (loading)
    return (
      <div className="flex items-center justify-center h-[60vh]">
        <div className="text-lg animate-pulse">Yükleniyor...</div>
      </div>
    );
  if (error)
    return (
      <div className="flex items-center justify-center h-[60vh]">
        <div className="text-red-500 text-lg font-semibold">{error}</div>
      </div>
    );

  // Ödenen borç miktarını hesapla
  const odenenBorc = borc?.toplamBorc - borc?.kalanBorc;
  // Ödeme yüzdesini hesapla
  const odemeYuzdesi = ((odenenBorc / borc?.toplamBorc) * 100).toFixed(1);

  return (
    <div className="w-full flex flex-col items-center justify-center py-8 px-4">
      {/* Başlık Bölümü */}
      <div className="flex flex-col items-center mb-8">
        <div className="bg-blue-100 rounded-full p-4 mb-3 shadow-sm">
          <Icon name="FiDollarSign" className="w-10 h-10 text-blue-500" />
        </div>
        <h2 className="text-3xl font-extrabold text-gray-900 mb-2 text-center">Borç Bilgilerim</h2>
        <p className="text-gray-500 text-center text-base">Borç durumunuzu takip edin ve ödemelerinizi planlayın</p>
      </div>

      {/* Ana Kart */}
      <div className="w-full max-w-4xl mx-auto bg-white rounded-2xl shadow-xl p-8 border border-gray-100">
        {/* İlerleme Çubuğu */}
        <div className="mb-8">
          <div className="flex justify-between items-center mb-2">
            <span className="text-sm font-medium text-gray-600">Ödeme İlerlemesi</span>
            <span className="text-sm font-semibold text-blue-600">{odemeYuzdesi}%</span>
          </div>
          <div className="w-full bg-gray-200 rounded-full h-4">
            <div
              className="bg-gradient-to-r from-blue-500 to-blue-600 h-4 rounded-full transition-all duration-500"
              style={{ width: `${odemeYuzdesi}%` }}
            ></div>
          </div>
        </div>

        {/* Borç Detayları Grid */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {/* Toplam Borç Kartı */}
          <div className="bg-gradient-to-br from-blue-50 to-blue-100 rounded-xl p-6 shadow-sm border border-blue-100">
            <div className="flex items-center gap-3 mb-3">
              <div className="bg-blue-500/10 p-2 rounded-lg">
                <Icon name="FiDollarSign" className="w-6 h-6 text-blue-600" />
              </div>
              <h3 className="text-lg font-semibold text-gray-700">Toplam Borç</h3>
            </div>
            <p className="text-2xl font-bold text-blue-700">{borc?.toplamBorc?.toLocaleString('tr-TR')} ₺</p>
            <p className="text-sm text-gray-500 mt-1">Toplam borç tutarınız</p>
          </div>

          {/* Kalan Borç Kartı */}
          <div className="bg-gradient-to-br from-red-50 to-red-100 rounded-xl p-6 shadow-sm border border-red-100">
            <div className="flex items-center gap-3 mb-3">
              <div className="bg-red-500/10 p-2 rounded-lg">
                <Icon name="FiAlertCircle" className="w-6 h-6 text-red-600" />
              </div>
              <h3 className="text-lg font-semibold text-gray-700">Kalan Borç</h3>
            </div>
            <p className="text-2xl font-bold text-red-700">{borc?.kalanBorc?.toLocaleString('tr-TR')} ₺</p>
            <p className="text-sm text-gray-500 mt-1">Ödenecek kalan tutar</p>
          </div>

          {/* Ödenen Borç Kartı */}
          <div className="bg-gradient-to-br from-green-50 to-green-100 rounded-xl p-6 shadow-sm border border-green-100">
            <div className="flex items-center gap-3 mb-3">
              <div className="bg-green-500/10 p-2 rounded-lg">
                <Icon name="FiCheckCircle" className="w-6 h-6 text-green-600" />
              </div>
              <h3 className="text-lg font-semibold text-gray-700">Ödenen Borç</h3>
            </div>
            <p className="text-2xl font-bold text-green-700">{odenenBorc?.toLocaleString('tr-TR')} ₺</p>
            <p className="text-sm text-gray-500 mt-1">Toplam ödenen tutar</p>
          </div>
        </div>

        {/* Bilgi Kartı */}
        <div className="mt-8 bg-gray-50 rounded-xl p-6 border border-gray-200">
          <div className="flex items-start gap-4">
            <div className="bg-blue-100 p-3 rounded-lg">
              <Icon name="FiInfo" className="w-6 h-6 text-blue-600" />
            </div>
            <div>
              <h4 className="text-lg font-semibold text-gray-800 mb-2">Borç Ödeme Bilgilendirmesi</h4>
              <p className="text-gray-600">
                Borçlarınızı zamanında ödemeniz, kredi notunuzu olumlu etkileyecek ve gelecekteki kredi başvurularınızda avantaj sağlayacaktır. 
                Taksit ödemelerinizi "Taksit Ödeme" sayfasından kolayca gerçekleştirebilirsiniz.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Borclarim; 