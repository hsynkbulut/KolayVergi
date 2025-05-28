import React, { useEffect, useState } from "react";
import axiosInstance from "../services/axios.config";
import Table from "../components/ui/Table";
import Button from "../components/ui/Button";
import Modal from "../components/ui/Modal";
import Icon from "../components/ui/Icon";

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

  const parseTaksitNoDate = (taksitNo) => {
    const str = taksitNo.substring(0, 12);
    const dateStr = `${str.substring(0,4)}-${str.substring(4,6)}-${str.substring(6,8)}T${str.substring(8,10)}:${str.substring(10,12)}`;
    return new Date(dateStr);
  };

  const fetchTaksitler = () => {
    setLoading(true);
    axiosInstance
      .get("/taksitler")
      .then((res) => {
        // taksitNo'nun ilk 12 karakterine göre sırala (oluşturma tarihi ve saati)
        const sorted = [...res.data].sort((a, b) => parseTaksitNoDate(a.taksitNo) - parseTaksitNoDate(b.taksitNo));
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

  const columns = [
    { header: "Taksit No", accessor: "taksitNo" },
    { header: "Tutar", accessor: "taksitTutari", cell: v => v + " ₺" },
    { header: "Son Ödeme Tarihi", accessor: "sonOdemeTarihi" },
    { header: "Durum", accessor: "durum" },
    { header: "Ödeme Türü", accessor: "odemeTuru" },
    {
      header: "İşlem",
      accessor: "id",
      cell: (_, row) =>
        row.durum === "ODENMEDI"
          ? <Button onClick={() => openPopup(row)} className="bg-gradient-to-r from-[#2563eb] to-blue-700 hover:from-blue-700 hover:to-blue-800 text-white font-semibold rounded-lg px-4 py-2 shadow-md flex items-center gap-2"><Icon name="FiCreditCard" className="w-5 h-5" />Öde</Button>
          : <span className="inline-flex items-center justify-center bg-green-100 text-green-700 px-4 py-2 rounded-lg text-base font-semibold shadow-sm">Ödendi</span>
    }
  ];

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
    <div className="w-full flex flex-col items-center justify-center py-8 px-2">
      <div className="flex flex-col items-center mb-8">
        <div className="bg-blue-100 rounded-full p-4 mb-3 shadow-sm">
          <Icon name="FiCreditCard" className="w-10 h-10 text-blue-500" />
        </div>
        <h2 className="text-3xl font-extrabold text-gray-900 mb-2 text-center">Taksit Ödeme</h2>
        <p className="mb-8 text-gray-500 text-center text-base">Taksitlerinizi kolayca ödeyin!</p>
      </div>
      <div className="w-full max-w-5xl mx-auto bg-blue-50 rounded-2xl shadow-xl p-8">
        <Table columns={columns} data={taksitler} emptyMessage="Kayıt bulunamadı" />
      </div>
      {/* Pop-up */}
      <Modal open={popupOpen} onClose={() => setPopupOpen(false)}>
        <div className="flex flex-col items-center text-center p-2">
          <div className="bg-blue-100 rounded-full p-4 mb-3 shadow-sm">
            <Icon name="FiCreditCard" className="w-8 h-8 text-blue-500" />
          </div>
          <h3 className="text-xl font-bold mb-2 text-gray-900">Taksit Ödeme</h3>
          <div className="mb-2 text-gray-700">Taksit No: <span className="font-mono font-semibold text-blue-700">{selectedTaksit?.taksitNo}</span></div>
          <div className="mb-4">
            <label className="mr-2 font-semibold">Ödeme Türü:</label>
            <select
              className="border rounded-lg px-3 py-2 focus:ring-2 focus:ring-blue-300 focus:border-blue-500 transition"
              value={odemeTuru}
              onChange={e => setOdemeTuru(e.target.value)}
              disabled={odemeLoading}
            >
              {ODEME_TURLERI.map(opt => (
                <option key={opt.value} value={opt.value}>{opt.label}</option>
              ))}
            </select>
          </div>
          <Button onClick={handleGetOdemeBilgisi} disabled={odemeLoading || !selectedTaksit} className="mb-4 bg-gradient-to-r from-[#2563eb] to-blue-700 hover:from-blue-700 hover:to-blue-800 text-white font-semibold rounded-lg px-6 py-2 shadow-md flex items-center gap-2">
            <Icon name="FiSearch" className="w-5 h-5" /> Taksit Ödeme Bilgisi Getir
          </Button>
          {odemeLoading && <div className="text-blue-600 mb-2">Yükleniyor...</div>}
          {odemeError && <div className="text-red-500 mb-2">{odemeError}</div>}
          {odemeBilgisi && (
            <div className="bg-gray-50 rounded-xl p-4 mb-4 w-full max-w-xs mx-auto text-left">
              <div className="mb-1"><b>Güncellenmiş Tutar:</b> {odemeBilgisi.guncellenmisTutar} ₺</div>
              <div className="mb-1"><b>Faiz:</b> {odemeBilgisi.faizMiktari} ₺</div>
              <div className="mb-1"><b>İndirim:</b> {odemeBilgisi.indirimMiktari} ₺</div>
              <div className="mb-1"><b>Cezai Miktar:</b> {odemeBilgisi.cezaMiktari} ₺</div>
            </div>
          )}
          {odemeBilgisi && (
            <Button onClick={handleOde} disabled={odemeLoading || odemeBilgisi.guncellenmisTutar == null} className="bg-gradient-to-r from-[#2563eb] to-blue-700 hover:from-blue-700 hover:to-blue-800 text-white font-semibold rounded-lg px-8 py-2 shadow-md flex items-center gap-2 mt-2">
              <Icon name="FiCheckCircle" className="w-5 h-5" /> {odemeLoading ? "Ödeniyor..." : "Öde"}
            </Button>
          )}
          {odemeSuccess && <div className="text-green-600 mt-4 font-semibold">{odemeSuccess}</div>}
        </div>
      </Modal>
    </div>
  );
};

export default TaksitOdeme; 