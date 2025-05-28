import { useEffect } from 'react';

const Modal = ({ open, onClose, children, title }) => {
  useEffect(() => {
    if (!open) return;
    const handleKeyDown = (e) => {
      if (e.key === 'Escape') onClose();
    };
    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [open, onClose]);

  if (!open) return null;

  return (
    <div className="fixed inset-0 flex items-center justify-center z-50 backdrop-blur-sm bg-black/40 transition-all duration-200">
      <div className="bg-white rounded-2xl shadow-2xl p-4 md:p-6 relative animate-fadeIn scale-95 max-w-lg w-full max-h-[80vh] overflow-y-auto">
        <button onClick={onClose} className="absolute top-3 right-3 text-gray-400 hover:text-red-500 text-2xl font-bold transition-colors" aria-label="Kapat">&times;</button>
        {title && <h2 className="text-lg md:text-xl font-bold mb-3 md:mb-4">{title}</h2>}
        <div className="text-sm md:text-base leading-relaxed">
          {children}
        </div>
      </div>
    </div>
  );
};

export default Modal; 