import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import ProtectedRoute from './components/ProtectedRoute';
import Login from './pages/Login';
import Register from './pages/Register';
import Layout from './components/layout/Layout';
import Borclarim from './pages/Borclarim';

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
                  <Page title="Ana Sayfa" />
                </Layout>
              </ProtectedRoute>
            }
          />
          <Route
            path="/profile"
            element={
              <ProtectedRoute>
                <Layout currentPath="/profile">
                  <Page title="Profilim" />
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
                  <Page title="Alışverişlerim" />
                </Layout>
              </ProtectedRoute>
            }
          />
          <Route
            path="/taksit-odeme"
            element={
              <ProtectedRoute>
                <Layout currentPath="/taksit-odeme">
                  <Page title="Taksit Ödeme" />
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
