import React, {useEffect, useState} from 'react'
import axios from 'axios'

export default function App(){
  const [banks,setBanks]=useState([])
  const [q,setQ]=useState('')
  const [loading,setLoading]=useState(false)

  useEffect(()=>{load()},[])

  async function load(){
    setLoading(true)
    try{
      const res = await axios.get('/api/v1/banks')
      setBanks(res.data.data || [])
    }catch(e){
      console.error(e)
      setBanks([])
    }finally{setLoading(false)}
  }

  const filtered = banks.filter(b=> (b.code + ' ' + b.name).toLowerCase().includes(q.toLowerCase()))

  return (
    <div className="app">
      <header className="header">
        <h1>BankRate</h1>
        <p className="lead">Demo SPA — danh sách ngân hàng</p>
      </header>
      <div className="toolbar">
        <input placeholder="Tìm mã hoặc tên" value={q} onChange={e=>setQ(e.target.value)} />
        <button onClick={load}>{loading ? '...' : 'Làm mới'}</button>
      </div>
      <main>
        <table className="table">
          <thead><tr><th>Code</th><th>Name</th><th>Website</th><th>Active</th></tr></thead>
          <tbody>
            {filtered.map(b=> (
              <tr key={b.code}><td>{b.code}</td><td>{b.name}</td><td><a href={b.website} target="_blank" rel="noreferrer">{b.website}</a></td><td>{b.active ? '✅' : '❌'}</td></tr>
            ))}
          </tbody>
        </table>
      </main>
    </div>
  )
}
