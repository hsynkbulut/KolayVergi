# KolayVergi Backend

KolayVergi uygulamasının backend (sunucu) kısmı, Java ve Spring Boot ile geliştirilmiştir. Tüm iş mantığı, API endpointleri, authentication ve veritabanı işlemleri bu projede yönetilir.

---

## 🚩 Proje Amacı
- Vergi, borç, taksit ve alışveriş işlemlerinin güvenli ve hızlı şekilde yönetilmesini sağlamak
- RESTful API ile frontend uygulamasına veri sağlamak
- JWT ile güvenli oturum ve rol bazlı yetkilendirme sunmak

---

## 🛠️ Kullanılan Teknolojiler
- Java 21
- Spring Boot
- Spring Security & JWT
- PostgreSQL
- JPA (Hibernate)
- Swagger/OpenAPI (API dokümantasyonu)
- Docker & Docker Compose
- pgAdmin (veritabanı arayüzü)

---

## 🚀 Kurulum ve Çalıştırma

### 1. Bağımlılıkları Yükleyin ve .jar Oluşturun
```bash
./mvnw clean package -DskipTests
```
- Derlenmiş .jar dosyası `target/` klasöründe oluşur.

### 2. Docker ile Başlatma (Tavsiye Edilen)
```bash
docker-compose up --build
```
- Spring Boot backend, PostgreSQL ve pgAdmin birlikte başlatılır.
- `docker-compose.yml` ve `src/main/resources/application.properties` dosyalarındaki veritabanı ayarlarının uyumlu olduğuna dikkat edin.

### 3. Manuel Başlatma (Docker olmadan)
```bash
mvn install
mvn spring-boot:run
```
- PostgreSQL veritabanınızın çalışır ve bağlantı bilgilerinin doğru olduğundan emin olun.

---

## 📁 Önemli Dizinler
- `src/main/java/` : Tüm Java kaynak kodları
- `src/main/resources/` : Konfigürasyon dosyaları (ör. application.properties)

---

## 📖 Swagger API Dokümantasyonu
- API endpointlerini ve dokümantasyonu görmek için backend çalışırken:
  - [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## ⚠️ Notlar
- `docker-compose.yml` ve `application.properties` dosyalarındaki veritabanı ayarları (isim, kullanıcı, şifre) birebir aynı olmalıdır.
- Ortam değişkenleri ve hassas bilgiler `.env` dosyası ile yönetilebilir.

---

## 📞 İletişim
- Hüseyin Karabulut: [hsyn.kbulut@gmail.com](mailto:hsyn.kbulut@gmail.com)
- Mustafa Fatih Güçlüer: [mfgucluer1@hotmail.com](mailto:mfgucluer1@hotmail.com) 