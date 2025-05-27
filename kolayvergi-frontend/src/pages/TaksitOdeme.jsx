import React, { useEffect, useState } from "react";
import axiosInstance from "../services/axios.config";
import Table from "../components/ui/Table";
import Button from "../components/ui/Button";
import Modal from "../components/ui/Modal";

const ODEME_TURLERI = [
  { value: "NAKIT", label: "Nakit" },
  { value: "KREDI", label: "Kredi" },
  { value: "KREDI_KARTI", label: "Kredi Kartı" },
];

const TaksitOdeme = () => {
  const [taksitler, setTaksitler] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [popupOpen, setPopupOpen] = useState(false);
  const [selectedTaksit, setSelectedTaksit] = useState(null);
  const [odemeTuru, setOdemeTuru] = useState("NAKIT");
  const [odemeBilgisi, setOdemeBilgisi] = useState(null);
  const [odemeLoading, setOdemeLoading] = useState(false);
  const [odemeError, setOdemeError] = useState("");
  const [odemeSuccess, setOdemeSuccess] = useState("");

  // Taksitleri çek
  const fetchTaksitler = () => {
    setLoading(true);
    axiosInstance
      .get("/taksitler")
      .then((res) => {
        // Son ödeme tarihine göre sırala
        const sorted = [...res.data].sort((a, b) => new Date(a.sonOdemeTarihi) - new Date(b.sonOdemeTarihi));
        setTaksitler(sorted);
        setLoading(false);
      })
      .catch(() => {
        setError("Taksitler alınamadı.");
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchTaksitler();
  }, []);

  // Pop-up açıldığında state sıfırla
  const openPopup = (taksit) => {
    setSelectedTaksit(taksit);
    setOdemeTuru("NAKIT");
    setOdemeBilgisi(null);
    setOdemeError("");
    setOdemeSuccess("");
    setPopupOpen(true);
  };

  // Taksit ödeme bilgisi getir
  const handleGetOdemeBilgisi = async () => {
    if (!selectedTaksit) return;
    setOdemeLoading(true);
    setOdemeError("");
    setOdemeBilgisi(null);
    try {
      // Sadece taksitNo ve odemeTuru ile istek at
      const res = await axiosInstance.get(`/odemeler/${selectedTaksit.taksitNo}?odemeTuru=${odemeTuru}`);
      if (!res.data || res.data.guncellenmisTutar == null) {
        setOdemeError("Taksit ödeme bilgisi eksik veya hatalı.");
      } else {
        setOdemeBilgisi(res.data);
      }
    } catch (err) {
      setOdemeError("Taksit ödeme bilgisi alınamadı.");
    }
    setOdemeLoading(false);
  };

  // Taksit öde
  const handleOde = async () => {
    if (!selectedTaksit || !odemeBilgisi || odemeBilgisi.guncellenmisTutar == null) return;
    setOdemeLoading(true);
    setOdemeError("");
    setOdemeSuccess("");
    try {
      await axiosInstance.post("/odemeler/taksit-odeme", {
        taksitNo: selectedTaksit.taksitNo,
        odemeTutari: odemeBilgisi.guncellenmisTutar,
        odemeTuru: odemeTuru,
      });
      setOdemeSuccess("Taksit ödemesi başarılı!");
      setTimeout(() => {
        setPopupOpen(false);
        fetchTaksitler();
      }, 1200);
    } catch (err) {
      setOdemeError("Taksit ödemesi başarısız oldu.");
    }
    setOdemeLoading(false);
  };

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

  return (
    <div className="flex flex-col items-center min-h-[80vh] bg-red-200">
      <h2 className="text-3xl font-bold text-blue-900 mt-16 mb-2">Taksit Ödeme</h2>
      <p className="mb-8 text-gray-700">Taksit Ödeme sayfasındasınız</p>
      <div className="w-full max-w-4xl">
        <Table>
          <thead>
            <tr>
              <th className="px-4 py-2">Taksit No</th>
              <th className="px-4 py-2">Tutar</th>
              <th className="px-4 py-2">Son Ödeme Tarihi</th>
              <th className="px-4 py-2">Durum</th>
              <th className="px-4 py-2">Ödeme Türü</th>
              <th className="px-4 py-2">İşlem</th>
            </tr>
          </thead>
          <tbody>
            {taksitler.map((t) => (
              <tr key={t.taksitNo} className="text-center">
                <td className="px-4 py-2">{t.taksitNo}</td>
                <td className="px-4 py-2">{t.taksitTutari} ₺</td>
                <td className="px-4 py-2">{t.sonOdemeTarihi}</td>
                <td className="px-4 py-2">{t.durum}</td>
                <td className="px-4 py-2">{t.odemeTuru}</td>
                <td className="px-4 py-2">
                  {t.durum === "ODENMEDI" ? (
                    <Button onClick={() => openPopup(t)}>Öde</Button>
                  ) : (
                    <span className="text-green-600 font-semibold">Ödendi</span>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
      {/* Pop-up */}
      <Modal open={popupOpen} onClose={() => setPopupOpen(false)}>
        <h3 className="text-xl font-bold mb-4">Taksit Ödeme</h3>
        <div className="mb-2">Taksit No: <span className="font-mono">{selectedTaksit?.taksitNo}</span></div>
        <div className="mb-4">
          <label className="mr-2 font-semibold">Ödeme Türü:</label>
          <select
            className="border rounded px-2 py-1"
            value={odemeTuru}
            onChange={e => setOdemeTuru(e.target.value)}
            disabled={odemeLoading}
          >
            {ODEME_TURLERI.map(opt => (
              <option key={opt.value} value={opt.value}>{opt.label}</option>
            ))}
          </select>
        </div>
        <Button onClick={handleGetOdemeBilgisi} disabled={odemeLoading || !selectedTaksit} className="mb-4">
          Taksit Ödeme Bilgisi Getir
        </Button>
        {odemeLoading && <div className="text-blue-600 mb-2">Yükleniyor...</div>}
        {odemeError && <div className="text-red-500 mb-2">{odemeError}</div>}
        {odemeBilgisi && (
          <div className="bg-gray-50 rounded p-4 mb-4">
            <div><b>Güncellenmiş Tutar:</b> {odemeBilgisi.guncellenmisTutar} ₺</div>
            <div><b>Faiz:</b> {odemeBilgisi.faizMiktari} ₺</div>
            <div><b>İndirim:</b> {odemeBilgisi.indirimMiktari} ₺</div>
            <div><b>Cezai Miktar:</b> {odemeBilgisi.cezaMiktari} ₺</div>
          </div>
        )}
        {odemeBilgisi && (
          <Button onClick={handleOde} disabled={odemeLoading || odemeBilgisi.guncellenmisTutar == null}>
            {odemeLoading ? "Ödeniyor..." : "Öde"}
          </Button>
        )}
        {odemeSuccess && <div className="text-green-600 mt-4">{odemeSuccess}</div>}
      </Modal>
    </div>
  );
};

export default TaksitOdeme; 