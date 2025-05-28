import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import ProtectedRoute from './components/ProtectedRoute';
import Login from './pages/Login';
import Register from './pages/Register';
import Layout from './components/layout/Layout';
import AlisverisListesi from './pages/alisveris/AlisverisListesi';
import AlisverisDetay from './pages/alisveris/AlisverisDetay';
import AlisverisEkle from './pages/alisveris/AlisverisEkle';
import Borclarim from './pages/Borclarim';
import TaksitOdeme from "./pages/TaksitOdeme";
import AnaSayfa from "./pages/AnaSayfa";
import Iletisim from './pages/Iletisim';

const Page = ({ title }) => (
  <div className="flex flex-col items-center justify-center w-full h-full gap-2">
    <div className="text-2xl font-bold text-blue-700">{title}</div>
    <div className="text-base text-gray-600">{title} sayfasındasınız</div>
  </div>
);

function App() {
  return (
    <Router>
      <AuthProvider>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route
            path="/"
            element={
              <ProtectedRoute>
                <Layout currentPath="/">
                  <AnaSayfa />
                </Layout>
              </ProtectedRoute>
            }
          />
          <Route
            path="/borclarim"
            element={
              <ProtectedRoute>
                <Layout currentPath="/borclarim">
                  <Borclarim />
                </Layout>
              </ProtectedRoute>
            }
          />
          <Route
            path="/alisverislerim"
            element={
              <ProtectedRoute>
                <Layout currentPath="/alisverislerim">
                  <AlisverisListesi />
                </Layout>
              </ProtectedRoute>
            }
          />
          <Route
            path="/alisverislerim/:id"
            element={
              <ProtectedRoute>
                <Layout currentPath="/alisverislerim">
                  <AlisverisDetay />
                </Layout>
              </ProtectedRoute>
            }
          />
          <Route
            path="/alisveris/yeni"
            element={
              <ProtectedRoute>
                <Layout currentPath="/alisverislerim">
                  <AlisverisEkle />
                </Layout>
              </ProtectedRoute>
            }
          />
          <Route
            path="/taksit-odeme"
            element={
              <ProtectedRoute>
                <Layout currentPath="/taksit-odeme">
                  <TaksitOdeme />
                </Layout>
              </ProtectedRoute>
            }
          />
          <Route
            path="/iletisim"
            element={
              <ProtectedRoute>
                <Layout currentPath="/iletisim">
                  <Iletisim />
                </Layout>
              </ProtectedRoute>
            }
          />
          <Route
            path="/admin"
            element={
              <ProtectedRoute requiredRoles={['ROLE_ADMIN']}>
                <Layout currentPath="/admin">
                  <Page title="Admin Sayfası" />
                </Layout>
              </ProtectedRoute>
            }
          />
        </Routes>
      </AuthProvider>
    </Router>
  );
}

export default App;
