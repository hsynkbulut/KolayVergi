import React, { useEffect, useState } from "react";
import axiosInstance from "../services/axios.config";

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

  if (loading) return <div className="flex items-center justify-center h-[60vh]"><div className="text-lg animate-pulse">Yükleniyor...</div></div>;
  if (error) return <div className="flex items-center justify-center h-[60vh]"><div className="text-red-500 text-lg font-semibold">{error}</div></div>;

  return (
    <div className="flex items-center justify-center min-h-[80vh] bg-transparent">
      <div className="bg-white/90 rounded-3xl shadow-2xl p-14 w-full max-w-xl flex flex-col items-center border border-gray-200">
        <h2 className="text-4xl font-extrabold text-gray-800 mb-10 tracking-tight drop-shadow-lg">Borç Bilgilerim</h2>
        <div className="w-full flex flex-col gap-8">
          <div className="flex flex-col items-center justify-center bg-blue-50 rounded-xl px-8 py-5 shadow-md text-2xl">
            <span className="font-semibold text-gray-700 mb-2">Toplam Borç</span>
            <span className="font-extrabold text-blue-700 text-3xl">{borc?.toplamBorc} ₺</span>
          </div>
          <div className="flex flex-col items-center justify-center bg-green-50 rounded-xl px-8 py-5 shadow-md text-2xl">
            <span className="font-semibold text-gray-700 mb-2">Kalan Borç</span>
            <span className="font-extrabold text-green-700 text-3xl">{borc?.kalanBorc} ₺</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Borclarim; 