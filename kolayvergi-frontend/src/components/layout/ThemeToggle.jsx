import React, { useEffect, useState } from 'react';

const ThemeToggle = () => {
  const [theme, setTheme] = useState(() => {
    if (typeof window !== 'undefined') {
      return localStorage.getItem('theme') || 'light';
    }
    return 'light';
  });

  useEffect(() => {
    if (theme === 'dark') {
      document.documentElement.classList.add('dark');
    } else {
      document.documentElement.classList.remove('dark');
    }
    localStorage.setItem('theme', theme);
  }, [theme]);

  return (
    <button
      aria-label="Tema Değiştir"
      onClick={() => setTheme(theme === 'dark' ? 'light' : 'dark')}
      className="relative flex items-center w-14 h-8 rounded-full transition-colors duration-300 focus:outline-none bg-gray-200 dark:bg-[#23272f] border border-gray-300 dark:border-[#353a45] shadow-sm"
    >
      <span
        className={`absolute left-1 top-1 w-6 h-6 rounded-full bg-white dark:bg-[#353a45] shadow-md transition-transform duration-300 ${theme === 'dark' ? 'translate-x-6' : ''}`}
      />
      {/* Güneş ikonu */}
      <svg className="absolute left-2 top-2 w-4 h-4 text-yellow-400" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24">
        <circle cx="12" cy="12" r="5" />
        <path d="M12 1v2M12 21v2M4.22 4.22l1.42 1.42M18.36 18.36l1.42 1.42M1 12h2M21 12h2M4.22 19.78l1.42-1.42M18.36 5.64l1.42-1.42" />
      </svg>
      {/* Ay ikonu */}
      <svg className="absolute right-2 top-2 w-4 h-4 text-blue-300" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24">
        <path d="M21 12.79A9 9 0 1 1 11.21 3a7 7 0 0 0 9.79 9.79z" />
      </svg>
    </button>
  );
};

export default ThemeToggle; 