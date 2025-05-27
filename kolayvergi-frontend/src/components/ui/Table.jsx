const Table = ({ columns, data, emptyMessage = "Kayıt bulunamadı", className = "" }) => {
  if (!data || data.length === 0) {
    return (
      <div className="w-full text-center text-gray-500 py-8 bg-white rounded-2xl shadow">
        {emptyMessage}
      </div>
    );
  }
  return (
    <div className="overflow-x-auto rounded-2xl shadow">
      <table className={`min-w-full bg-white divide-y divide-gray-200 ${className}`}>
        <thead>
          <tr>
            {columns.map((col) => (
              <th key={col.header} className="px-4 py-2 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                {col.header}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {data.map((row) => (
            <tr key={row.id} className="hover:bg-gray-50">
              {columns.map((col) => (
                <td key={col.accessor} className="px-4 py-2 whitespace-nowrap">
                  {col.cell ? col.cell(row[col.accessor], row) : row[col.accessor]}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Table; 