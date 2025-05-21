# KolayVergi

KolayVergi, vergi hesaplamalarını ve taksit ödemelerini kolaylaştırmak için geliştirilmiş modern bir web uygulamasıdır. Bu proje, vergi mükelleflerinin vergi hesaplamalarını ve taksit ödemelerini hızlı ve doğru bir şekilde yapabilmelerini sağlar.

## 🚀 Özellikler

- Vergi hesaplama araçları
- Taksit ödeme sistemi
- Kullanıcı dostu arayüz
- Güvenli kimlik doğrulama
- Borç takip sistemi
- Alışveriş vergisi hesaplama
- Responsive tasarım

## 🛠️ Kullanılan Teknolojiler

### Frontend
- React.js
- Vite
- TypeScript
- Tailwind CSS
- React Router
- Axios

### Backend
- Spring Boot
- Java
- PostgreSQL
- JWT Authentication
- Spring Security
- Swagger/OpenAPI

## 📋 API Endpoints

### Kimlik Doğrulama İşlemleri
- POST /api/v1/auth/login - Kullanıcı girişi
- POST /api/v1/auth/refresh - Token yenileme
- POST /api/v1/auth/register - Yeni kullanıcı kaydı
- PUT /api/v1/auth/update - Profil güncelleme

### Kullanıcı İşlemleri
- GET /api/v1/kullanicilar/{id} - Kullanıcı bilgilerini getir
- DELETE /api/v1/kullanicilar/{id}/delete - Kullanıcı sil
- GET /api/v1/kullanicilar - Tüm kullanıcıları listele (Admin)

### Alışveriş İşlemleri
- POST /api/v1/alisverisler - Yeni alışveriş oluştur
- GET /api/v1/alisverisler/{id} - Alışveriş detaylarını getir
- PUT /api/v1/alisverisler/{id} - Alışveriş bilgilerini güncelle
- DELETE /api/v1/alisverisler/{id} - Alışveriş kaydını sil

### Borç İşlemleri
- GET /api/v1/borclar/{id} - Borç detaylarını getir

### Taksit Ödeme İşlemleri
- POST /api/v1/odemeler/taksit-odeme - Taksit ödemesi yap
- GET /api/v1/odemeler/{taksitNo} - Taksit ödeme detaylarını getir

## 🚀 Kurulum

1. Projeyi klonlayın:
bash
git clone https://github.com/your-username/kolayvergi.git


2. Frontend bağımlılıklarını yükleyin:
bash
cd kolayvergi-frontend
npm install


3. Backend bağımlılıklarını yükleyin:
bash
cd kolayvergi-backend
mvn install


4. Uygulamayı başlatın:
bash
# Frontend
npm run dev

# Backend
mvn spring-boot:run


## 👥 Katkıda Bulunanlar

- [Hüseyin Karabulut](https://github.com/hsynkbulut) - Frontend Geliştirici
- [Mustafa Fatih Güçlüer](https://github.com/mfgucluer) - Backend Geliştirici

## 📝 Lisans

Bu proje MIT lisansı altında lisanslanmıştır. Detaylar için [LICENSE](LICENSE) dosyasına bakın.

## 📞 İletişim

Proje ile ilgili sorularınız için:
- Hüseyin Karabulut: [GitHub](https://github.com/hsynkbulut)
- Mustafa Fatih Güçlüer: [GitHub](https://github.com/mfgucluer)
