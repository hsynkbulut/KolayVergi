import React, { useState } from 'react';
import Modal from '../ui/Modal';

const Footer = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isPrivacyModalOpen, setIsPrivacyModalOpen] = useState(false);
  const [isHelpModalOpen, setIsHelpModalOpen] = useState(false);

  return (
    <footer className="w-full bg-blue-50 text-blue-900 border-t mt-8">
      <div className="max-w-7xl mx-auto px-4 md:px-8 py-8 flex flex-col md:flex-row items-center justify-between gap-6">
        <div className="flex flex-col items-center md:items-start">
          <span className="font-extrabold text-xl md:text-2xl tracking-tight">KolayVergi</span>
          <span className="text-sm text-blue-800 mt-1">Vergi işlemlerinizde güvenli ve hızlı çözüm.</span>
        </div>
        <div className="flex flex-wrap items-center gap-5 text-sm">
          <a href="/iletisim" className="hover:underline hover:text-blue-700 transition-colors">İletişim</a>
          <a href="#" onClick={() => setIsHelpModalOpen(true)} className="hover:underline hover:text-blue-700 transition-colors">Yardım</a>
          <a href="#" onClick={() => setIsPrivacyModalOpen(true)} className="hover:underline hover:text-blue-700 transition-colors">Gizlilik Politikası</a>
          <a href="#" onClick={() => setIsModalOpen(true)} className="hover:underline hover:text-blue-700 transition-colors">Kullanım Şartları</a>
        </div>
        <div className="flex items-center gap-4">
          <a href="https://www.linkedin.com/company/kolayvergi" target="_blank" rel="noopener noreferrer" aria-label="LinkedIn" className="hover:text-blue-700 transition-colors">
            <svg width="24" height="24" fill="currentColor" viewBox="0 0 24 24"><path d="M19 0h-14c-2.761 0-5 2.239-5 5v14c0 2.761 2.239 5 5 5h14c2.762 0 5-2.239 5-5v-14c0-2.761-2.238-5-5-5zm-11 19h-3v-10h3v10zm-1.5-11.268c-.966 0-1.75-.784-1.75-1.75s.784-1.75 1.75-1.75 1.75.784 1.75 1.75-.784 1.75-1.75 1.75zm13.5 11.268h-3v-5.604c0-1.337-.025-3.063-1.868-3.063-1.868 0-2.154 1.459-2.154 2.967v5.7h-3v-10h2.881v1.367h.041c.401-.761 1.381-1.563 2.841-1.563 3.039 0 3.6 2.001 3.6 4.601v5.595z"/></svg>
          </a>
          <a href="https://twitter.com/kolayvergi" target="_blank" rel="noopener noreferrer" aria-label="Twitter" className="hover:text-blue-700 transition-colors">
            <svg width="24" height="24" fill="currentColor" viewBox="0 0 24 24"><path d="M24 4.557a9.93 9.93 0 0 1-2.828.775 4.932 4.932 0 0 0 2.165-2.724c-.951.564-2.005.974-3.127 1.195a4.92 4.92 0 0 0-8.384 4.482c-4.086-.205-7.713-2.164-10.141-5.144a4.822 4.822 0 0 0-.666 2.475c0 1.708.87 3.216 2.188 4.099a4.904 4.904 0 0 1-2.229-.616c-.054 2.281 1.581 4.415 3.949 4.89a4.936 4.936 0 0 1-2.224.084c.627 1.956 2.444 3.377 4.6 3.417a9.867 9.867 0 0 1-6.102 2.104c-.396 0-.787-.023-1.175-.069a13.945 13.945 0 0 0 7.548 2.212c9.057 0 14.009-7.513 14.009-14.009 0-.213-.005-.425-.014-.636a10.012 10.012 0 0 0 2.457-2.548z"/></svg>
          </a>
          <a href="https://github.com/kolayvergi" target="_blank" rel="noopener noreferrer" aria-label="GitHub" className="hover:text-blue-700 transition-colors">
            <svg width="24" height="24" fill="currentColor" viewBox="0 0 24 24"><path d="M12 .297c-6.63 0-12 5.373-12 12 0 5.303 3.438 9.8 8.205 11.387.6.113.82-.258.82-.577 0-.285-.01-1.04-.015-2.04-3.338.724-4.042-1.415-4.042-1.415-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.084-.729.084-.729 1.205.084 1.84 1.236 1.84 1.236 1.07 1.834 2.809 1.304 3.495.997.108-.775.418-1.305.762-1.605-2.665-.305-5.466-1.334-5.466-5.931 0-1.31.469-2.381 1.236-3.221-.124-.303-.535-1.523.117-3.176 0 0 1.008-.322 3.301 1.23a11.52 11.52 0 0 1 3.003-.404c1.018.005 2.045.138 3.003.404 2.291-1.553 3.297-1.23 3.297-1.23.653 1.653.242 2.873.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.803 5.624-5.475 5.921.43.372.823 1.102.823 2.222 0 1.606-.014 2.898-.014 3.293 0 .322.218.694.825.576C20.565 22.092 24 17.592 24 12.297c0-6.627-5.373-12-12-12"/></svg>
          </a>
        </div>
        <div className="text-xs text-blue-700 text-center md:text-right">
          &copy; {new Date().getFullYear()} KolayVergi. Tüm hakları saklıdır.
        </div>
      </div>

      <Modal open={isModalOpen} onClose={() => setIsModalOpen(false)} title="Kullanım Şartları">
        <div className="prose max-w-none">
          <p className="mb-4">
            KolayVergi uygulamasını kullanarak aşağıdaki şartları kabul etmiş olursunuz:
          </p>
          <ul className="list-disc pl-5 space-y-2">
            <li>Uygulamayı yalnızca yasal amaçlar için kullanacağınızı taahhüt edersiniz.</li>
            <li>Verdiğiniz tüm bilgilerin doğru ve güncel olduğundan emin olmalısınız.</li>
            <li>Hesap güvenliğinizden ve şifrenizin gizliliğinden siz sorumlusunuz.</li>
            <li>Uygulama üzerinden yapılan tüm işlemlerin yasal ve etik kurallara uygun olması gerekmektedir.</li>
            <li>KolayVergi, kullanıcı verilerinin güvenliği ve gizliliği konusunda gerekli önlemleri alır, ancak internet üzerinden yapılan işlemlerde mutlak güvenlik garantisi verilemez.</li>
          </ul>
          <p className="mt-4">
            Bu şartlar, uygulamanın kullanımı sırasında güncellenebilir. Güncel şartları takip etmek sizin sorumluluğunuzdadır.
          </p>
        </div>
      </Modal>

      <Modal open={isPrivacyModalOpen} onClose={() => setIsPrivacyModalOpen(false)} title="Gizlilik Politikası">
        <div className="text-sm md:text-base leading-relaxed">
          <p className="mb-4">
            KolayVergi olarak kişisel verilerinizin güvenliği ve gizliliği bizim için önemlidir. Bu gizlilik politikası, uygulamamızı kullanırken verilerinizin nasıl toplandığını, kullanıldığını ve korunduğunu açıklar.
          </p>

          <div className="font-semibold mt-4 mb-2 text-blue-900">Toplanan Veriler</div>
          <ul className="list-disc pl-5 space-y-1 mb-4">
            <li>Kimlik bilgileri (ad, soyad, e-posta adresi)</li>
            <li>Vergi ve borç bilgileri</li>
            <li>Alışveriş geçmişi ve ödeme bilgileri</li>
          </ul>

          <div className="font-semibold mt-4 mb-2 text-blue-900">Verilerin Kullanımı</div>
          <ul className="list-disc pl-5 space-y-1 mb-4">
            <li>Hizmetlerimizi sunmak ve geliştirmek</li>
            <li>Hesabınızı yönetmek ve güvenliğini sağlamak</li>
            <li>Yasal yükümlülüklerimizi yerine getirmek</li>
          </ul>

          <div className="font-semibold mt-4 mb-2 text-blue-900">Veri Güvenliği</div>
          <p className="mb-2">
            Verilerinizi korumak için endüstri standardı güvenlik önlemleri kullanıyoruz:
          </p>
          <ul className="list-disc pl-5 space-y-1 mb-4">
            <li>SSL şifreleme ile güvenli veri iletişimi</li>
            <li>Düzenli güvenlik güncellemeleri ve denetimleri</li>
            <li>Erişim kontrolü ve yetkilendirme sistemleri</li>
          </ul>

          <div className="font-semibold mt-4 mb-2 text-blue-900">Çerezler ve İzleme</div>
          <p className="mb-2">
            Sitemizde çerezler kullanılmaktadır. Bu çerezler:
          </p>
          <ul className="list-disc pl-5 space-y-1 mb-4">
            <li>Oturum yönetimi ve güvenlik</li>
            <li>Hizmet kalitesini iyileştirme</li>
          </ul>

          <div className="font-semibold mt-4 mb-2 text-blue-900">Haklarınız</div>
          <p className="mb-2">
            KVKK kapsamında aşağıdaki haklara sahipsiniz:
          </p>
          <ul className="list-disc pl-5 space-y-1 mb-4">
            <li>Verilerinize erişim ve düzeltme talep etme</li>
            <li>Verilerinizin silinmesini isteme</li>
            <li>Veri işlemeye itiraz etme</li>
            <li>Verilerinizin aktarılmasını talep etme</li>
          </ul>

          <p className="mt-4 text-xs text-gray-600">
            Bu gizlilik politikası, 28.05.2025 tarihinden itibaren geçerlidir. Politikamızda değişiklik yapılması durumunda, değişiklikler bu sayfada yayınlanacaktır.
          </p>
        </div>
      </Modal>

      <Modal open={isHelpModalOpen} onClose={() => setIsHelpModalOpen(false)} title="Yardım & Sıkça Sorulan Sorular">
        <div className="text-sm md:text-base leading-relaxed">
          <div className="font-semibold mb-2 text-blue-900">Sıkça Sorulan Sorular</div>
          <div className="mb-3">
            <b>Giriş yapamıyorum, ne yapmalıyım?</b>
            <div className="ml-2 mb-2">E-posta ve şifrenizi doğru girdiğinizden emin olun. Şifrenizi unuttuysanız "Şifremi Unuttum" bağlantısını kullanın.</div>
            <b>Borçlarımı nereden görebilirim?</b>
            <div className="ml-2 mb-2">Ana menüdeki "Borçlarım" bölümünden tüm borç ve taksitlerinizi görüntüleyebilirsiniz.</div>
            <b>Alışveriş eklerken hata alıyorum, ne yapmalıyım?</b>
            <div className="ml-2 mb-2">Zorunlu alanları eksiksiz doldurduğunuzdan ve internet bağlantınızın olduğundan emin olun. Sorun devam ederse destek ekibimize ulaşın.</div>
            <b>Destek ekibine nasıl ulaşabilirim?</b>
            <div className="ml-2 mb-2">"İletişim" bölümünden bize e-posta gönderebilir veya <a href="mailto:destek@kolayvergi.com" className="text-blue-700 underline">destek@kolayvergi.com</a> adresini kullanabilirsiniz.</div>
          </div>
          <div className="font-semibold mb-2 text-blue-900">Kısa Kullanım Rehberi</div>
          <ul className="list-disc pl-5 space-y-1 mb-4">
            <li><b>Ana Sayfa:</b> Genel özet ve hızlı erişim menüsü.</li>
            <li><b>Borçlarım:</b> Tüm borç ve taksitlerinizi görüntüleyin.</li>
            <li><b>Alışverişlerim:</b> Geçmiş alışverişlerinizi ve detaylarını inceleyin.</li>
            <li><b>Taksit Ödeme:</b> Borç taksitlerinizi kolayca ödeyin.</li>
          </ul>
          <div className="mt-2 text-xs text-gray-600">
            Daha fazla yardım için <a href="mailto:destek@kolayvergi.com" className="text-blue-700 underline">destek@kolayvergi.com</a> adresine e-posta gönderebilirsiniz.
          </div>
        </div>
      </Modal>
    </footer>
  );
};

export default Footer; 