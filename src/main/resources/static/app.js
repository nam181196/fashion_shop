async function fetchBanks() {
  const statusEl = document.getElementById('status');
  const table = document.getElementById('banks');
  const tbody = table.querySelector('tbody');
  statusEl.textContent = 'Đang tải...';
  table.classList.add('hidden');
  tbody.innerHTML = '';
  try {
    const res = await fetch('/api/v1/banks');
    const json = await res.json();
    if (!json || !json.data) throw new Error('No data');
    const banks = json.data;
    statusEl.textContent = `Tìm thấy ${banks.length} ngân hàng`;
    banks.forEach(b => {
      const tr = document.createElement('tr');
      tr.innerHTML = `<td>${escapeHtml(b.code)}</td><td>${escapeHtml(b.name)}</td><td><a href="${escapeAttr(b.website)}" target="_blank">${escapeHtml(b.website)}</a></td><td>${b.active ? '✅' : '❌'}</td>`;
      tbody.appendChild(tr);
    });
    table.classList.remove('hidden');
  } catch (e) {
    statusEl.textContent = 'Lỗi khi tải dữ liệu: ' + e.message;
  }
}

function escapeHtml(s){ if(!s) return ''; return String(s).replace(/[&<>"']/g, c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":"&#39;"})[c]); }
function escapeAttr(s){ return escapeHtml(s); }

document.getElementById('refresh').addEventListener('click', fetchBanks);
document.getElementById('q').addEventListener('input', (e)=>{
  const q = e.target.value.trim().toLowerCase();
  const rows = document.querySelectorAll('#banks tbody tr');
  rows.forEach(r=>{
    const text = r.textContent.toLowerCase();
    r.style.display = (!q || text.includes(q)) ? '' : 'none';
  });
});

window.addEventListener('load', ()=>{
  fetchBanks();
});
